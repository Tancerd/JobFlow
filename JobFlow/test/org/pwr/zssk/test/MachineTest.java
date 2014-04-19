package org.pwr.zssk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.Machine;
import org.pwr.zssk.model.rule.FIFO;
import org.pwr.zssk.support.MachineStatus;
import org.pwr.zssk.support.Order;
import org.pwr.zssk.support.Prepare;

public class MachineTest {

	private Job job1;
	private Job job2;
	
	private Machine machine;
	
	@Before
	public void before() {
		Order order1 = new Order(10, 1);
		Order order2 = new Order(5, 3);
		Order order3 = new Order(8, 2);
		
		List<Order> orderList;
		orderList = new ArrayList<Order>(10);
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		
		job1 = new Job();
		job1.setOrderList(orderList);
		job1.setId(1);
		
		machine = new Machine();
		machine.setId(1);
		
		
		List<Prepare> prepareList = new ArrayList<Prepare>(10);
		Prepare prepare = new Prepare(1, 4);
		prepareList.add(prepare);
		
		machine.setPrepareList(prepareList);
		
	}
	
	@Test
	public void shouldWork() throws Exception {
		machine.applyRule(FIFO.class);
		
		assertEquals(MachineStatus.WAITING, machine.getStatus());
		assertEquals(0, machine.getTimeOfNextAction());
		
		machine.addNewJob(job1);
		machine.setNextAction();
		assertEquals(MachineStatus.JOB, machine.getStatus());
		assertEquals(10, machine.getTimeOfNextAction());
		
		machine.setNextAction();
		assertEquals(MachineStatus.PREPARE, machine.getStatus());
		assertEquals(14, machine.getTimeOfNextAction());
		
		machine.setNextAction();
		assertEquals(MachineStatus.WAITING, machine.getStatus());
	}
}
