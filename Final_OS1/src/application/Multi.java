package application;


import java.util.*;

import application.Process;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Multi {
	static double TA_sum =0;
	static double TOTALwaitTime =0;

	public static ObservableList<Process> Multi_alg(ArrayList<Process>L,int perc) {
		ArrayList<Process>P_Arrived=new ArrayList<Process>();
		ArrayList<Process>P_Finished=new ArrayList<Process>();
		System.out.println("Before clone");
		ArrayList<Process>Temp=(ArrayList<Process>)L.clone();

		double real_perc=(perc * 0.01) ; // divison doesn't work.

		System.out.println("Real perc = "+real_perc);

		double j=0;
		float time=0;
		int index=L.size()-1;
		double MinBurst = 0;
		int Tin;
		while(!P_Arrived.isEmpty() || !Temp.isEmpty()) {
			if(!Temp.isEmpty()) {
				j=L.get(index).getArrival_time();
				while(index!=-1 && L.get(index).arrival_time ==j) {
					Process Tempp = new Process(L.get(index).PID,L.get(index).arrival_time,L.get(index).burst_time,L.get(index).priority);
					P_Arrived.add(Tempp);
					P_Arrived.get(P_Arrived.size()-1).setBurstTimeForMulti((double)P_Arrived.get(P_Arrived.size()-1).getBurst_time());
					Temp.remove(index);
					index--;
				}

				time=Calc(real_perc,P_Arrived.size());

				MinBurst=getMinBurst(P_Arrived);

				if(!Temp.isEmpty()) { // all process has NOT arrived
					if(((MinBurst/time)+P_Arrived.get(P_Arrived.size()-1).arrival_time)<Temp.get(index).arrival_time)// a process has finished before the next process arrived
					{
						Tin=GetIndex(L,P_Arrived.get(getMin(P_Arrived)).PID);	
						L.get(Tin).setFinishTimeForBurst(((MinBurst/time)+P_Arrived.get(P_Arrived.size()-1).arrival_time));   
						j=L.get(Tin).getFinishTimeForBurst();
						for(int i=0;i<P_Arrived.size();i++) {
							//finish time for process-latest arrival time*calc
							P_Arrived.get(i).setBurstTimeForMulti(P_Arrived.get(i).burstTimeForMulti-((j-P_Arrived.get(P_Arrived.size()-1).arrival_time)*time));	

						}
						P_Arrived.remove(getMin(P_Arrived));
						time=Calc(real_perc,P_Arrived.size());
					}
					for(int i=0;i<P_Arrived.size();i++) {
						P_Arrived.get(i).setBurstTimeForMulti(P_Arrived.get(i).burstTimeForMulti-((Temp.get(index).arrival_time-j)*time));

					}
				}
			}
			else {
				MinBurst=getMinBurst(P_Arrived);
				time=Calc(real_perc,P_Arrived.size());
				Tin=GetIndex(L,P_Arrived.get(getMin(P_Arrived)).PID);	
				L.get(Tin).setFinishTimeForBurst(((MinBurst/time)+j));
				j=L.get(Tin).getFinishTimeForBurst();	
				for(int i=0;i<P_Arrived.size();i++) {
					P_Arrived.get(i).setBurstTimeForMulti(P_Arrived.get(i).getBurstTimeForMulti()-MinBurst);   	
				}
				System.out.println(j+"-------------"+L.get(Tin));
				P_Arrived.remove(getMin(P_Arrived));



			}

		}
		System.out.println("===================================================");


		for(int i=0;i<L.size();i++)
		{
			L.get(i).setTAforMulti(L.get(i).getFinishTimeForBurst() - L.get(i).getArrival_time());
			TA_sum += L.get(i).getFinishTimeForBurst()-L.get(i).getArrival_time();

			L.get(i).setWTforMulti(L.get(i).getTAforMulti()-L.get(i).getBurst_time());
			L.get(i).setWTA((double)L.get(i).getTAforMulti()/(double)L.get(i).getBurst_time()); // WTA

			System.out.println("WT = "+L.get(i).getWTforMulti() + "for "+L.get(i));
			System.out.println("===================================================");

			TOTALwaitTime+=L.get(i).getTAforMulti()-L.get(i).getBurst_time();
		}

		System.out.println("TA average = " +TA_sum/L.size() );
		System.out.println("WTA average = " +TOTALwaitTime/L.size() );


		bubblesrtOnID(L);

		ObservableList<Process> L_Observalble = FXCollections.observableArrayList(L);//converting array-list to observable





		return L_Observalble;

	}



	public static float Calc(double cpu,int s) {


		double wait=1-cpu;
		float result=0;
		for (int i=0;i<s;i++) {
			result+=cpu*Math.pow(wait, i);

		}
		return result/s;
	}
	public static double getMinBurst(ArrayList<Process> P_Arrived){ 

		double minValue = P_Arrived.get(0).burstTimeForMulti;

		for(int i=1;i<P_Arrived.size();i++){ 
			if(P_Arrived.get(i).burstTimeForMulti < minValue){ 
				minValue = P_Arrived.get(i).burstTimeForMulti; 
			} 
		}

		return minValue; 
	} 
	public static int getMin(ArrayList<Process> P_Arrived){ 

		int minValue = P_Arrived.get(0).burst_time;
		int index = 0;

		for(int i=1;i<P_Arrived.size();i++){ 
			if(P_Arrived.get(i).burst_time < minValue){ 
				minValue = P_Arrived.get(i).burst_time; 
				index = i;
			} 
		}


		return index; 
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

}


