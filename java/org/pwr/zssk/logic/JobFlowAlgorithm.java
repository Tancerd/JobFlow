package org.pwr.zssk.logic;

import java.util.Comparator;
import java.util.LinkedList;
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

	private PriorityQueue<OrderComponent> orderQueue = new PriorityQueue<>(10,
			comparator);

	private List<Job> jobList;
	private List<Machine> machineList;
	private List<String> logsList = new LinkedList<>();

	public JobFlowAlgorithm() {

	}

	public int calculate() {
		int calculateTime = 0;
		for (Job j : jobList) {
			orderQueue.add(j);
		}
		while (!orderQueue.isEmpty()) {
			OrderComponent orderComponent = orderQueue.poll();
			if (orderComponent instanceof Job) {
				Job job = (Job) orderComponent;
				Machine machine = findMachine(job);
				if (machine == null)
					continue;
				//System.out.println("Time: " + job.getTimeOfNextAction() + " Job " + job.getId() + " to Machine " + machine.getId());
				logsList.add("Time: " + job.getTimeOfNextAction() + " Job " + job.getId() + " to Machine " + machine.getId());
				job.setStartingWaitDate(job.getTimeOfNextAction());
				machine.addNewJob(job);				
				if (machine.getStatus().equals(MachineStatus.WAITING)
						&& machine.justReturnedToWork()) {
					machine.setTimeOfNextAction(job.getTimeOfNextAction());
					orderQueue.add(machine);
					//System.out.println("Time: " + job.getTimeOfNextAction() + " Machine " + machine.getId() + ": END " +  machine.getStatus());
					logsList.add("Time: " + job.getTimeOfNextAction() + " Machine " + machine.getId() + ": END " +  machine.getStatus());
				}
			} else {
				Machine machine = (Machine) orderComponent;
				int oldTime = machine.getTimeOfNextAction();
				machine.setNextAction();
				calculateTime = machine.getTimeOfNextAction();
				//System.out.println("Time: " + oldTime + " Machine " + machine.getId() + ": START " +  machine.getStatus() + " " + machine.getDoneJob().getId());
				logsList.add("Time: " + oldTime + " Machine " + machine.getId() + ": START " +  machine.getStatus() + " " + machine.getDoneJob().getId());

				if (machine.getStatus().equals(MachineStatus.JOB)) {
					Job job = machine.getDoneJob();
					job.setTimeOfNextAction(calculateTime);
					job.setNextAction();
					//System.out.println("Done Job: " + job.getId());
					//logsList.add("Done Job: " + job.getId());
					orderQueue.add(job);
				}
				if (!machine.getStatus().equals(MachineStatus.WAITING))
				{
					//System.out.println("Time: " + calculateTime + " Machine " + machine.getId() + ": END " +  machine.getStatus() + " " + machine.getDoneJob().getId());
					logsList.add("Time: " + calculateTime + " Machine " + machine.getId() + ": END " +  machine.getStatus() + " " + machine.getDoneJob().getId());
					orderQueue.add(machine);
				}
			}
		}
		//System.out.println("----------------------------------------");
		return calculateTime;
	}

	private Machine findMachine(Job job) {
		if (job.getNextMachine() == -1)
			return null;
		Machine machine = new Machine();
		machine.setId(job.getNextMachine());
		int index = machineList.indexOf(machine);
		if (index == -1)
			return null;
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

	public List<String> getLogsList() {
		return logsList;
	}
	

}
