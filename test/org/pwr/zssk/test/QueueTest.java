package org.pwr.zssk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;
import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.OrderComponent;

public class QueueTest {

	private Comparator<OrderComponent> comparator;
	private PriorityQueue<OrderComponent> orderQueue;
	private List <OrderComponent> jobList;
	
	@Before
	public void before()
	{
		comparator = new Comparator<OrderComponent>() {
			@Override
			public int compare(OrderComponent x, OrderComponent y) {
				if (x.getTimeOfNextAction() < y.getTimeOfNextAction()) {
					return -1;
				} else if (x.getTimeOfNextAction() > y.getTimeOfNextAction()) {
					return 1;
				} else {
					if (x instanceof Job && !(y instanceof Job))
						return -1;
					if (y instanceof Job && !(x instanceof Job))
						return 1;
					if (y instanceof Job && x instanceof Job)
						return 1;
					return 0;
				}
			}
		};

		orderQueue = new PriorityQueue<>(10,
				comparator);
		
		jobList = new ArrayList<>(10);
		for (int i = 0; i < jobList.size(); i++)
		{
			Job j = new Job();
			j.setId(i);
			jobList.add(j);
		}
	}
	
	@Test
	public void shoudReturnCorrectOrder() throws Exception {
		for (OrderComponent oC : jobList)
			orderQueue.add(oC);
		
		for (int i = 0; i < orderQueue.size(); i++)
			assertEquals(i, ((Job)orderQueue.poll()).getId());
			
		
	}
}
