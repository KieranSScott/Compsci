package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientDetails extends JFrame{
  String title = "PatientDetails";
  JPanel patientDetails;
  public PatientDetails() {
    setTitle(title);
    setSize(300, 430);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    getContentPane().add(patientDetails);
  }
}
