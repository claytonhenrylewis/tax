/**
 * Created by claytonlewis on 4/4/17.
 */

import java.io.*;
import java.util.Scanner;

public class SAP {
    private Digraph graph;

    public SAP(Digraph G) {
        graph = G;
    }

    public boolean isAncestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(graph, w);
        for (Integer i : v)
            if (pathW.hasPathTo(i))
                return true;
        return false;
    }

    //length of shortest ancestral path of v and w
    public int length(int v, int w) {
        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(graph, w);

        int anc = ancestor(v, w);
        int len;
        if (anc == -1) {
            len = -1;
        } else {
            len = pathV.distTo(anc) + pathW.distTo(anc);
        }
        return len;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(graph, w);

        int anc = ancestor(v, w);
        int len;
        if (anc == -1) {
            len = -1;
        } else {
            len = pathV.distTo(anc) + pathW.distTo(anc);
        }
        return len;
    }
    

    //return a common ancestor of v and w that participates in shortest
    //ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(graph, w);
        int shortestAncestor = -1;
        int shortestLength = Integer.MAX_VALUE;
        Stack<Integer> ancestors = new Stack<Integer>();

        for (int i = 0; i < graph.V(); i++) {
            if (pathV.hasPathTo(i) && pathW.hasPathTo(i)) {
                ancestors.push(i);
            }
        }

        for (Integer i : ancestors) {
            int length = pathV.distTo(i) + pathW.distTo(i);
            if (length < shortestLength) {
                shortestLength = length;
                shortestAncestor = i;
            }
        }

        return shortestAncestor;
    }

    
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(graph, w);
        int shortestAncestor = -1;
        int shortestLength = Integer.MAX_VALUE;
        Stack<Integer> ancestors = new Stack<Integer>();

        for (int i = 0; i < this.graph.V(); i++) {
            if (pathV.hasPathTo(i) && pathW.hasPathTo(i)) {
                ancestors.push(i);
            }
        }

        for (Integer i : ancestors) {
            int length = pathV.distTo(i) + pathW.distTo(i);
            if (length < shortestLength) {
                shortestLength = length;
                shortestAncestor = i;
            }
        }
        return shortestAncestor;
    }
    

    public boolean isValid(int v) {
        return ((v >= 0) && (v <= graph.V()));
    }

    public static void main(String[] args) {
        try {
            In in = new In(args[0]);
            SAP sap = new SAP(new Digraph(in));
            File file = new File(args[1]);
            Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)));
            while (scanner.hasNext()) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                int length = sap.length(v, w);
                int ancestor = sap.ancestor(v, w);
                System.out.printf("sap = %d, ancestor = %d\n", length, ancestor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
