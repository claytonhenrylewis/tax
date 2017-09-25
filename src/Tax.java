import java.io.*;
import java.net.URL;
import java.util.Iterator;

public class Tax {
  public static void main(String [] args) {
    Item item1 = new Item("1 book at 12.49");
    System.out.println(item1.getCount());
    System.out.println(item1.getName());
    System.out.println(item1.getPrice());

    WordNet wordNet = new WordNet("../synsets.txt", "../hypernyms.txt");
    //System.out.println(wordNet.isAncestor("food", "chocolate"));
  }
}