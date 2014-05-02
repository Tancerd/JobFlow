package org.pwr.zssk.model;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.pwr.zssk.support.MachineStatus;
import org.pwr.zssk.support.Prepare;

public class Machine implements OrderComponent {

	private int id;

	private PriorityQueue<Job> buffor;
	private int timeOfNextAction = 0;
	private MachineStatus status = MachineStatus.WAITING;
	
	private int startingWaitDate = 0;
	private int waitingTime = 0;
	private int jobsNumber = 0;

	private List<Prepare> prepareList;

	private Job doneJob;

	@Override
	public int getTimeOfNextAction() {
		return timeOfNextAction;
	}

	@Override
	public void setNextAction() {
		switch (status) {
		case PREPARE:
			if (buffor.isEmpty()) {
				status = MachineStatus.WAITING;
				startingWaitDate = timeOfNextAction;
				break;
			} else
				doneJob = buffor.poll();
			timeOfNextAction += doneJob.getTimeForMachine(id);
			status = MachineStatus.JOB;
			break;
		case JOB:
			timeOfNextAction += getPrepareTime();
			status = MachineStatus.PREPARE;
			break;
		case WAITING:
			doneJob = buffor.poll();
			waitingTime += timeOfNextAction - startingWaitDate;
			timeOfNextAction += doneJob.getTimeForMachine(id);
			
			status = MachineStatus.JOB;
			break;
		}

	}

	private int getPrepareTime() {
		Prepare prepare = new Prepare();
		prepare.setJob(doneJob.getId());
		int index = prepareList.indexOf(prepare);
		return prepareList.get(index).getTime();
	}

	public void addNewJob(Job job) {
		buffor.add(job);
		jobsNumber++;
	}

	public void applyRule(Class c) {
		Comparator<Job> comparator = null;
		try {
			comparator = (Comparator<Job>) c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			comparator = null;
			e.printStackTrace();
		}
		buffor = new PriorityQueue<>(10, comparator);
	}

	public MachineStatus getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Prepare> getPrepareList() {
		return prepareList;
	}

	public void setPrepareList(List<Prepare> prepareList) {
		this.prepareList = prepareList;
	}

	public void setTimeOfNextAction(int timeOfNextAction) {
		this.timeOfNextAction = timeOfNextAction;
	}

	public Job getDoneJob() {
		return doneJob;
	}
	
	

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	
	public int getJobsNumber() {
		return jobsNumber;
	}

	@Override
	public boolean equals(Object obj) {
		Machine machine = (Machine) obj;
		if (machine == null)
			return false;
		return machine.getId() == this.getId();
	}

	public boolean justReturnedToWork() {
		return buffor.size() == 1;
	}

}
