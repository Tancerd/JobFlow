package org.pwr.zssk.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pwr.zssk.dataaccess.DataStore;
import org.pwr.zssk.dataaccess.Facade;

public class GUI {
	
	static GUI gui;
	DataStore dataStore;
	JobFlowFrame jobFlowFrame;
	Facade facade;
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    gui= new GUI();
	   
	}
	
	public GUI()
	{
		
		facade=new Facade();
		dataStore= facade.getDataStore();//new DataStore();
		jobFlowFrame= new JobFlowFrame(facade);
		jobFlowFrame.setDataStore(dataStore);
		jobFlowFrame.setVisible(true);
		
		
	}
}
