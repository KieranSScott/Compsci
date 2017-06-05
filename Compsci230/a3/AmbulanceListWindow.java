package a3;

import java.awt.*;
import javax.swing.*;

public final class AmbulanceListWindow extends AppWindow {
	public AmbulanceListWindow(DataModel data) {
		super(data, true);
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
		
		JTable table = new JTable(data.getAmbulances());
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		constraints = Utils.defaultConstraints(0, 0);
		constraints.gridwidth = 2;
		constraints.weighty = 6;
		this.add(scrollPane, constraints);
		
		JLabel durationLable = new JLabel("Duration (seconds): ");
		this.add(durationLable);
		
		JTextField durationInput = new JTextField();
		this.add(durationInput);
		
		JButton start = new JButton("Start");
		this.add(start);
		
		JButton stop = new JButton("Stop");
		this.add(stop);
		
	}
}
