package org.pwr.zssk.model.rule;

import java.util.Comparator;

import org.pwr.zssk.model.Job;

public class LWR implements Comparator<Job> {

	@Override
	public int compare(Job o1, Job o2) {
		int j1 = o1.getSumOfRestTimes();
		int j2 = o2.getSumOfRestTimes();
		if (j1 > j2) return 1;
		if (j1 < j2) return -1;
		return 0;
	}

}
