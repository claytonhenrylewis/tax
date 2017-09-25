import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ArrayList;

public class Tax {
  public static void main(String [] args) {
    if (args.length < 3) {
      System.out.println("Usage: java -cp .:../bin/stdlib.jar:../bin/algs4.jar Tax [sales-tax-rate] [import-tax-rate] [input-file] [input-file] ...");
      return;
    }

    Item.setSalesTaxRate(Double.parseDouble(args[0]));
    Item.setImportTaxRate(Double.parseDouble(args[1]));

    WordNet wordNet = new WordNet("../synsets.txt", "../hypernyms.txt");
    Item.setWordNet(wordNet);

    Exemptions exemptions = new Exemptions("../exemptions.txt");
    Item.setExemptions(exemptions);

    ArrayList<ItemList> itemLists = new ArrayList<ItemList>();
    for (int i = 2; i < args.length; i++) {
      String path = "../input/";
      path = path.concat(args[i]);
      itemLists.add(new ItemList(path));
    }

    for (ItemList itemList : itemLists) {
      System.out.println(itemList.printList());
    }

    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("../output/output.txt"));
      for (ItemList itemList : itemLists) {
          String list = itemList.printList();
          bw.append(list);
      }
      bw.close();
    } catch (IOException e) {
      System.out.println("Error writing to output file.");
      e.printStackTrace();
    }
  }
}