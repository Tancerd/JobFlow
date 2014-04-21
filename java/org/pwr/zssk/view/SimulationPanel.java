package org.pwr.zssk.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel {

	public SimulationPanel() {
		super();
		setSize(800,600);

	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.white);
		g.clearRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
	}
}
