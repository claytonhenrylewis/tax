import java.lang.Math;
import java.util.ArrayList;

//Item class represents one purchased item

public class Item {
  private int count;
  private String name;
  private double price;
  private boolean exempt;
  private boolean imported;
  private static WordNet wordNet;
  private static Exemptions exemptions;
  private static double salesTaxRate;
  private static double importTaxRate;

  //Set sales tax rate for all items
  public static void setSalesTaxRate(double newSalesTaxRate) {
    Item.salesTaxRate = newSalesTaxRate;
  }

  //Set import tax rate for all items
  public static void setImportTaxRate(double newImportTaxRate) {
    Item.importTaxRate = newImportTaxRate;
  }
  
  //Set up word net for all items
  public static void setWordNet(WordNet newWordNet) {
    Item.wordNet = newWordNet;
  }

  //Set exemptions for all items
  public static void setExemptions(Exemptions newExemptions) {
    Item.exemptions = newExemptions;
  }

  //Create new item from its component data
  public Item(int count, String name, double price, boolean imported) {
    this.count = count;
    this.name = name;
    this.price = price;
    this.exempt = this.checkExempt();
    this.imported = imported;
  }

  //Create a new item from a line of text input
  public Item(String lineItem) {
    String[] tokens = lineItem.split(" ");
    int i = 0;
    this.count = Integer.parseInt(tokens[i]);
    i++;
    if (tokens[i].toLowerCase().equals("imported")) {
      this.imported = true;
    }
    this.price = Math.ceil(Double.parseDouble(tokens[tokens.length - 1]) * 100.0) / 100.0;
    this.name = tokens[i];
    for (int j = i + 1; j < tokens.length - 2; j++) {
      this.name = this.name.concat(" ");
      this.name = this.name.concat(tokens[j]);
    }
    this.exempt = this.checkExempt();
  }

  //Access count
  public int getCount() {
    return this.count;
  }

  //Access name
  public String getName() {
    return this.name;
  }

  //Access price
  public double getPrice() {
    return this.price;
  }

  //Compute tax for item
  public double getSalesTax() {
    double rate = 0.0;
    double tax = 0.0;
    if (exempt)
      rate = 0.0;
    else
      rate = salesTaxRate;
    if (imported)
      rate += importTaxRate;
    tax = Math.ceil(this.count * rate * price * 20.0) / 20.0;
    return tax;
  }

  //Compute total price for item
  public double getTotalPrice() {
    return (Math.ceil(((this.count * this.price) + this.getSalesTax()) * 100.0) / 100.0);
  }

  //Check if item is exempt
  //Uses WordNet to check if one of the exempt items is an ancestor of this item
  private boolean checkExempt() {
    ArrayList<String> exempt = Item.exemptions.getExemptions();
    String[] tokens = this.name.split(" ");
    for (String t : tokens) {
      for (String e : exempt) {
        if (wordNet.isAncestor(e, t)) {
          return true;
      }
        if (t.length() > 1) {
          if (t.charAt(t.length() - 1) == 's') {
            String newT = t.substring(0, t.length() - 1);
            if (wordNet.isAncestor(e, newT))
              return true;
          }
        }
      }
    }
    return false;
  }
}