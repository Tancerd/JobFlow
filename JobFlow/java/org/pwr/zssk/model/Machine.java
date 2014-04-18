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
			if (buffor.isEmpty())
				status = MachineStatus.WAITING;
			else
				status = MachineStatus.JOB;
			break;
		case JOB:
			timeOfNextAction += getPrepareTime();
			status = MachineStatus.PREPARE;
			break;
		case WAITING:
			doneJob = buffor.poll();
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


	
	

}
