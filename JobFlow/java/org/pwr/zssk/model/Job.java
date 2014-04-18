package org.pwr.zssk.model;

import java.util.List;

import org.pwr.zssk.support.Order;

public class Job implements OrderComponent {

	private int id;

	private List<Order> orderList;
	private int timeOfNextAction = 0;
	private int orderCounter = 0;

	@Override
	public int getTimeOfNextAction() {
		return timeOfNextAction;
	}

	@Override
	public void setNextAction() {
		orderCounter++;
	}

	public int getNextMachine() {
		if (orderCounter == orderList.size())
			return -1;
		return orderList.get(orderCounter).getMachine();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimeForMachine(int machineId) {
		Order order = new Order();
		order.setMachine(machineId);
		int index = orderList.indexOf(order);
		return orderList.get(index).getTime();
	}

	public void setTimeOfNextAction(int timeOfNextAction) {
		this.timeOfNextAction = timeOfNextAction;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
