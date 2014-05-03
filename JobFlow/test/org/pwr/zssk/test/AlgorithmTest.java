package org.pwr.zssk.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.pwr.zssk.logic.JobFlowAlgorithm;
import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.Machine;
import org.pwr.zssk.model.rule.EDD;
import org.pwr.zssk.model.rule.FIFO;
import org.pwr.zssk.model.rule.LIFO;
import org.pwr.zssk.model.rule.LPT;
import org.pwr.zssk.model.rule.LWR;
import org.pwr.zssk.model.rule.SPT;
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
		
		
		machine2 = new Machine();
		machine2.setId(2);
		
		machine3 = new Machine();
		machine3.setId(3);
		
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
		
		List<Prepare> prepareList = new ArrayList<Prepare>(10);
		Prepare prepare1 = new Prepare(1, 4);
		Prepare prepare2 = new Prepare(2, 6);
		prepareList.add(prepare1);
		prepareList.add(prepare2);
		
		machine1.setPrepareList(prepareList);
		machine2.setPrepareList(prepareList);
		machine3.setPrepareList(prepareList);
		
	}
	@Test
	public void shouldWorkWhenFIFO() throws Exception {
		
		machine1.applyRule(FIFO.class);
		machine2.applyRule(FIFO.class);
		machine3.applyRule(FIFO.class);
		
		int result = jobFlowAlgorithm.calculate();
		assertEquals(43, result);
	}
	
	@Test
	public void shouldWorkWhenLIFO() throws Exception {
		
		machine1.applyRule(LIFO.class);
		machine2.applyRule(LIFO.class);
		machine3.applyRule(LIFO.class);
		
		int result = jobFlowAlgorithm.calculate();
		assertEquals(43, result);
	}
	
	@Test
	public void shouldWorkWhenEDD() throws Exception {
		
		machine1.applyRule(EDD.class);
		machine2.applyRule(EDD.class);
		machine3.applyRule(EDD.class);
		
		int result = jobFlowAlgorithm.calculate();
		assertEquals(43, result);
	}
	
	@Test
	public void shouldWorkWhenLPT() throws Exception {
		
		machine1.applyRule(LPT.class);
		machine2.applyRule(LPT.class);
		machine3.applyRule(LPT.class);
		
		int result = jobFlowAlgorithm.calculate();
		assertEquals(43, result);
	}
	
	@Test
	public void shouldWorkWhenSPT() throws Exception {
		
		machine1.applyRule(SPT.class);
		machine2.applyRule(SPT.class);
		machine3.applyRule(SPT.class);
		
		int result = jobFlowAlgorithm.calculate();
		assertEquals(43, result);
	}
	
	@Test
	public void shouldWorkWhenLWR() throws Exception {
		
		machine1.applyRule(LWR.class);
		machine2.applyRule(LWR.class);
		machine3.applyRule(LWR.class);
		
		int result = jobFlowAlgorithm.calculate();
		assertEquals(43, result);
	}
}
