package a3;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DataModel {
	private PatientTableModel _patients = new PatientTableModel();
	private AmbulanceTableModel _ambulances = new AmbulanceTableModel();
	
	public PatientTableModel getPatients() {
		return _patients;
	}
	
	public AmbulanceTableModel getAmbulances() {
		return _ambulances;
	}
	
	public void Save() {
		try {
			Path patientPath = Paths.get("patients.csv");
			Files.deleteIfExists(patientPath);
			Files.write(patientPath, _patients.save(), StandardOpenOption.CREATE);

			Path ambulancePath = Paths.get("ambulances.csv");
			Files.deleteIfExists(ambulancePath);
			Files.write(ambulancePath, _ambulances.save(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DataModel Load() {
		DataModel data = new DataModel();

		PatientTableModel patients = data.getPatients();
		try {
			Path patientPath = Paths.get("patients.csv");
			String s = patientPath.toAbsolutePath().toString();
			System.out.println("Looking for patients.csv in " + s);			
			List<String> lines = Files.readAllLines(patientPath);
			for (int loop = 1; loop < lines.size(); loop++) {
				String line = lines.get(loop);
				if ((line != null) && (line != "")) {
					patients.parseAndAdd(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		AmbulanceTableModel ambulances = data.getAmbulances();
		try {
			Path ambulancesPath = Paths.get("ambulances.csv");
			String s = ambulancesPath.toAbsolutePath().toString();
			System.out.println("Looking for patients.csv in " + s);			
			List<String> lines = Files.readAllLines(ambulancesPath);
			for (int loop = 1; loop < lines.size(); loop++) {
				String line = lines.get(loop);
				if ((line != null) && (line != "")) {
					ambulances.parseAndAdd(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
