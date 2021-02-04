package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class EP_Without_Preemption {
	static double TA_sum =0;
	static double TOTALwaitTime =0;
	static ArrayList<Process> P_GRANT_CHART=new ArrayList<Process>();
	static int i;

	public static ObservableList<Process> EP_Without_Preemption_alg( ArrayList<Process> L){
		i = 0;

		ArrayList<Process> P_Arrived=new ArrayList<Process>();
		Process no=new Process();

		int min_aTime=L.get(L.size()-1).arrival_time; // get the first arrival time
		ArrayList<Process>Tempp=(ArrayList<Process>)L.clone();

		int index=L.size()-1;
		int j=0;
		int tempi;
		while(!P_Arrived.isEmpty() || !Tempp.isEmpty()) {

			while(index!=-1 && L.get(index).arrival_time == i) {
				Process Temp = new Process(L.get(index).PID,L.get(index).arrival_time,L.get(index).burst_time,L.get(index).priority);
				P_Arrived.add(Temp);

				Tempp.remove(index);	
				index--;
			}

			if(P_Arrived.isEmpty()) {
				P_GRANT_CHART.add(no);
			}
			if(i == min_aTime) {
				j=getHighestPriority(P_Arrived);

			}



			if(P_Arrived.isEmpty()!=true) {	

				if(P_Arrived.get(j).burst_time!=0 ) {
					P_Arrived.get(j).burst_time--;
					P_GRANT_CHART.add(P_Arrived.get(j));


				}else {
					tempi=GetIndex(L,P_Arrived.get(j).getPID());
					L.get(tempi).setFinishtime(i);
					L.get(tempi).setTA(i-L.get(tempi).getArrival_time());
					TA_sum += i-L.get(tempi).arrival_time;
					L.get(tempi).setWTA((double)L.get(tempi).getTA()/(double)L.get(tempi).getBurst_time()); // WTA
					L.get(tempi).setWait(L.get(tempi).getTA()-L.get(tempi).getBurst_time());
					TOTALwaitTime+=L.get(tempi).getTA()-L.get(tempi).getBurst_time();


					P_Arrived.remove(j);
					if(!P_Arrived.isEmpty()) {

						j=getHighestPriority(P_Arrived);
						P_Arrived.get(j).burst_time--;
						P_GRANT_CHART.add(P_Arrived.get(j));
					}
					else P_GRANT_CHART.add(no); // NO PROCEES TO DISPLAY ON CHART 

				}
			}
			///////////////////aging////////////////////////////
			if(P_Arrived.isEmpty()!=true) {
				for(int k=0;k<P_Arrived.size();k++) {
					if(k!=j && P_Arrived.get(k).priority!=0) { // don't increment the age if the process is working and if the process has ZERO priority
						P_Arrived.get(k).Age++;	 
					}
					if(P_Arrived.get(k).Age%3==0 && P_Arrived.get(k).priority!=0 &&  k!=j) { // k!=j should also be added 
						P_Arrived.get(k).priority--;
					}


				}
			}
			i++;
		}


		bubblesrtOnID(L);  // to show them in ascending order in the table view 




		ObservableList<Process> L_Observalble = FXCollections.observableArrayList(L);

		for (int iChart = 0;iChart <P_GRANT_CHART.size();iChart++)
		{
			System.out.println(iChart);
			System.out.println(P_GRANT_CHART.get(iChart).PID);

			System.out.println("---------------------------------");

		}

		return L_Observalble;



	}
	public static int getHighestPriority(ArrayList<Process> P_Arrived){ 
		if(P_Arrived.isEmpty()) {
			return -1;
		}
		else {
			int minValue =Math.abs(P_Arrived.get(0).priority) ;
			int index = 0;
			int PID = P_Arrived.get(0).PID; 
			for(int i=1;i<P_Arrived.size();i++){ 
				if(P_Arrived.get(i).priority < minValue){ // lower number == higher priority
					minValue = Math.abs(P_Arrived.get(i).priority); 
					index = i;
				}
				// here if the priority is equal then it checks the PID
				else if (P_Arrived.get(i).priority == minValue) {
					if(P_Arrived.get(i).PID < PID)
					{
						PID = P_Arrived.get(i).PID;
						index = i;
					}	  
				}
			}
			return index; 
		}
	} 

	public static int getArrival(ArrayList<Process> P_Arrived,int in){ 

		int arrive_time = -1;

		for(int i=0;i<P_Arrived.size();i++){ 
			if(P_Arrived.get(i).arrival_time == in){  
				arrive_time = P_Arrived.get(i).arrival_time;
			} 
		}
		return arrive_time; 
	} 

	public static int TotalTime(ArrayList<Process>L) {
		int max_time=L.get(L.size()-1).arrival_time;
		for(int i=0;i<L.size();i++) {
			max_time+=L.get(i).burst_time;
		}
		return max_time;

	}

	public static void bubblesrtOnID(ArrayList<Process> L)
	{
		Process temp;

		for (int x=0; x<L.size(); x++) // bubble sort outer loop
		{
			for (int i=0; i < L.size() - x - 1; i++) {
				if (L.get(i).getPID() - L.get(i+1).getPID() > 0)
				{
					temp = L.get(i);
					L.set(i,L.get(i+1));
					L.set(i+1, temp);
				}
			}

		}

	}
	public static int GetIndex(ArrayList<Process>L,int PID) {
		int index=0;
		for(int i=0;i<L.size();i++) {
			if(L.get(i).getPID()==PID) {
				index=i;
				break;
			}


		}
		return index;
	}

}



