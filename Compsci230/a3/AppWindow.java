package a3;

import java.awt.*;
import javax.swing.*;

public abstract class AppWindow {
	private JFrame _window;
	private DataModel _data;
	
	public AppWindow(DataModel data, Boolean isMain) {
		_window = new JFrame("Ambulance Tracking System");
		_window.setSize(400,  400);
		if (isMain) {
			_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		_data = data;
	}
	
	public DataModel getData() {
		return _data;
	}

	protected void setLayout(LayoutManager manager) {
		_window.setLayout(manager);
	}
	
	protected void add(Component comp) {
		_window.add(comp);
	}
	
	protected void add(Component comp, GridBagConstraints c) {
		_window.add(comp, c);
	}
	
	public void hide() {
		_window.setVisible(false);
	}
	
	public void save() {
		_data.Save();
	}
		
	public void show() {
		_window.setVisible(true);
	}
	
	protected void addLabelFor(Component c, String label, int row) {
		JLabel labelToAdd = new JLabel(label);
		labelToAdd.setLabelFor(c);
		GridBagConstraints constraints = Utils.defaultConstraints(0, row);
		this.add(labelToAdd, constraints);
	}
}
