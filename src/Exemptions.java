import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

//Exemptions class holds a list of exempt items.
//Items are checked against this list when computing tax

public class Exemptions {
  private ArrayList<String> exemptions;
  
  //Create list of exemptions from a text file containing all exemptions
  public Exemptions(String fileName) {
    exemptions = new ArrayList<String>();
    In exemptionsIn = new In(fileName);
    String line = exemptionsIn.readLine();
    while (line != null) {
      exemptions.add(line);
      line = exemptionsIn.readLine();
    }
  }

  //Access exemptions
  public ArrayList<String> getExemptions() {
    return this.exemptions;
  }
}