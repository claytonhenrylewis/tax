import java.util.ArrayList;
import java.lang.Math;

//ItemList class contains a list of Item objects.
//Represents a "shopping basket"

public class ItemList {
  private ArrayList<Item> items;

  //Create an ItemList from a list of Items
  public ItemList(ArrayList<Item> items) {
    this.items = items;
  }

  //Create an ItemList from a file listing the items
  public ItemList(String fileName) {
    this.items = new ArrayList<Item>();
    In itemsIn = new In(fileName);
    String line = itemsIn.readLine();
    while (line != null) {
      items.add(new Item(line));
      line = itemsIn.readLine();
    }
  }

  //Add item to the list
  public void addItem(Item i) {
    this.items.add(i);
  }

  //Compute total sales tax for the list
  private double totalSalesTax() {
    double total = 0.0;
    for (Item item : this.items) {
      total += item.getSalesTax();
    }
    return (Math.round(total * 100.0) / 100.0);
  }

  //Compute total cost for the list, including tax
  private double totalCost() {
    double total = 0.0;
    for (Item item : this.items) {
      total += item.getTotalPrice();
    }
    return (Math.round(total * 100.0) / 100.0);
  }

  //Print receipt for the list
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