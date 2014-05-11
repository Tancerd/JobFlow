package org.pwr.zssk.dataaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;

public class DataStore implements Serializable{

	private int machineNumber=0;
	private int jobNumber=0;
	private Rule[] rules=new Rule[0];
	private Integer[][] prepareTimes=new Integer[0][0];
	private Integer[] arriveTimes=new Integer[0];
	private String[][] orders=new String[0][0];

	public void write(String path)
	{
		
		
        ObjectOutputStream outWrite = null;
		try {
			outWrite = new ObjectOutputStream(new FileOutputStream(path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        try {
			outWrite.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DataStore read(String path)
	{
		 ObjectInputStream we = null;
		try {
			we = new ObjectInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         DataStore dS = null;
		try {
			dS = (DataStore)we.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         System.out.println(dS.toString());
         return dS;
	}
	@Override
	public String toString() {
		String str="";
		str+="Job Number: "+jobNumber+'\n';
		str+="Machine Number: "+machineNumber+'\n';
		str+='\n';
		for(int n=0;n<rules.length;n++)
			str+=rules[n]+" ";
		str+='\n';
		for(int n=0;n<prepareTimes.length;n++)
			str+=prepareTimes[n]+" ";
		str+='\n';
		for(int n=0;n<orders.length;n++)
			str+=orders[n]+" ";
		str+='\n';
		
		return super.toString();
	}
	public String[][] getOrders() {
		return orders;
	}

	public void setOrders(String[][] orders) {
		this.orders = orders;
	}

	public int getMachineNumber() {
		return machineNumber;
	}

	public void setMachineNumber(int machineNumber) {
		this.machineNumber = machineNumber;
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Rule[] getRules() {
		return rules;
	}

	public void setRules(Rule[] rules) {
		this.rules = rules;
	}

	public Integer[][] getPrepareTimes() {
		return prepareTimes;
	}

	public void setPrepareTimes(Integer[][] prepareTimes) {
		this.prepareTimes = prepareTimes;
	}

	public Integer[] getArriveTimes() {
		return arriveTimes;
	}

	public void setArriveTimes(Integer[] arriveTimes) {
		this.arriveTimes = arriveTimes;
	}

}
