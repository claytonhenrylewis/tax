import java.util.ArrayList;

public class ItemList {
  private ArrayList<Item> items;

  public ItemList(ArrayList<Item> items) {
    this.items = items;
  }

  public ItemList(String fileName) {
    this.items = new ArrayList<Item>();
    In itemsIn = new In(fileName);
    String line = itemsIn.readLine();
    while (line != null) {
      items.add(new Item(line));
      line = itemsIn.readLine();
    }
  }

  public void addItem(Item i) {
    this.items.add(i);
  }

  private double totalSalesTax() {
    double total = 0.0;
    for (Item item : this.items) {
      total += item.getSalesTax();
    }
    return total;
  }

  private double totalCost() {
    double total = 0.0;
    for (Item item : this.items) {
      total += item.getTotalPrice();
    }
    return total;
  }

  public String printList() {
    String list = "";
    for (Item i : this.items) {
      String line = "";
      line = line.concat(Integer.toString(i.getCount()));
      line = line.concat(" ");
      line = line.concat(i.getName());
      line = line.concat(": ");
      line = line.concat(Double.toString(i.getTotalPrice()));
      line = line.concat("\n");
      list = list.concat(line);
    }
    list = list.concat("Sales Taxes: ");
    list = list.concat(Double.toString(this.totalSalesTax()));
    list = list.concat("\nTotal: ");
    list = list.concat(Double.toString(this.totalCost()));
    list = list.concat("\n");
    return list;
  }
}