import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class Ambulance {
  Object[][] data;
  String[] colNames = {"ID", "Location", "Status", "Patient"};
  String filename = "ambulances.csv";

  public Ambulance() {
    load(filename);
  }
  private void load(String csv) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File (csv));

      scanner.nextLine();
      ArrayList<String[]> temp = new ArrayList<>();
      for (int row=0;scanner.hasNext(); row++) {
        String[] line = scanner.nextLine().split(",");
        temp.add(line);
      }
      data = new Object[temp.size()][];
      for (int row= 0 ; row < temp.size(); row++){
        data[row] = new String[temp.get(row).length - 1];
        Object[] tempy = temp.get(row);
        //data[row] = temp.get(row);
        //System.out.println(data[row])
        data[row][0] = (String)tempy[0];
        data[row][1] = ((String)tempy[1] + "," + (String)tempy[2]);
        data[row][2] = (String)tempy[3];
        data[row][3] = (String)tempy[4];

      }
    }
    catch (FileNotFoundException e) {
        System.out.println("File not found");
        System.exit(0);
      }
    finally {
        if(scanner != null) {
          scanner.close();
        }
      }
  }
}
