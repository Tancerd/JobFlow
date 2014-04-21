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
			else if (x.getTimeOfNextAction() > y.getTimeOfNextAction()) {
				return 1;
			}
			else
			{
				if (x instanceof Job && !(y instanceof Job)) return -1;
				if (y instanceof Job && !(x instanceof Job)) return 1;
				return 0;
			}
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
				if (machine == null) continue;
				machine.addNewJob(job);
				if (machine.getStatus().equals(MachineStatus.WAITING) && machine.justReturnedToWork()) {
					machine.setTimeOfNextAction(job.getTimeOfNextAction());
					orderQueue.add(machine);
				}
			}
			else
			{
				Machine machine = (Machine) orderComponent;	
				machine.setNextAction();
				calculateTime = machine.getTimeOfNextAction();
				System.out.println(machine.getId() + " " + calculateTime);
				if (machine.getStatus().equals(MachineStatus.JOB))
				{
					Job job = machine.getDoneJob();
					job.setTimeOfNextAction(calculateTime);
					job.setNextAction();
					orderQueue.add(job);
				}
				if (!machine.getStatus().equals(MachineStatus.WAITING))
					orderQueue.add(machine);
			}
		}
		return calculateTime;
	}

	private Machine findMachine(Job job) {
		if (job.getNextMachine() == -1) return null;
		Machine machine = new Machine();
		machine.setId(job.getNextMachine());
		int index = machineList.indexOf(machine);
		if (index == -1) return null;
		return machineList.get(index);
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

	
}
