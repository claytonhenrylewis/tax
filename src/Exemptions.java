import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Exemptions {
  private ArrayList<String> exemptions;
  
  public Exemptions(String fileName) {
    exemptions = new ArrayList<String>();
    In exemptionsIn = new In(fileName);
    String line = exemptionsIn.readLine();
    while (line != null) {
      exemptions.add(line);
      line = exemptionsIn.readLine();
    }
  }

  public void printExemptions() {
    for (String s : exemptions) {
      System.out.println(s);
    }
  }
}