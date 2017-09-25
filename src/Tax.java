import java.io.*;
import java.net.URL;
import java.util.Iterator;

public class Tax {
  public static void main(String [] args) {
    WordNet wordNet = new WordNet("../synsets.txt", "../hypernyms.txt");
    Item.setWordNet(wordNet);

    Exemptions exemptions = new Exemptions("../exemptions.txt");
    exemptions.printExemptions();
    Item.setExemptions(exemptions);

    ItemList list1 = new ItemList("../input1.txt");
    ItemList list2 = new ItemList("../input2.txt");
    ItemList list3 = new ItemList("../input3.txt");

    System.out.println(list1.printList());
    System.out.println(list2.printList());
    System.out.println(list3.printList());
  }
}