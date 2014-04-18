package org.pwr.zssk.logic;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.Machine;
import org.pwr.zssk.model.OrderComponent;
import org.pwr.zssk.support.MachineStatus;

public class JobFlowAlgorithm {

	private Comparator<OrderComponent> comparator = new Comparator<OrderComponent>() {
		@Override
		public int compare(OrderComponent x, OrderComponent y) {
			if (x.getTimeOfNextAction() < y.getTimeOfNextAction()) {
				return -1;
			}
			if (x.getTimeOfNextAction() > y.getTimeOfNextAction()) {
				return 1;
			}
			return 0;
		}
	};

	private PriorityQueue<OrderComponent> orderQueue = new PriorityQueue<>(10,
			comparator);

	private List<Job> jobList;
	private List<Machine> machineList;

	public JobFlowAlgorithm() {

	}

	public int calculate() {
		int calculateTime = 0;
		for (Job j : jobList) {
			orderQueue.add(j);
		}
		while (!orderQueue.isEmpty()) {
			OrderComponent orderComponent = orderQueue.poll();
			if (orderComponent instanceof Job)
			{
				Job job = (Job) orderComponent;
				Machine machine = findMachine(job);
				machine.addNewJob(job);
				if (machine.getStatus().equals(MachineStatus.WAITING)) {
					machine.setTimeOfNextAction(calculateTime);
					orderQueue.add(machine);
				}
			}
			else
			{
				Machine machine = (Machine) orderComponent;
				calculateTime = machine.getTimeOfNextAction();
				machine.setNextAction();
				if (!machine.getStatus().equals(MachineStatus.WAITING))
					orderQueue.add(machine);
			}
		}
		return calculateTime;
	}

	private Machine findMachine(Job job) {
		Machine machine = new Machine();
		machine.setId(job.getNextMachine());
		int index = machineList.indexOf(machine);
		return machineList.get(index);
	}

}