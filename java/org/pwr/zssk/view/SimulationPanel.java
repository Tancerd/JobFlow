package org.pwr.zssk.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel {

	int rectSizeTick=5;
	int rectSize=rectSizeTick;
	int machineNumber=5;
	int maxLength=100;
	int offsetX=50;
	int offsetY=50;
	String [] logArray= new String[0];
	
	public void setRectSizeByTick(int tick)
	{
		
		rectSize=rectSizeTick*tick;
	}
	public SimulationPanel() {
		super();
		setPreferredSize(new Dimension(maxLength*rectSize+offsetX*2,machineNumber*rectSize+offsetY*2));

	}
	public void updateSize()
	{
		setPreferredSize(new Dimension(maxLength*rectSize+offsetX*2,machineNumber*rectSize+offsetY*2));
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		drawSimulation(g);
		drawWireMesh(g);
		
		
	}
	private void drawWireMesh(Graphics g)
	{
		Graphics2D g2d= (Graphics2D) g;
		g2d.setStroke(new BasicStroke(1,0,1,1));
		 g2d.setColor(Color.black);
		for(int n=0;n<machineNumber+1;n++)
			g2d.drawLine(offsetX, offsetY+n*rectSize, offsetX+maxLength*rectSize, offsetY+n*rectSize);
		for(int n=0;n<maxLength+1;n++)
			g2d.drawLine(offsetX+n*rectSize, offsetY, offsetX+n*rectSize, offsetY+machineNumber*rectSize);
	}
	private void drawSimulation(Graphics g)
	{
		drawSimRect(g, 0, 0, 5, "WAIT");
		drawSimRect(g, 3, 1, 3, "PREPARE");
		drawSimRect(g, 6, 3, 17, "JOB");
		Graphics2D g2d= (Graphics2D) g;
		
	}
	private void drawSimRect(Graphics g, int x, int y, int width, String type)
	{
		Graphics2D g2d= (Graphics2D) g;
		if(type.equals("WAIT"))
			g2d.setColor(Color.black);
		if(type.equals("PREPARE"))
			g2d.setColor(Color.gray);
		if(type.equals("JOB"))
			g2d.setColor(Color.yellow);
		
		g2d.fillRect(offsetX+x*rectSize, offsetY+y*rectSize, width*rectSize, rectSize);
	}
	public void drawSimulationFromLog(String [] tlogArr)
	{
		logArray=tlogArr;
		repaint();
		
	}
}
