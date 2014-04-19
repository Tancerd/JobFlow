package org.pwr.zssk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.pwr.zssk.logic.JobFlowAlgorithm;
import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.Machine;
import org.pwr.zssk.model.rule.FIFO;
import org.pwr.zssk.support.Order;
import org.pwr.zssk.support.Prepare;

public class AlgorithmTest {
	private Job job1;
	private Job job2;
	
	private Machine machine1;
	private Machine machine2;
	private Machine machine3;
	
	private JobFlowAlgorithm jobFlowAlgorithm;
	
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
		
		job2 = new Job();
		job2.setOrderList(orderList);
		job2.setId(2);
		
		machine1 = new Machine();
		machine1.setId(1);
		machine1.applyRule(FIFO.class);
		
		machine2 = new Machine();
		machine2.setId(2);
		machine2.applyRule(FIFO.class);
		
		machine3 = new Machine();
		machine3.setId(3);
		machine3.applyRule(FIFO.class);
		
		jobFlowAlgorithm = new JobFlowAlgorithm();
		List<Job> jobList = new ArrayList<Job>();
		jobList.add(job1);
		jobList.add(job2);
		jobFlowAlgorithm.setJobList(jobList);
		List<Machine> machineList = new ArrayList<Machine>();
		machineList.add(machine1);
		machineList.add(machine2);
		machineList.add(machine3);
		jobFlowAlgorithm.setMachineList(machineList);
		
	}
	@Test
	public void shouldWork() throws Exception {
		List<Prepare> prepareList = new ArrayList<Prepare>(10);
		Prepare prepare1 = new Prepare(1, 4);
		Prepare prepare2 = new Prepare(2, 6);
		prepareList.add(prepare1);
		prepareList.add(prepare2);
		
		machine1.setPrepareList(prepareList);
		machine2.setPrepareList(prepareList);
		machine3.setPrepareList(prepareList);
		
		int result = jobFlowAlgorithm.calculate();
		assertEquals(43, result);
	}
}
