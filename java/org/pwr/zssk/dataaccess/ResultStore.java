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

	@Override
	public String toString() {
		String str="";
		
		str+="Œredni czas wykonywania Jobów: \n";
		for(int n=0;n<averageTimeOfMakingJob.length;n++)
			str+=(n+1)+": "+averageTimeOfMakingJob[n]+"\n";
		str+="\n";
		
		str+="Œredni czas przygotowywania maszyn: \n";
		for(int n=0;n<averageTimeOfPrepareMachine.length;n++)
			str+=(n+1)+": "+averageTimeOfPrepareMachine[n]+"\n";
		str+="\n";
		
		str+="Œredni czas czekania na maszynê: \n";
		for(int n=0;n<averageTimeOfWaitingForMachine.length;n++)
			str+=(n+1)+": "+averageTimeOfWaitingForMachine[n]+"\n";
		str+="\n";
		
		str+="Œredni czas czekania na zadanie: \n";
		for(int n=0;n<averageTimeOfWaitingForJob.length;n++)
			str+=(n+1)+": "+averageTimeOfWaitingForJob[n]+"\n";
		str+="\n";
		
		str+="Czas zakoñczenia zadañ: \n";
		for(int n=0;n<timeOfJobEnd.length;n++)
			str+=(n+1)+": "+timeOfJobEnd[n]+"\n";
		str+="\n";
		
		
		str+="Iloœæ zadañ na maszynie: \n";
		for(int n=0;n<numberOfJobsOnMachine.length;n++)
			str+=(n+1)+": "+numberOfJobsOnMachine[n]+"\n";
		str+="\n";
		
		
		str+="Iloœæ zadañ wykonanych przez maszynê: \n";
		for(int n=0;n<numberOfJobsDoneByMachine.length;n++)
			str+=(n+1)+": "+numberOfJobsDoneByMachine[n]+"\n";
		str+="\n";
		
		
		return str;
	}
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
