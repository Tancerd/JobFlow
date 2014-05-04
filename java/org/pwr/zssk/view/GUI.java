package org.pwr.zssk.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.pwr.zssk.dataaccess.DataStore;

public class GUI {
	
	static GUI gui;
	DataStore dataStore;
	JobFlowFrame jobFlowFrame;
	public static void main(String[] args) {
	    gui= new GUI();
	    
	}
	
	public GUI()
	{
		
		
		dataStore= new DataStore();
		jobFlowFrame= new JobFlowFrame();
		jobFlowFrame.setDataStore(dataStore);
		jobFlowFrame.setVisible(true);
		
		
	}
}
