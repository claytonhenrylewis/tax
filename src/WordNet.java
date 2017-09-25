import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by claytonlewis on 4/7/17.
 */
public final class WordNet {
    private Digraph graph;
    private int V;
    private SAP sap;
    private Map<String, ArrayList<Integer>> nouns = new HashMap<String, ArrayList<Integer>>();
    private Map<Integer, String> synsets = new HashMap<Integer, String>();

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In synsetsIn = new In(synsets);
        String line = synsetsIn.readLine();
        V = 1;
        while (line != null) {
            String[] lineElements = line.split(",");
            String[] words = lineElements[1].split(" ");
            int id = Integer.parseInt(lineElements[0]);

            for (String word : words) {
                if (nouns.containsKey(word)) {
                    nouns.get(word).add(id);
                } else {
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    ids.add(id);
                    nouns.put(word, ids);
                }
            }
            this.synsets.put(id, lineElements[1]);
            V++;
            line = synsetsIn.readLine();
        }

        graph = new Digraph(V);
        In hypernymsIn = new In(hypernyms);
        line = hypernymsIn.readLine();
        while (line != null) {
            String[] lineElements = line.split(",");
            int id = Integer.parseInt(lineElements[0]);
            for (String i : lineElements) {
                graph.addEdge(id, Integer.parseInt(i));
            }
            line = hypernymsIn.readLine();
        }
        sap = new SAP(graph);
    }

    // is the word a WordNet noun? This can be used to search for existing
    // nouns at the beginning of the printSap method
    public boolean isNoun(String word) {
        return (nouns.containsKey(word));
    }

    // print the synset (second field of synsets.txt) that is the common ancestor
    // of nounA and nounB in a shortest ancestral path as well as the length of the path,
    // following this format: "sap<space>=<space><number>,<space>ancestor<space>=<space><synsettext>"
    // If no such path exists the sap should contain -1 and ancestor should say "null"
    // This method should use the previously defined SAP datatype
    public void printSap(String nounA, String nounB) {
        int len = -1;
        int anc = -1;
        String ancestor = null;
        if (isNoun(nounA) && isNoun(nounB)) {
            ArrayList<Integer> idA = nouns.get(nounA);
            ArrayList<Integer> idB = nouns.get(nounB);
            len = sap.length(idA, idB);
            anc = sap.ancestor(idA, idB);
            ancestor = synsets.get(anc);
        }
        System.out.printf("sap = %d, ancestor = %s\n", len, ancestor);
    }

    public boolean isAncestor(String nounA, String nounB) {
        if (isNoun(nounA) && isNoun(nounB)) {
            ArrayList<Integer> idA = nouns.get(nounA);
            ArrayList<Integer> idB = nouns.get(nounB);
            return sap.isAncestor(idA, idB);
        }
        return false;
    }

    public static void main(String[] args) {
        WordNet wordNet = new WordNet(args[0], args[1]);
        In input = new In(args[2]);
        while (input.hasNextLine()) {
            String line = input.readLine();
            String lineElements[] = line.split(" ");
            wordNet.printSap(lineElements[0], lineElements[1]);
        }
    }
}
