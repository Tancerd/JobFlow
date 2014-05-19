package org.pwr.zssk.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel implements MouseMotionListener{

	int rectSizeTick=5;
	int rectSize=rectSizeTick;
	int machineNumber=0;
	int jobNumber=0;
	int maxLength=0;
	int offsetX=100;
	int offsetY=50;
	
	PopUpFrame popUpFrame; 
	int [] machineWaiting;
	int [] machineJobStart;
	int [] machinePrepare;
	int [] machineWhichOneJob;
	int [] machinePrepareAfterJob;
	int [] jobWaiting;
	int [] jobPrepare;
	String [] logArray= new String[0];
	ArrayList<Rectangle> collisionMap= new ArrayList<Rectangle>();
	public void computeMaxLength()
	{
		int maxTime=0;
		for(int n=0;n<logArray.length;n++)
		{
			System.out.println(logArray[n]);
			
			String []tempArray=logArray[n].split(" ");
			
			int time= Integer.parseInt(tempArray[1]);
			maxTime=Math.max(maxTime, time);
		}
		maxLength=maxTime;
	}
	public void simulationInit(int tmachineNumber, int tjobNumber, String[] array)
	{
		collisionMap.clear();
		logArray=array;
		machineNumber=tmachineNumber;
		jobNumber=tjobNumber;
		machineWaiting= new int[machineNumber];
		machineJobStart= new int[machineNumber];
		machinePrepare= new int[machineNumber];
		machineWhichOneJob= new int[machineNumber];
		machinePrepareAfterJob= new int[machineNumber];
		jobPrepare= new int[jobNumber];
		jobWaiting= new int[jobNumber];
		for(int n=0;n< machineNumber;n++)
		{
			machineWaiting[n]=0;
			machineJobStart[n]=-1;
			machinePrepare[n]=-1;
			machineWhichOneJob[n]=-1;
			machinePrepareAfterJob[n]=-1;
		}
		for(int n=0;n<jobNumber;n++)
		{
			jobWaiting[n]=0;
			jobPrepare[n]=0;
		}
		computeMaxLength();
		updateSize();
		repaint();
	}
	public void setRectSizeByTick(int tick)
	{
		
		rectSize=rectSizeTick*tick;
		computeMaxLength();
		updateSize();
		repaint();
	}
	public SimulationPanel() {
		super();
		
		
		
		setPreferredSize(new Dimension(maxLength*rectSize+offsetX*2,offsetY+machineNumber*rectSize+rectSize*2+offsetY+jobNumber*rectSize+offsetY*2));
		addMouseMotionListener(this);
	}
	public void updateSize()
	{
		setPreferredSize(new Dimension(maxLength*rectSize+offsetX*2,  offsetY+machineNumber*rectSize+rectSize*2+offsetY+jobNumber*rectSize+offsetY*2));
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		collisionMap.clear();
		g.setColor(Color.white);
		for(int n=0;n<jobNumber;n++)
		{
			jobWaiting[n]=0;
			jobPrepare[n]=-1;
		}
		for(int n=0;n< machineNumber;n++)
		{
			machineWaiting[n]=0;
			machineJobStart[n]=-1;
			machinePrepare[n]=-1;
			machineWhichOneJob[n]=-1;
			machinePrepareAfterJob[n]=-1;
		}
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		drawSimulation(g,"Machine");
		drawWireMesh(g,"Machine");
		for(int n=0;n< machineNumber;n++)
		{
			machineWaiting[n]=-1;
			machineJobStart[n]=-1;
			machinePrepare[n]=-1;
			machineWhichOneJob[n]=-1;
			machinePrepareAfterJob[n]=-1;
		}
		
		fillJobWaiting(g);
		drawSimulation(g,"Job");
		drawWireMesh(g,"Job");
	}
	private void drawWireMesh(Graphics g, String str)
	{
		int offsetYMachineJob=0;
		int lineNumber=machineNumber;
		if(str.equals("Job"))
		{
			offsetYMachineJob=offsetY+machineNumber*rectSize+rectSize*2;
			lineNumber=jobNumber;
		}
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, rectSize/2)); 
		Graphics2D g2d= (Graphics2D) g;
		g2d.setStroke(new BasicStroke(1,0,1,1));
		 g2d.setColor(Color.black);
		
		for(int n=0;n<lineNumber+1;n++)
			g2d.drawLine(offsetX, offsetYMachineJob+offsetY+n*rectSize, offsetX+maxLength*rectSize, offsetYMachineJob+offsetY+n*rectSize);
		for(int n=0;n<maxLength+1;n++)
			g2d.drawLine(offsetX+n*rectSize, offsetYMachineJob+offsetY, offsetX+n*rectSize, offsetYMachineJob+offsetY+lineNumber*rectSize);
		for(int n=0;n<lineNumber;n++)
			g2d.drawString(str+(n+1), offsetX-rectSize*3, offsetYMachineJob+offsetY+n*rectSize+rectSize*2/3);
		for(int n=0;n<maxLength;n++)
			g2d.drawString(""+n, offsetX+n*rectSize, offsetYMachineJob+offsetY-rectSize/2);
	}
	private void drawSimulation(Graphics g, String str)
	{
		int offsetYMachineJob=0;
		if(str.equals("Job"))
		{
			offsetYMachineJob=offsetY+machineNumber*rectSize+rectSize*2;
		}
		Graphics2D g2d= (Graphics2D) g;
		for(int n=0;n<logArray.length;n++)
		{
			System.out.println(logArray[n]);
			
			String []tempArray=logArray[n].split(" ");
			
			int time= Integer.parseInt(tempArray[1]);
			if(tempArray[2].equals("Job") && (str.equals("Machine")))
			{
				int jobID= Integer.parseInt(tempArray[3].replace(":", ""));
				if(jobPrepare[jobID-1]==-1)
					jobPrepare[jobID-1]=time;
			}
			if(tempArray[2].equals("Machine"))
			{
				int machineID= Integer.parseInt(tempArray[3].replace(":", ""));
				//cokolwiek innego
				if(tempArray[4].equals("START"))
				{
					if(tempArray[5].equals("JOB"))
					{
						//zaczyna robote
						int jobID= Integer.parseInt(tempArray[6]);
						machineWhichOneJob[machineID-1]=jobID;
						machineJobStart[machineID-1]=time;
						if(str.equals("Machine"))
						{
							jobWaiting[jobID-1]=time;
						}
					}
					else if(tempArray[5].equals("WAITING"))
					{
						//zaczyna czekac
						machineWaiting[machineID-1]=time;
					}
					else if(tempArray[5].equals("PREPARE"))
					{
						//zaczyna sie przezbrajac
						machinePrepare[machineID-1]=time;
					}
				}
				if(tempArray[4].equals("END"))
				{
					if(tempArray[5].equals("JOB") && machineJobStart[machineID-1]!=-1)
					{
						//konczy robote, rysuje
						drawSimRect(g,machineJobStart[machineID-1],str.equals("Machine")?machineID-1:machineWhichOneJob[machineID-1]-1,time-machineJobStart[machineID-1],"JOB",str);
						drawSimString(g,machineJobStart[machineID-1],str.equals("Machine")?machineID-1:machineWhichOneJob[machineID-1]-1,str.equals("Machine")?"Job "+machineWhichOneJob[machineID-1]:"Machine "+(machineID),str);
						Rectangle tempRect= new Rectangle();
						tempRect.x=offsetX+(machineJobStart[machineID-1])*rectSize;
						tempRect.y=offsetYMachineJob+offsetY+(str.equals("Machine")?machineID-1:machineWhichOneJob[machineID-1]-1)*rectSize;
						tempRect.width= (time-machineJobStart[machineID-1])*rectSize;
						tempRect.height= rectSize;
						collisionMap.add(tempRect);
						machinePrepareAfterJob[machineID-1]=machineWhichOneJob[machineID-1];
					}
					else if(tempArray[5].equals("WAITING") && machineWaiting[machineID-1]!=-1 && str.equals("Machine"))
					{
						//konczy czekanie, rysuje
						drawSimRect(g,machineWaiting[machineID-1],machineID-1,time-machineWaiting[machineID-1],"WAIT",str);
						Rectangle tempRect= new Rectangle();
						tempRect.x=offsetX+(machineWaiting[machineID-1])*rectSize;
						tempRect.y=offsetYMachineJob+offsetY+(machineID-1)*rectSize;
						tempRect.width= (time-machineWaiting[machineID-1])*rectSize;
						tempRect.height= rectSize;
						collisionMap.add(tempRect);
					}
					else if(tempArray[5].equals("PREPARE") && machinePrepare[machineID-1]!=-1 && str.equals("Machine"))

					{
						//konczy przezbrajanie, rysuje
						drawSimRect(g,machinePrepare[machineID-1],machineID-1,time-machinePrepare[machineID-1],"PREPARE",str);
						Rectangle tempRect= new Rectangle();
						tempRect.x=offsetX+(machinePrepare[machineID-1])*rectSize;
						tempRect.y=offsetYMachineJob+offsetY+(machineID-1)*rectSize;
						tempRect.width= (time-machinePrepare[machineID-1])*rectSize;
						tempRect.height= rectSize;
						collisionMap.add(tempRect);
						if(time-machinePrepare[machineID-1]!=0)
						drawSimPrepareAdnotation(g, machinePrepare[machineID-1],machineID-1,machinePrepareAfterJob[machineID-1],str);
					}
					
				}
			}
		}
			
	}
	private void drawSimRect(Graphics g, int x, int y, int width, String type,String str)
	{
		int offsetYMachineJob=0;
		if(str.equals("Job"))
		{
			offsetYMachineJob=offsetY+machineNumber*rectSize+rectSize*2;
		}
		Graphics2D g2d= (Graphics2D) g;
		if(type.equals("WAIT"))
			g2d.setColor(Color.black);
		if(type.equals("PREPARE"))
			g2d.setColor(Color.gray);
		if(type.equals("JOB"))
			g2d.setColor(Color.yellow);
		if(type.equals("JOB_PREPARE"))
			g2d.setColor(Color.white);
		g2d.fillRect(offsetX+x*rectSize, offsetYMachineJob+offsetY+y*rectSize, width*rectSize, rectSize);
	}
	private void fillJobWaiting(Graphics g)
	{
		for(int n=0;n<jobNumber;n++)
		{
			drawSimRect(g,0,n,jobWaiting[n],"WAIT","Job");	
			drawSimRect(g,0,n,jobPrepare[n],"JOB_PREPARE","Job");	
		}
	}
	private void drawSimString(Graphics g, int x, int y, String stri, String str)
	{
		int offsetYMachineJob=0;
		if(str.equals("Job"))
		{
			offsetYMachineJob=offsetY+machineNumber*rectSize+rectSize*2;
		}
		Graphics2D g2d= (Graphics2D) g;
			g2d.setColor(Color.black);

			g.setFont(new Font("TimesRoman", Font.PLAIN, rectSize*1/3)); 
		g2d.drawString(stri, offsetX+x*rectSize, offsetYMachineJob+offsetY+y*rectSize+rectSize*1/3);
	}
	
	private void drawSimPrepareAdnotation(Graphics g, int x, int y, int previousJobID, String str)
	{
		int offsetYMachineJob=0;
		if(str.equals("Job"))
		{
			offsetYMachineJob=offsetY+machineNumber*rectSize+rectSize*2;
		}
		Graphics2D g2d= (Graphics2D) g;
			g2d.setColor(Color.black);
			
			g2d.fillRect(offsetX+x*rectSize+rectSize*2/5, offsetYMachineJob+offsetY+y*rectSize+rectSize*2/5, rectSize*1/5, rectSize*1/5);
			g2d.drawLine(offsetX+x*rectSize+rectSize*2/5,offsetYMachineJob+ offsetY+y*rectSize+rectSize*2/5, offsetX+x*rectSize-rectSize*3, offsetYMachineJob+offsetY+y*rectSize+rectSize*(machineNumber+1));
			g2d.drawLine(offsetX+x*rectSize-rectSize*3, offsetYMachineJob+offsetY+y*rectSize+rectSize*(machineNumber+1), offsetX+x*rectSize, offsetYMachineJob+offsetY+y*rectSize+rectSize*(machineNumber+1));
			g.setFont(new Font("TimesRoman", Font.PLAIN, rectSize*1/3)); 
		g2d.drawString("P.A.J."+previousJobID, offsetX+x*rectSize-rectSize*3, offsetYMachineJob+offsetY+y*rectSize+rectSize*(machineNumber+1));
	}
	public void drawSimulationFromLog(String [] tlogArr)
	{
		logArray=tlogArr;
		repaint();
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int czyIJaki=collision(arg0.getX(), arg0.getY());
		if(czyIJaki!=-1)
		{
			System.out.println(czyIJaki);
			if(popUpFrame==null)
			{
				popUpFrame= new PopUpFrame();
				popUpFrame.setLocation(arg0.getXOnScreen()-50, arg0.getYOnScreen()-100);
				popUpFrame.setText("Czas trwania: "+czyIJaki);
			}
			else
			{
				popUpFrame.setLocation(arg0.getXOnScreen()-50, arg0.getYOnScreen()-100);
				popUpFrame.setText("Czas trwania: "+czyIJaki);
			}
		}
		else
		{
			if(popUpFrame!=null)
			{
				popUpFrame.dispose();
				popUpFrame= null;
			}
		}
	}
	
	private int collision(int kx, int ky)
	{
		for( int n=0;n<collisionMap.size();n++)
			if(collisionMap.get(n).contains(kx, ky))
				return (collisionMap.get(n).width)/rectSize;
		return -1;
	}
}
