package org.pwr.zssk.dataaccess;

import java.util.ArrayList;
import java.util.List;

import org.pwr.zssk.logic.JobFlowAlgorithm;
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

	
	private List<Job> jobList = new ArrayList<Job>();
	private List<Machine> machineList = new ArrayList<Machine>();
	
	private JobFlowAlgorithm jobFlowAlgorithm;
	
	private DataStore dataStore = new DataStore();
	private ResultStore resultStore = new ResultStore();

	
	public void prepareAlgorithm()
	{
		jobList = new ArrayList<Job>(dataStore.getJobNumber());
		machineList = new ArrayList<Machine>(dataStore.getMachineNumber());
		
		this.prepareArriveTimeForJobs();
		this.prepareOrderForJobs();
		this.prepareRulesForMachines();
		
		jobFlowAlgorithm = new JobFlowAlgorithm();
		jobFlowAlgorithm.setJobList(jobList);
		jobFlowAlgorithm.setMachineList(machineList);
	}
	
	public void Calculate()
	{
		int result = jobFlowAlgorithm.calculate();
		
		double[] averageTimeOfMakingJob = new double[dataStore.getMachineNumber()]; //jest
		int[] numberOfJobsOnMachine = new int[dataStore.getMachineNumber()]; //jest
		double[] averageTimeOfPrepareMachine = new double[dataStore.getMachineNumber()]; //jest
		double[] averageTimeOfWaitingForMachine = new double[dataStore.getJobNumber()]; //jest
		double[] averageTimeOfWaitingForJob = new double[dataStore.getMachineNumber()]; //jest
		int[] numberOfJobsDoneByMachine = new int[dataStore.getMachineNumber()];
		int[] timeOfJobEnd = new int[dataStore.getJobNumber()];
		
		String[] logs = new String[jobFlowAlgorithm.getLogsList().size()];
		logs = jobFlowAlgorithm.getLogsList().toArray(logs);
		
		for (int i = 0; i < jobFlowAlgorithm.getJobList().size(); i++) {
			Job j = jobFlowAlgorithm.getJobList().get(i);
			timeOfJobEnd[i] = j.getTimeOfNextAction();
			for (Order o : j.getOrderList())
			{
				averageTimeOfMakingJob[o.getMachine()-1] += o.getTime();
				numberOfJobsOnMachine[o.getMachine()-1]++;
			}
			averageTimeOfWaitingForMachine[i] = j.getWaitingTime() / j.getOrderList().size();
			
			
			
		}
		
		for (int i = 0; i < jobFlowAlgorithm.getMachineList().size(); i++) {
			averageTimeOfMakingJob[i] /= numberOfJobsOnMachine[i];
			
			Machine m = jobFlowAlgorithm.getMachineList().get(i);
			for(Prepare p : m.getPrepareList())
				averageTimeOfPrepareMachine[i] += p.getTime();
			averageTimeOfPrepareMachine[i] /= m.getPrepareList().size();
			numberOfJobsDoneByMachine[i] = m.getJobsNumber();
			averageTimeOfWaitingForMachine[i] = m.getWaitingTime() / m.getJobsNumber(); 
			
		}
		
		resultStore.setAverageTimeOfMakingJob(averageTimeOfMakingJob);
		resultStore.setAverageTimeOfPrepareMachine(averageTimeOfPrepareMachine);
		resultStore.setAverageTimeOfWaitingForJob(averageTimeOfWaitingForJob);
		resultStore.setAverageTimeOfWaitingForMachine(averageTimeOfWaitingForMachine);
		resultStore.setResultTime(result);
		resultStore.setTimeOfJobEnd(timeOfJobEnd);
		resultStore.setNumberOfJobsOnMachine(numberOfJobsOnMachine);
		resultStore.setNumberOfJobsDoneByMachine(numberOfJobsDoneByMachine);
		resultStore.setLogs(logs);
		
	}
	// liczba maszyn musi siê zgadzac z liczba regul
	private void prepareRulesForMachines() {
		Rule[] rules = dataStore.getRules();
		for (int i = 0; i < rules.length; i++) {
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
	private void setPrepareTimeForMachines() {
		Integer[][] time = dataStore.getPrepareTimes();
		int i = 0;
		for (Integer[] t : time) {
			List<Prepare> prepareList = new ArrayList<>();
			for (int j = 1; j <= t.length; j++) {
				prepareList.add(new Prepare(j, t[j - 1]));
			}
			machineList.get(i).setPrepareList(prepareList);
			i++;
		}
	}

	// rozmiar musi byc rowny liczbie jobow
	private void prepareArriveTimeForJobs() {
		Integer[] times = dataStore.getArriveTimes();
		for (int i = 0; i < times.length; i++) {
			jobList.get(i).setTimeOfNextAction(times[i]);
		}
	}

	// lista o wielkosci j, z jobami po kolei
	private void prepareOrderForJobs() {
		String[][] orders = dataStore.getOrders();
		int i = 0;
		for (String[] o : orders) {
			List <Order> orderList = new ArrayList<Order>();
			for (String s : o) 
			{
				String[] split = s.split(" ");
				Order order = new Order(Integer.parseInt(split[1]), Integer.parseInt(split[0]));
				if (split[0].equals("0")) break;
				orderList.add(order);
			}
			jobList.get(i).setOrderList(orderList);
			i++;
		}
	}

	public DataStore getDataStore() {
		return dataStore;
	}

	public void setDataStore(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	public ResultStore getResultStore() {
		return resultStore;
	}

	public void setResultStore(ResultStore resultStore) {
		this.resultStore = resultStore;
	}
	
	
}
