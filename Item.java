public class Item {
  private int count;
  private String name;
  private double price;
  private boolean exempt;
  private boolean imported;

  public Item(int count, String name, double price) {
    this.count = count;
    this.name = name;
    this.price = price;
  }

  public Item(String lineItem) {
    String[] tokens = lineItem.split(" ");
    int i = 0;
    this.count = Integer.parseInt(tokens[i]);
    i++;
    if (tokens[i].toLowerCase().equals("imported")) {
      this.imported = true;
      i++;
    }
    this.price = Double.parseDouble(tokens[tokens.length - 1]);
    this.name = "";
    for (int j = i; j < tokens.length - 2; j++) {
      this.name.concat(tokens[j]);
    }  
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
}