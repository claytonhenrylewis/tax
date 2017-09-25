import java.lang.Math;
import java.util.ArrayList;

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

  public static void setSalesTaxRate(double newSalesTaxRate) {
    Item.salesTaxRate = newSalesTaxRate;
  }

  public static void setImportTaxRate(double newImportTaxRate) {
    Item.importTaxRate = newImportTaxRate;
  }

  public static void setWordNet(WordNet newWordNet) {
    Item.wordNet = newWordNet;
  }

  public static void setExemptions(Exemptions newExemptions) {
    Item.exemptions = newExemptions;
  }

  public Item(int count, String name, double price, boolean imported) {
    this.count = count;
    this.name = name;
    this.price = price;
    this.exempt = this.checkExempt();
    this.imported = imported;
  }

  public Item(String lineItem) {
    String[] tokens = lineItem.split(" ");
    int i = 0;
    this.count = Integer.parseInt(tokens[i]);
    i++;
    if (tokens[i].toLowerCase().equals("imported")) {
      this.imported = true;
    }
    this.price = Double.parseDouble(tokens[tokens.length - 1]);
    this.name = tokens[i];
    for (int j = i + 1; j < tokens.length - 2; j++) {
      this.name = this.name.concat(" ");
      this.name = this.name.concat(tokens[j]);
    }
    this.exempt = this.checkExempt();
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getSalesTax() {
    double rate = 0.0;
    double tax = 0.0;
    if (exempt)
      rate = 0;
    else
      rate = salesTaxRate;
    if (imported)
      rate += importTaxRate;
    tax = Math.round(rate * price * 20.0) / 20.0;
    System.out.println(rate);
    return tax;
  }

  public double getTotalPrice() {
    return (this.price + this.getSalesTax());
  }

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