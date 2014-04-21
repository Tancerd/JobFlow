package org.pwr.zssk.dataaccess;

import java.util.ArrayList;
import java.util.List;

import org.pwr.zssk.model.Job;
import org.pwr.zssk.model.Machine;
import org.pwr.zssk.model.rule.EDD;
import org.pwr.zssk.model.rule.FIFO;
import org.pwr.zssk.model.rule.LIFO;
import org.pwr.zssk.model.rule.LPT;
import org.pwr.zssk.model.rule.LWR;
import org.pwr.zssk.model.rule.SPT;
import org.pwr.zssk.support.Order;
import org.pwr.zssk.support.Prepare;

public class Facade {

	private int machineNumber;
	private int jobNumber;
	private List<Job> jobList = new ArrayList<Job>();
	private List<Machine> machineList = new ArrayList<Machine>();

	public void setNumberOfMachines(int m) {
		machineNumber = m;
	}

	public void setNumberOfJobs(int j) {
		jobNumber = j;
	}

	// liczba maszyn musi siê zgadzac z liczba regul
	public void setRulesForMachines(Rule[] rules) {
		for (int i = 0; i < machineNumber; i++) {
			Class rule;
			switch (rules[i]) {
			case FIFO:
				rule = FIFO.class;
				break;
			case LIFO:
				rule = LIFO.class;
				break;
			case EDD:
				rule = EDD.class;
				break;
			case LPT:
				rule = LPT.class;
				break;
			case LWR:
				rule = LWR.class;
				break;
			default:
				rule = SPT.class;
				break;
			}
			machineList.get(i).applyRule(rule);
		}
	}

	// rozmiar m kolumn na j wierszy
	public void setPrepareTimeForMachines(Integer[][] time) {
		int i = 0;
		for (Integer[] t : time) {
			List<Prepare> prepareList = new ArrayList<>();
			for (int j = 1; j <= jobNumber; j++) {
				prepareList.add(new Prepare(j, t[j - 1]));
			}
			machineList.get(i).setPrepareList(prepareList);
			i++;
		}
	}

	// rozmiar musi byc rowny liczbie jobow
	public void setArriveTimeForJobs(Integer[] times) {
		for (int i = 0; i < jobNumber; i++) {
			jobList.get(i).setTimeOfNextAction(times[i]);
		}
	}

	// lista o wielkosci j, z jobami po kolei
	public void setOrderForJobs(String[][] orders) {
		int i = 0;
		for (String[] o : orders) {
			List <Order> orderList = new ArrayList<Order>();
			for (String s : o) 
			{
				String[] split = s.split(" ");
				Order order = new Order(Integer.parseInt(split[1]), Integer.parseInt(split[0]));
				orderList.add(order);
			}
			jobList.get(i).setOrderList(orderList);
			i++;
		}
	}
}
