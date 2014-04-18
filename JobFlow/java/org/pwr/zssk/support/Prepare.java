package org.pwr.zssk.support;

public class Prepare {
	int job;
	int time;

	public Prepare() {
	}

	public Prepare(int job, int time) {
		this.job = job;
		this.time = time;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public boolean equals(Object obj) {
		Prepare prepare = (Prepare)obj;
		if (prepare == null) return false;
		return prepare.getJob() == this.job;
	}

	
}
