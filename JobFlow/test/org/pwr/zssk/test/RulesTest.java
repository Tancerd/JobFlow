package org.pwr.zssk.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;
import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.rule.FIFO;

public class RulesTest {

	private PriorityQueue<Job> queue;
	private List<Job> jobList;
	
	@Before
	public void before()
	{
		Job job1 = new Job();
		job1.setTimeToNextAction(10);
		
		Job job2 = new Job();
		job1.setTimeToNextAction(20);
		
		Job job3 = new Job();
		job1.setTimeToNextAction(5);
		
		jobList = new ArrayList<>(10);
		
		jobList.add(job1);
		jobList.add(job2);
		jobList.add(job3);
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
}
