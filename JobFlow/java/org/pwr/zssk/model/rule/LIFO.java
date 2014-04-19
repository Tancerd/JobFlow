package org.pwr.zssk.model.rule;

import java.util.Comparator;

import org.pwr.zssk.model.Job;

public class LIFO implements Comparator<Job> {

	@Override
	public int compare(Job o1, Job o2) {
		return -1;
	}

}
