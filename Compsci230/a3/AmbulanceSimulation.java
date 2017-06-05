package a3;

public class AmbulanceSimulation {

	public static void main(String[] args) {
		DataModel model = DataModel.Load();
		AmbulanceControl control = new AmbulanceControl(model);
		AmbulanceListWindow window = new AmbulanceListWindow(model);
		window.show();
	}

}
