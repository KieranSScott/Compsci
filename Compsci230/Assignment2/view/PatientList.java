package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientList extends JFrame{
  String title = "AmbulanceTrackerApp";

  JPanel patientList;
  JTable table;
  JButton addNew, back;
  JLabel header;

  public PatientList() {
    setTitle(title);
    setSize(600, 650);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    patientList = new JPanel();
    header = new JLabel("Patients", JLabel.CENTER);
    table = new JTable();
    addNew = new JButton("Add New");
    back = new JButton("Back");

    header.setFont(new Font("Arial", Font.BOLD, 22));
    back.setFont(new Font("Arial", Font.BOLD, 22));
    addNew.setFont(new Font("Arial", Font.BOLD, 22));

    table.setMaximumSize(new Dimension(600, 500));
    back.setMaximumSize(new Dimension(600, 75));
    addNew.setMaximumSize(new Dimension(600, 75));

    patientList.setLayout(new BoxLayout(patientList, BoxLayout.Y_AXIS));
    header.setAlignmentX(Component.CENTER_ALIGNMENT);
    table.setAlignmentX(Component.CENTER_ALIGNMENT);
    back.setAlignmentX(Component.CENTER_ALIGNMENT);
    addNew.setAlignmentX(Component.CENTER_ALIGNMENT);

    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backButtonAction(e);
      }
    });

    addNew.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addNewButtonAction(e);
      }
    });

    patientList.add(header);
    patientList.add(table);
    patientList.add(back);
    patientList.add(addNew);


    getContentPane().add(patientList);
  }
  private void backButtonAction(ActionEvent e) {
    MainWindow window = new MainWindow();
    window.show();
    window.setVisible(true);
  }
  private void addNewButtonAction(ActionEvent e) {
    PatientDetails patientDetails = new PatientDetails();
    patientDetails.show();
    patientDetails.setVisible(true);
  }
}
