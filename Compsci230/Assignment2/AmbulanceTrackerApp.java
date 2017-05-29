import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class AmbulanceTrackerApp {
  public static void main(String[] args) {
    Patient patient = new Patient();
    Ambulance ambulance = new Ambulance();
    MainWindow window = new MainWindow(patient, ambulance);
    window.setVisible(true);
  }
}
