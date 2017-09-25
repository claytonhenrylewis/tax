import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ArrayList;

//Main class for executing all tasks

public class Tax {
  public static void main(String [] args) {
    //Check number of inputs
    if (args.length < 3) {
      System.out.println("Usage: java -cp .:../bin/stdlib.jar:../bin/algs4.jar Tax [sales-tax-rate] [import-tax-rate] [input-file] [input-file] ...");
      return;
    }

    //Set tax rates
    Item.setSalesTaxRate(Double.parseDouble(args[0]));
    Item.setImportTaxRate(Double.parseDouble(args[1]));

    //Set up word net
    WordNet wordNet = new WordNet("../synsets.txt", "../hypernyms.txt");
    Item.setWordNet(wordNet);

    //Load exemptions
    Exemptions exemptions = new Exemptions("../exemptions.txt");
    Item.setExemptions(exemptions);

    //Create item lists from input files
    ArrayList<ItemList> itemLists = new ArrayList<ItemList>();
    for (int i = 2; i < args.length; i++) {
      String path = "../input/";
      path = path.concat(args[i]);
      itemLists.add(new ItemList(path));
    }

    //Print receipts to stdout
    for (ItemList itemList : itemLists) {
      System.out.println(itemList.printList());
    }

    //Print receipts to text file
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