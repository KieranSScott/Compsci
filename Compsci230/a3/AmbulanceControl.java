package a3;

import java.util.ArrayList;
import java.util.Collections;

public class AmbulanceControl {
	AmbulanceTableModel ambulances;
	PatientTableModel patients;
	
	ArrayList<Patient> patientArray;
	
	public AmbulanceControl(DataModel data) {
		ambulances = data.getAmbulances();
		patients = data.getPatients();
		patientArray = new ArrayList<Patient>();
		for(int i = 0; i < patients.getRowCount(); i++) {
			Patient patient = patients.getByID((Integer) patients.getValueAt(i, 0));
			patientArray.add(patient);
		centralControl(8);
		}
	}
	public void centralControl(int row) {
		Ambulance ambulance = ambulances.getByID((String) ambulances.getValueAt(row, 0));
		String status = ambulance.getStatus();
		switch (status) {
			case "At Station":
				
				ArrayList<Double> patientDistance = new ArrayList<Double>();
				ArrayList<Patient> pendingPatients = new ArrayList<Patient>();
				for(Patient p: patientArray) {	
					
					int patientPending = p.getStatus().compareTo("Pending");
					if(patientPending == 0) { 
						patientDistance.add(distance(ambulance, p));
						pendingPatients.add(p);
						
					}		
				}
				for(double d: patientDistance) {
					System.out.println(d);
				}
				Patient patientToAssign = pendingPatients.get(patientDistance.indexOf(Collections.min(patientDistance)));
				System.out.println(patientToAssign.getStatus());
				
				
			case "Responding":
				
			case "At Scene":
			
			case "Transporting":
				
			case "At Destination":
				
			case "Returning":	
			
		}
	}
	
	public double distance(Ambulance a, Patient p) {
		double distance = Math.sqrt((a.getX() - p.getX()) + (a.getY() - p.getY()));
		
		return distance;
	}
	
}
