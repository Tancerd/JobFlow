package org.pwr.zssk.dataaccess;

public class ResultStore {

	private int resultTime;
	private double[] averageTimeOfMakingJob;
	private double[] averageTimeOfPrepareMachine;
	private double[] averageTimeOfWaitingForMachine;
	private double[] averageTimeOfWaitingForJob;
	private int[] timeOfJobEnd;
	private int[] numberOfJobsOnMachine;
	private int[] numberOfJobsDoneByMachine;
	private String[] logs;

	public int getResultTime() {
		return resultTime;
	}

	public void setResultTime(int resultTime) {
		this.resultTime = resultTime;
	}

	public double[] getAverageTimeOfMakingJob() {
		return averageTimeOfMakingJob;
	}

	public void setAverageTimeOfMakingJob(double[] averageTimeOfMakingJob) {
		this.averageTimeOfMakingJob = averageTimeOfMakingJob;
	}

	public double[] getAverageTimeOfPrepareMachine() {
		return averageTimeOfPrepareMachine;
	}

	public void setAverageTimeOfPrepareMachine(
			double[] averageTimeOfPrepareMachine) {
		this.averageTimeOfPrepareMachine = averageTimeOfPrepareMachine;
	}

	public double[] getAverageTimeOfWaitingForMachine() {
		return averageTimeOfWaitingForMachine;
	}

	public void setAverageTimeOfWaitingForMachine(
			double[] averageTimeOfWaitingForMachine) {
		this.averageTimeOfWaitingForMachine = averageTimeOfWaitingForMachine;
	}

	public double[] getAverageTimeOfWaitingForJob() {
		return averageTimeOfWaitingForJob;
	}

	public void setAverageTimeOfWaitingForJob(
			double[] averageTimeOfWaitingForJob) {
		this.averageTimeOfWaitingForJob = averageTimeOfWaitingForJob;
	}

	public int[] getTimeOfJobEnd() {
		return timeOfJobEnd;
	}

	public void setTimeOfJobEnd(int[] timeOfJobEnd) {
		this.timeOfJobEnd = timeOfJobEnd;
	}

	public int[] getNumberOfJobsOnMachine() {
		return numberOfJobsOnMachine;
	}

	public void setNumberOfJobsOnMachine(int[] numberOfJobsOnMachine) {
		this.numberOfJobsOnMachine = numberOfJobsOnMachine;
	}

	public int[] getNumberOfJobsDoneByMachine() {
		return numberOfJobsDoneByMachine;
	}

	public void setNumberOfJobsDoneByMachine(int[] numberOfJobsDoneByMachine) {
		this.numberOfJobsDoneByMachine = numberOfJobsDoneByMachine;
	}

	public String[] getLogs() {
		return logs;
	}

	public void setLogs(String[] logs) {
		this.logs = logs;
	}

	

}
