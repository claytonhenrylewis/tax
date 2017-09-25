import java.util.ArrayList;
import java.lang.Math;

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
    return (Math.round(total * 100.0) / 100.0);
  }

  private double totalCost() {
    double total = 0.0;
    for (Item item : this.items) {
      total += item.getTotalPrice();
    }
    return (Math.round(total * 100.0) / 100.0);
  }

  public String printList() {
    String list = "";
    for (Item i : this.items) {
      String line = String.format("%d %s: %.2f\n", i.getCount(), i.getName(), i.getTotalPrice());
      list = list.concat(line);
    }
    list = list.concat(String.format("Sales Tax: %.2f\n", this.totalSalesTax()));
    list = list.concat(String.format("Total: %.2f\n", this.totalCost()));
    return list;
  }
}