package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SRTF {

	static double TA_sum =0;
	static double TOTALwaitTime =0;
	static ArrayList<Process> P_GRANT_CHART=new ArrayList<Process>();
	static int i;
	
	public static ObservableList<Process> srjf_alg( ArrayList<Process> L){
		i = 0;

		ArrayList<Process> P_Arrived=new ArrayList<Process>();
		Process no=new Process();

		int min_aTime=L.get(L.size()-1).arrival_time; // get the first arrival time

		ArrayList<Process>Tempp=(ArrayList<Process>)L.clone();
		int index=L.size()-1;
		int j=0;
		int tempi=0;
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
			j=getMinBurst(P_Arrived);
			
			}
			
			j=getMinBurst(P_Arrived); //  change the working process if needed 
		
			
		if(P_Arrived.isEmpty()!=true) {	
			
		 if(P_Arrived.get(j).burst_time!=0 ) {
			 P_Arrived.get(j).burst_time--;
			 P_GRANT_CHART.add(P_Arrived.get(j));

			 
		 }else {
			 tempi=GetIndex(L,P_Arrived.get(j).getPID());
			 L.get(tempi).setFinishtime(i);
			 L.get(tempi).setTA(i-L.get(tempi).getArrival_time());
			 L.get(tempi).setWTA((double)L.get(tempi).getTA()/(double)L.get(tempi).getBurst_time()); // WTA
			 L.get(tempi).setWait(L.get(tempi).getTA()-L.get(tempi).getBurst_time());
			 TOTALwaitTime+=L.get(tempi).getTA()-L.get(tempi).getBurst_time();
			 
			 TA_sum += i-L.get(tempi).arrival_time;

			 P_Arrived.remove(j);
			 
			 if(!P_Arrived.isEmpty()) { // if P_Arrived not empty then add processes
				 j=getMinBurst(P_Arrived);
				 P_Arrived.get(j).burst_time--;
				 P_GRANT_CHART.add(P_Arrived.get(j));
			 }
			 else P_GRANT_CHART.add(no); // NO PROCEES TO DISPLAY ON CHART 
			
		 }
		}
		i++;
		}

		 
		 bubblesrtOnID(L);
		 

	 	ObservableList<Process> L_Observalble = FXCollections.observableArrayList(L);
   	 System.out.println("======================================================");

		for (int iChart = 0;iChart <P_GRANT_CHART.size();iChart++)
		{
	    	 System.out.println(iChart);
	    	 System.out.println(P_GRANT_CHART.get(iChart).PID);

		     System.out.println("---------------------------------");

		}


		      return L_Observalble;
		}
		
		
		public static int getMinBurst(ArrayList<Process> P_Arrived){ 
			if(P_Arrived.isEmpty()) {
				return -1;
			}
			else {
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
