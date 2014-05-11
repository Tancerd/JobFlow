package org.pwr.zssk.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;
import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.rule.EDD;
import org.pwr.zssk.model.rule.FIFO;
import org.pwr.zssk.model.rule.LIFO;
import org.pwr.zssk.model.rule.LPT;
import org.pwr.zssk.model.rule.LWR;
import org.pwr.zssk.model.rule.SPT;
import org.pwr.zssk.support.Order;

public class RulesTest {

	private PriorityQueue<Job> queue;
	private List<Job> jobList;
	
	@Before
	public void before()
	{
		Job job1 = new Job();
		job1.setArriveTime(10);
		
		Job job2 = new Job();
		job2.setArriveTime(20);
		
		Job job3 = new Job();
		job3.setArriveTime(5);
		
		jobList = new ArrayList<>(10);
		
		jobList.add(job1);
		jobList.add(job2);
		jobList.add(job3);
		
		Order order1 = new Order(10, 1);
		Order order2 = new Order(5, 3);
		Order order3 = new Order(8, 2);
		
		List<Order> orderList;
		orderList = new ArrayList<Order>(10);
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		
		
		
		job1.setOrderList(orderList);
		job1.setId(1);
		
		order1 = new Order(9, 1);
		orderList = new ArrayList<Order>(10);
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		
		job2.setOrderList(orderList);
		job2.setId(2);
		
		order1 = new Order(11, 1);
		orderList = new ArrayList<Order>(10);
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		
		job3.setOrderList(orderList);
		job3.setId(3);
		
	}
	
	@Test
	public void shouldReturnFirstAddedJob() throws Exception {
		Comparator<Job> comparator = new FIFO();
		
		queue = new PriorityQueue<Job>(10, comparator);
		
		queue.add(jobList.get(0));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(1));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(2));
		assertTrue(jobList.get(0) == queue.peek());
	}
	
	@Test
	public void shouldReturnLastAddedJob() throws Exception {
		Comparator<Job> comparator = new LIFO();
		
		queue = new PriorityQueue<Job>(10, comparator);
		
		queue.add(jobList.get(0));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(1));
		assertTrue(jobList.get(1) == queue.peek());
		
		queue.add(jobList.get(2));
		assertTrue(jobList.get(2) == queue.peek());
	}
	
	@Test
	public void shouldReturnJobWithShorterTimeForFirstMachine() throws Exception {
		Comparator<Job> comparator = new SPT();
		
		queue = new PriorityQueue<Job>(10, comparator);
		
		queue.add(jobList.get(0));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(1));
		assertTrue(jobList.get(1) == queue.peek());
		
		queue.add(jobList.get(2));
		assertTrue(jobList.get(1) == queue.peek());
	}
	
	@Test
	public void shouldReturnJobWithShorterTimeForEndHisAllOrders() throws Exception {
		Comparator<Job> comparator = new EDD();
		
		queue = new PriorityQueue<Job>(10, comparator);
		
		queue.add(jobList.get(0));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(1));
		assertTrue(jobList.get(1) == queue.peek());
		
		queue.add(jobList.get(2));
		assertTrue(jobList.get(1) == queue.peek());
	}
	
	@Test
	public void shouldReturnJobWithLongerTimeForFirstMachine() throws Exception {
		Comparator<Job> comparator = new LPT();
		
		queue = new PriorityQueue<Job>(10, comparator);
		
		queue.add(jobList.get(0));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(1));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(2));
		assertTrue(jobList.get(2) == queue.peek());
	}
	
	@Test
	public void shouldReturnJobWithShorterTimeForEndHisRestOrders() throws Exception {
		Comparator<Job> comparator = new LWR();
		
		queue = new PriorityQueue<Job>(10, comparator);
		
		queue.add(jobList.get(0));
		assertTrue(jobList.get(0) == queue.peek());
		
		queue.add(jobList.get(1));
		assertTrue(jobList.get(1) == queue.peek());
		
		queue.add(jobList.get(2));
		assertTrue(jobList.get(1) == queue.peek());
	}
}
