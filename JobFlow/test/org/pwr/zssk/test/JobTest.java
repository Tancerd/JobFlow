package org.pwr.zssk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.pwr.zssk.model.Job;
import org.pwr.zssk.support.Order;

public class JobTest {

	private Job job;
	private List<Order> orderList;
	
	@Before
	public void before() {
		Order order1 = new Order(10, 1);
		Order order2 = new Order(5, 3);
		Order order3 = new Order(8, 2);
		
		orderList = new ArrayList<Order>(10);
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		
		job = new Job();
		job.setOrderList(orderList);
		
	}
	
	@Test
	public void shouldReturnNextMachineIdInOrder() throws Exception {
		assertEquals(orderList.get(0).getMachine(), job.getNextMachine());
		job.setNextAction();
		assertEquals(orderList.get(1).getMachine(), job.getNextMachine());
		job.setNextAction();
		assertEquals(orderList.get(2).getMachine(), job.getNextMachine());
		job.setNextAction();
		assertEquals(-1, job.getNextMachine());
	}
}
