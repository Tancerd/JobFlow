package org.pwr.zssk.dataaccess;

public class DataStore {

	private int machineNumber=0;
	private int jobNumber=0;
	private Rule[] rules=new Rule[0];
	private Integer[][] prepareTimes=new Integer[0][0];
	private Integer[] arriveTimes=new Integer[0];
	private String[][] orders= new String[0][0];

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
