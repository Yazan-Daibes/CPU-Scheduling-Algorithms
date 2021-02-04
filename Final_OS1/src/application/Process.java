package application;


public class Process implements Comparable <Process> {
	int PID;
	int arrival_time;
	//double burst_time;
	int burst_time;
	int priority;
	int Age;

	int finishtime;
	int TA;
	double WTA;
	int wait;
	// for multiprogramming
	double FinishTimeForBurst;
	double TAforMulti;
	double WTforMulti;
	double burstTimeForMulti;
	
	public Process() {
		PID=-1;
		arrival_time=0;
		burst_time=0;
		priority=-1;
	}


	public Process(int pID, int arrival_time, int burst_time, int priority) {
		super();
		PID = pID;
		this.arrival_time = arrival_time;
		this.burst_time = burst_time;
		this.priority = priority;
	}
	
	
	@Override
	public String toString() {
		return "Process [PID=" + PID + ", arrival_time=" + arrival_time + ", burst_time=" + burst_time + ", priority="
				+ priority + ", finishtime=" + finishtime + ", TA=" + TA + ", WTA=" + WTA + ", wait=" + wait + "]";
	}


	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public int getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(int arrival_time) {
		this.arrival_time = arrival_time;
	}

	public int getBurst_time() {
		return burst_time;
	}
	public void setBurst_time(int burst_time) {
		this.burst_time = burst_time;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	

	public int getFinishtime() {
		return finishtime;
	}


	public void setFinishtime(int finishtime) {
		this.finishtime = finishtime;
	}


	public int getTA() {
		return TA;
	}


	public void setTA(int tA) {
		TA = tA;
	}


	public double getWTA() {
		return WTA;
	}


	public void setWTA(double wTA) {
		WTA = wTA;
	}


	public int getWait() {
		return wait;
	}


	public void setWait(int wait) {
		this.wait = wait;
	}


	public double getBurstTimeForMulti() {
		return burstTimeForMulti;
	}


	public void setBurstTimeForMulti(double burstTimeForMulit) {
		this.burstTimeForMulti = burstTimeForMulit;
	}
	public double getFinishTimeForBurst() {
		return FinishTimeForBurst;
	}


	public void setFinishTimeForBurst(double finishTimeForBurst) {
		FinishTimeForBurst = finishTimeForBurst;
	}





	public double getTAforMulti() {
		return TAforMulti;
	}


	public void setTAforMulti(double tAforMulti) {
		TAforMulti = tAforMulti;
	}


	public double getWTforMulti() {
		return WTforMulti;
	}


	public void setWTforMulti(double wTforMulti) {
		WTforMulti = wTforMulti;
	}


	public int compareTo(Process l) {
		if(this.getArrival_time()==l.getArrival_time())
		return 0;
		else if(this.getArrival_time()< l.getArrival_time())
			return 1;
		else 
			return -1;
	}

	}
