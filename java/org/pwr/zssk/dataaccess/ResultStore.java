package org.pwr.zssk.dataaccess;

public class ResultStore {

	private int resultTime;
	private double[] TimeOfMakingJob;
	private double[] TimeOfPrepareMachine;
	private double[] TimeOfWaitingForMachine;
	private double[] TimeOfWaitingForJob;
	private int[] timeOfJobEnd;
	private int[] numberOfJobsOnMachine;
	private int[] numberOfJobsDoneByMachine;
	private String[] logs;

	@Override
	public String toString() {
		String str="";
		
		str+="Czas zakoñczenia wszystkich prac: " + resultTime + "\n\n";
		
		str+="Ca³kowity czas wykonywania Jobów: \n";
		for(int n=0;n<TimeOfMakingJob.length;n++)
			str+=(n+1)+": "+TimeOfMakingJob[n]+"\n";
		str+="\n";
		
		str+="Ca³kowity czas przygotowywania maszyn: \n";
		for(int n=0;n<TimeOfPrepareMachine.length;n++)
			str+=(n+1)+": "+TimeOfPrepareMachine[n]+"\n";
		str+="\n";
		
		str+="Ca³kowity czas czekania na maszynê: \n";
		for(int n=0;n<TimeOfWaitingForMachine.length;n++)
			str+=(n+1)+": "+TimeOfWaitingForMachine[n]+"\n";
		str+="\n";
		
		str+="Ca³kowity czas czekania na zadanie: \n";
		for(int n=0;n<TimeOfWaitingForJob.length;n++)
			str+=(n+1)+": "+TimeOfWaitingForJob[n]+"\n";
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

	

	public double[] getTimeOfMakingJob() {
		return TimeOfMakingJob;
	}
	public void setTimeOfMakingJob(double[] timeOfMakingJob) {
		TimeOfMakingJob = timeOfMakingJob;
	}
	public double[] getTimeOfPrepareMachine() {
		return TimeOfPrepareMachine;
	}
	public void setTimeOfPrepareMachine(double[] timeOfPrepareMachine) {
		TimeOfPrepareMachine = timeOfPrepareMachine;
	}
	public double[] getTimeOfWaitingForMachine() {
		return TimeOfWaitingForMachine;
	}
	public void setTimeOfWaitingForMachine(double[] timeOfWaitingForMachine) {
		TimeOfWaitingForMachine = timeOfWaitingForMachine;
	}
	public double[] getTimeOfWaitingForJob() {
		return TimeOfWaitingForJob;
	}
	public void setTimeOfWaitingForJob(double[] timeOfWaitingForJob) {
		TimeOfWaitingForJob = timeOfWaitingForJob;
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
