package org.pwr.zssk.model;

public class Order {
	
	private int time;
	private int machine;
	
	public Order() { }
	
	public Order(int time, int machine) {
		super();
		this.time = time;
		this.machine = machine;
	}

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getMachine() {
		return machine;
	}
	public void setMachine(int machine) {
		this.machine = machine;
	}
	
	
}
