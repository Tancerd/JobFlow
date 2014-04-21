package org.pwr.zssk.dataaccess;

public class DataStore {

	private int machineNumber;
	private int jobNumber;
	private Rule[] rules;
	private Integer[][] prepareTimes;
	private Integer[] arriveTimes;
	private String[][] orders;

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
