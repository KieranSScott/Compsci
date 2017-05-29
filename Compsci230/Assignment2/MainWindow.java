import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainWindow extends JFrame{
  String title = "AmbulanceTrackerApp";

  JPanel majorPanel;


  //Main Window Component init
  JLabel header;
  JButton patients, ambulances, exit;
  JPanel mainWindow;

  //Patient List Component init
  JPanel patientList;
  JTable tablePatient;
  JScrollPane scrollPanePatient;
  JButton addNewPatient, backPatient;
  JLabel headerPatients;

  //Ambulance List Component init
  JPanel ambulanceList;
  JTable tableAmbulance;
  JScrollPane scrollPaneAmbulance;
  JButton addNewAmbulance, backAmbulance;
  JLabel headerAmbulance;

  //Patient Details Component init
  JPanel patientDetails;

  //Ambulance Details Component init
  JPanel ambulanceDetails;

  public MainWindow(Patient patient, Ambulance ambulance) {

    //Main Window
    setTitle(title);
    setSize(600, 650);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainWindow = new JPanel();
    header = new JLabel("Ambulance Tracker");
    patients = new JButton("Patients");
    ambulances = new JButton("Ambulances");
    exit = new JButton("Exit");

    mainWindow.setLayout(new BoxLayout(mainWindow,BoxLayout.Y_AXIS));
    header.setAlignmentX(Component.CENTER_ALIGNMENT);
    patients.setAlignmentX(Component.CENTER_ALIGNMENT);
    ambulances.setAlignmentX(Component.CENTER_ALIGNMENT);
    exit.setAlignmentX(Component.CENTER_ALIGNMENT);

    header.setFont(new Font("Arial", Font.BOLD, 22));
    patients.setFont(new Font("Arial", Font.BOLD, 24));
    ambulances.setFont(new Font("Arial", Font.BOLD, 24));
    exit.setFont(new Font("Arial", Font.BOLD, 24));

    patients.setMaximumSize(new Dimension(250, 110));
    ambulances.setMaximumSize(new Dimension(250, 110));
    exit.setMaximumSize(new Dimension(250, 110));
    header.setMaximumSize(new Dimension(250, 50));

    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        exitButtonAction(e);
      }
    });

    patients.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        patientsButtonAction(e);
      }
    });

    ambulances.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ambulancesButtonAction(e);
      }
    });

    mainWindow.add(header);
    mainWindow.add(patients);
    mainWindow.add(ambulances);
    mainWindow.add(exit);

    getContentPane().add(mainWindow);
    getContentPane().setVisible(true);
    mainWindow.setVisible(true);

    //Patient List Window
    setSize(600, 650);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    patientList = new JPanel();
    headerPatients = new JLabel("Patients", JLabel.CENTER);
    tablePatient = new JTable(patient.data, patient.colNames);
    scrollPanePatient = new JScrollPane(tablePatient);
    addNewPatient = new JButton("Add New");
    backPatient = new JButton("Back");

    headerPatients.setFont(new Font("Arial", Font.BOLD, 22));
    backPatient.setFont(new Font("Arial", Font.BOLD, 22));
    addNewPatient.setFont(new Font("Arial", Font.BOLD, 22));

    tablePatient.setMaximumSize(new Dimension(600, 500));
    backPatient.setMaximumSize(new Dimension(600, 75));
    addNewPatient.setMaximumSize(new Dimension(600, 75));

    patientList.setLayout(new BoxLayout(patientList, BoxLayout.Y_AXIS));
    headerPatients.setAlignmentX(Component.CENTER_ALIGNMENT);
    tablePatient.setAlignmentX(Component.CENTER_ALIGNMENT);
    backPatient.setAlignmentX(Component.CENTER_ALIGNMENT);
    addNewPatient.setAlignmentX(Component.CENTER_ALIGNMENT);

    tablePatient.setPreferredScrollableViewportSize(new Dimension(600, 400));
    tablePatient.setFillsViewportHeight(true);

    backPatient.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backPatientButtonAction(e);
      }
    });

    addNewPatient.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addNewPatientButtonAction(e);
      }
    });

    patientList.add(headerPatients);
    patientList.add(scrollPanePatient);
    patientList.add(addNewPatient);
    patientList.add(backPatient);

    //Ambulance List Window
    ambulanceList = new JPanel();
    headerAmbulance = new JLabel("Ambulances", JLabel.CENTER);
    tableAmbulance = new JTable(ambulance.data, ambulance.colNames);
    scrollPaneAmbulance = new JScrollPane(tableAmbulance);
    addNewAmbulance = new JButton("Add New");
    backAmbulance = new JButton("Back");

    headerAmbulance.setFont(new Font("Arial", Font.BOLD, 22));
    backAmbulance.setFont(new Font("Arial", Font.BOLD, 22));
    addNewAmbulance.setFont(new Font("Arial", Font.BOLD, 22));

    tableAmbulance.setMaximumSize(new Dimension(600, 500));
    backAmbulance.setMaximumSize(new Dimension(600, 75));
    addNewAmbulance.setMaximumSize(new Dimension(600, 75));

    ambulanceList.setLayout(new BoxLayout(ambulanceList, BoxLayout.Y_AXIS));
    headerAmbulance.setAlignmentX(Component.CENTER_ALIGNMENT);
    tableAmbulance.setAlignmentX(Component.CENTER_ALIGNMENT);
    backAmbulance.setAlignmentX(Component.CENTER_ALIGNMENT);
    addNewAmbulance.setAlignmentX(Component.CENTER_ALIGNMENT);

    tableAmbulance.setPreferredScrollableViewportSize(new Dimension(600, 400));
    tableAmbulance.setFillsViewportHeight(true);

    backAmbulance.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backAmbulanceButtonAction(e);
      }
    });

    addNewAmbulance.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addNewAmbulanceButtonAction(e);
      }
    });

    ambulanceList.add(headerAmbulance);
    ambulanceList.add(scrollPaneAmbulance);
    ambulanceList.add(addNewAmbulance);
    ambulanceList.add(backAmbulance);


    //Patient Details Window
    patientDetails = new JPanel();

    //Ambulance Details Window
    ambulanceDetails = new JPanel();


  }
  //Main Window buttons
  private void exitButtonAction(ActionEvent e) {
    System.exit(0);
  }
  private void patientsButtonAction(ActionEvent e) {
    mainWindow.setVisible(false);
    getContentPane().add(patientList);
    int countPatients;
    countPatients++;
    if (countPatients > 0) {
      patientList.setVisible(true);
    }

  }
  private void ambulancesButtonAction(ActionEvent e) {
    mainWindow.setVisible(false);
    getContentPane().add(ambulanceList);
    int countAmbulances;
    countAmbulances++;
    if (countAmbulances > 0) {
      ambulanceList.setVisible(true);
    }
  }

  //Patient List buttons
  private void backPatientButtonAction(ActionEvent e) {
    patientList.setVisible(false);
    mainWindow.setVisible(true);
  }
  private void addNewPatientButtonAction(ActionEvent e) {
    patientList.setVisible(false);
    getContentPane().add(patientDetails);
    patientDetails.setVisible(true);
  }

  //Ambulance List buttons
  private void backAmbulanceButtonAction(ActionEvent e) {
    patientList.setVisible(false);
    mainWindow.setVisible(true);
  }
  private void addNewAmbulanceButtonAction(ActionEvent e) {
    ambulanceList.setVisible(false);
    ambulanceDetails.setVisible(true);
  }
}
