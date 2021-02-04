		package application;
		
	
	import java.net.URL;

import javax.swing.JOptionPane;

import application.Process;
import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
		import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
		import java.io.FileNotFoundException;
		import java.io.FileWriter;
		import java.io.IOException;
		import java.io.PrintWriter;
		import java.util.*;
import java.awt.event.ActionEvent;
import java.io.*;
	
		
		public class SampleController implements Initializable {
				@FXML
			    private TableView<Process> tableView;
			  //  @FXML
			//    private TableView<Process> CalculationTable;

			  
			    @FXML
			    private TableColumn<Process, Integer> processID;
		
			    @FXML
			    private TableColumn<Process, Integer> ArrivalTime;
		
			    @FXML
			    private TableColumn<Process, Integer> BurstTime;
		
			    @FXML
			    private TableColumn<Process, Integer> Priority;
			
	
			    @FXML
			    private Button Generate;
			    @FXML
			    private TextField pNumText;
			    @FXML
			    private TableColumn<Process, Integer> col_finishTime;

			    @FXML
			    private TableColumn<Process, Integer> col_TA;

			    @FXML
			    private TableColumn<Process, Double> col_WTA;

			    @FXML
			    private TableColumn<Process, Integer> col_Wait;
	
			    @FXML
			    private Label SJFLabel;
			    @FXML
			    private Label AVG_TA_LABEL;

			    @FXML
			    private Label AVG_WT_LABEL;
			    @FXML
			    private TextField txt_cpuMulti;
				@Override
				public void initialize(URL arg0, ResourceBundle arg1) {
					// TODO Auto-generated method stub
				}
				
			
				////////////GeneratRandomNumbers////////////////
				@FXML
			    void GeneratRandomNumbers() throws IOException {
					
		    			
			    	
			    	   try {
				    		int length =Integer.parseInt(pNumText.getText());

			    	          // opening file in write mode (without overwriting)
	
			    	          PrintWriter write = new PrintWriter(new FileWriter("Numbers.txt",false));
	
			    	          // initializing random number generator
	
			    	          Random random = new Random();
	
			    	          for (int i = 0; i < length; i++) {
	
			    	        	  
			    	              // generating a number between (0 to 14) for IDs.
			    	        	  write.print(i+1);
			    	        	  write.print("  ");
			    	                // generating a number between (0 to 9) for arrival time.
	
			    	                write.print(random.nextInt(10));
			    	                // generating a number between (0 to 14) for burst time.
			    	               write.print("  ");
			    	            	write.print(random.nextInt(15)+1);
			    	              
			    	            	// generating a number between (0 to 10) for priority.
			    	                write.print("  ");
			    	                
			    	                 write.print(random.nextInt(15) +1);
			    	            	
			    	            	
			    	              	
			    	                write.println();
			    	          }
			    	          	System.out.println("Numbers are generated");
			    	          write.close();
			    	          showProcesses();
			    	        //  getProcess();
	
	
		
			    	   
			    }	catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Number of process MUST be integer");
		    	   }	    	   
			    	   
			    	catch (FileNotFoundException e) {
	
					JOptionPane.showMessageDialog(null,"Couldn't generate Numbers. Please try again");
	
	
			    }
			    	   
			    }
				/////////showProcesses()/////////////////////
				public void showProcesses() throws IOException
				{
					// set the columns in the table
					processID.setCellValueFactory(new PropertyValueFactory<Process, Integer>("pID")); 
					ArrivalTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("arrival_time"));
					BurstTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("burst_time"));
					Priority.setCellValueFactory(new PropertyValueFactory<Process, Integer>("priority"));      
					tableView.setItems(getProcess());

				}
				/////showProcessCal for the Calculation part/////////////////////////////
				public void showProcessesCalc() throws IOException
				{
					// set the columns in the table

					col_finishTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("finishtime")); 
					col_TA.setCellValueFactory(new PropertyValueFactory<Process, Integer>("TA"));
					col_WTA.setCellValueFactory(new PropertyValueFactory<Process, Double>("WTA"));
					col_Wait.setCellValueFactory(new PropertyValueFactory<Process, Integer>("wait"));  
	    
				}
				//////////////////////////////////
				public void showProcessesCalcForMulti() throws IOException
				{
					// set the columns in the table     	
					col_finishTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("FinishTimeForBurst")); 
					col_TA.setCellValueFactory(new PropertyValueFactory<Process, Integer>("TAforMulti"));
					col_WTA.setCellValueFactory(new PropertyValueFactory<Process, Double>("WTA"));
					col_Wait.setCellValueFactory(new PropertyValueFactory<Process, Integer>("WTforMulti"));   
				}
				//////////////////getProcess///////////////////////
				public ObservableList<Process> getProcess() throws IOException
				{
					System.out.println("GetProcess");
					ObservableList<Process> processes = FXCollections.observableArrayList();
					
					  String file = "Numbers.txt";
				        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	
				        String curLine;
				        while ((curLine = bufferedReader.readLine()) != null){
				            //process the line as required
				        	String[]  splitedString = curLine.split("  ");
				        	
				        
				        	processes.add(new Process (Integer.parseInt(splitedString[0]),Integer.parseInt(splitedString[1]),
				        			Integer.parseInt(splitedString[2]),Integer.parseInt(splitedString[3])));
				            	     
				        }
				        bufferedReader.close();
					return processes;
				}
//////////////////////////////////////////////////////////////////////////////////////////////		
				public ArrayList<Process> getProcessAsArrayList() throws IOException
				{
					System.out.println("getProcessAsArrayList");

					ArrayList<Process> processes = new ArrayList<Process>();
					
					  String file = "Numbers.txt";
				        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	
				        String curLine;
				        while ((curLine = bufferedReader.readLine()) != null){
				            //process the line as required
				        	String[]  splitedString = curLine.split("  ");
				        	
				        
				        	processes.add(new Process (Integer.parseInt(splitedString[0]),Integer.parseInt(splitedString[1]),
				        			Integer.parseInt(splitedString[2]),Integer.parseInt(splitedString[3])));
				            	     
				        }
				        bufferedReader.close();
					Collections.sort(processes);//sorted on arrival time
					return processes;
				}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		
			int SJFflag=0;
	
			@FXML
			public void SJF_alg() 
			{
				System.out.println("In SJF");
				 FCFSflag =0;
				 SRTFflag=0;
				 EP_without_PreemptionFlag=0;
				 EP_with_PreemptionFlag = 0;

				 
			try {	ObservableList<Process> SJF_Observalble = FXCollections.observableArrayList();
			//	Collections.sort(getProcessAsArrayList());
				
				if(getProcessAsArrayList().isEmpty())
					JOptionPane.showMessageDialog(null,"Please generate processes first");

				
				else {
					
					SJF_Observalble =	SJF.SJF_alg(getProcessAsArrayList());
					showProcessesCalc();
					SJFLabel.setText("Shortest Job First");

					tableView.setItems(SJF_Observalble);

					AVG_TA_LABEL.setText(String.valueOf(SJF.TA_sum/SJF_Observalble.size()));
					AVG_WT_LABEL.setText(String.valueOf(SJF.TOTALwaitTime/SJF_Observalble.size()));
				}
			//	SJF_Observalble.clear();
				SJFflag = 1;
				SJF.TA_sum =0;
				SJF.TOTALwaitTime=0;
				System.out.println("SJF is executed");
			}catch(IOException e)
				{
				JOptionPane.showMessageDialog(null,"Please generate processes first");
	
				}
				
			}
			
			int EP_without_PreemptionFlag = 0;

			@FXML
			public void EP_Without_Preemption_alg() 
			{
				System.out.println("In EP_Without_Preemption");
				 SJFflag=0;
				 FCFSflag =0;
				 SRTFflag=0;
				 EP_with_PreemptionFlag = 0;

			try {ObservableList<Process> EP_Without_Preemption_Observalble = FXCollections.observableArrayList();
			//	Collections.sort(getProcessAsArrayList());
				
				if(getProcessAsArrayList().isEmpty())
					JOptionPane.showMessageDialog(null,"Please generate processes first");

				
				else {
					
					EP_Without_Preemption_Observalble =	EP_Without_Preemption.EP_Without_Preemption_alg(getProcessAsArrayList());
					showProcessesCalc();
					SJFLabel.setText("EP Without Preemption");

					tableView.setItems(EP_Without_Preemption_Observalble);

					AVG_TA_LABEL.setText(String.valueOf(EP_Without_Preemption.TA_sum/EP_Without_Preemption_Observalble.size()));
					AVG_WT_LABEL.setText(String.valueOf(EP_Without_Preemption.TOTALwaitTime/EP_Without_Preemption_Observalble.size()));

				}
				EP_without_PreemptionFlag=1;
				EP_Without_Preemption.TA_sum=0;
				EP_Without_Preemption.TOTALwaitTime=0;
				System.out.println("EP Without Preemption is executed");
			}catch(IOException e)
				{
				JOptionPane.showMessageDialog(null,"Please generate processes first");

				}
				

			}
			int EP_with_PreemptionFlag = 0;
			public void EP_With_Preemption_alg() 
			{
				System.out.println("In EP_With_Preemption");
				SJFflag=0;
				 FCFSflag =0;
				 SRTFflag=0;
				 EP_without_PreemptionFlag=0;
				
				try {ObservableList<Process> EP_With_Preemption_Observalble = FXCollections.observableArrayList();
			
				
				if(getProcessAsArrayList().isEmpty())
					JOptionPane.showMessageDialog(null,"Please generate processes first");

				
				else {
					
					EP_With_Preemption_Observalble =EP_With_Preemption.EP_With_Preemption_alg(getProcessAsArrayList());
					showProcessesCalc();
					SJFLabel.setText("EP With Preemption");

					tableView.setItems(EP_With_Preemption_Observalble);

					AVG_TA_LABEL.setText(String.valueOf(EP_With_Preemption.TA_sum/EP_With_Preemption_Observalble.size()));
					AVG_WT_LABEL.setText(String.valueOf(EP_With_Preemption.TOTALwaitTime/EP_With_Preemption_Observalble.size()));

				}
				EP_with_PreemptionFlag=1;
				EP_With_Preemption.TA_sum=0;
				EP_With_Preemption.TOTALwaitTime=0;
				System.out.println("EP With Preemption is executed");
				}catch(IOException e)
					{
					JOptionPane.showMessageDialog(null,"Please generate processes first");

					}

			}
				

			int FCFSflag = 0;

				@FXML
			public void FCFS_alg() 
				{
					System.out.println("In FCFS");
					SJFflag=0;
					EP_without_PreemptionFlag =0;
				 	SRTFflag=0;
				 	EP_with_PreemptionFlag = 0;

				try {ObservableList<Process> FCFS_Observalble = FXCollections.observableArrayList();
				//	Collections.sort(getProcessAsArrayList());
					
					if(getProcessAsArrayList().isEmpty())
						JOptionPane.showMessageDialog(null,"Please generate processes first");

					
					else {
						
						FCFS_Observalble =	FCFS.FCFS_alg(getProcessAsArrayList());
						showProcessesCalc();
						SJFLabel.setText("FCFS");
						tableView.setItems(FCFS_Observalble);
						AVG_TA_LABEL.setText(String.valueOf(FCFS.TA_sum/FCFS_Observalble.size()));
						AVG_WT_LABEL.setText(String.valueOf(FCFS.TOTALwaitTime/FCFS_Observalble.size()));
					}
				
					FCFSflag = 1;
					FCFS.TA_sum=0;
					FCFS.TOTALwaitTime=0;
				System.out.println("FCFS is executed");
				}catch(IOException e)
				{
					JOptionPane.showMessageDialog(null,"Please generate processes first");

				}
				
			}
				int SRTFflag=0;

				public void SRTF_alg() 
				{
					System.out.println("In SRTF");
					SJFflag=0;
					EP_without_PreemptionFlag =0;
					FCFSflag=0;
					EP_with_PreemptionFlag = 0;

					try {ObservableList<Process> SRTF_Observalble = FXCollections.observableArrayList();
				//	Collections.sort(getProcessAsArrayList());
					
					if(getProcessAsArrayList().isEmpty())
						JOptionPane.showMessageDialog(null,"Please generate processes first");

					
					else {
						
						SRTF_Observalble =	SRTF.srjf_alg(getProcessAsArrayList());
						showProcessesCalc();
						SJFLabel.setText("SRTF");
						tableView.setItems(SRTF_Observalble);
						AVG_TA_LABEL.setText(String.valueOf(SRTF.TA_sum/SRTF_Observalble.size()));
						AVG_WT_LABEL.setText(String.valueOf(SRTF.TOTALwaitTime/SRTF_Observalble.size()));
					}
					SRTFflag=1;
					SRTF.TA_sum=0;
					SRTF.TOTALwaitTime=0;
				System.out.println("SRTF");
					}catch(IOException e)
					{
						JOptionPane.showMessageDialog(null,"Please generate processes first");

					}
				
			}
				
				int Multiflag = 0;
				public void Multi_alg() 
				{
					
					System.out.println("In Multi-Programming");
					//for the chart
					SJFflag=0;
					EP_without_PreemptionFlag =0;
					FCFSflag=0;
					EP_with_PreemptionFlag = 0;
					SRTFflag=0;
				
				try {ObservableList<Process> Multi_Observalble = FXCollections.observableArrayList();
				//	Collections.sort(getProcessAsArrayList());
					
					if(getProcessAsArrayList().isEmpty())
						JOptionPane.showMessageDialog(null,"Please Generate Random Processes First");

					
					else {
						try {
							Integer cpuPercent = Integer.parseInt(txt_cpuMulti.getText());
							if(cpuPercent > 100 || cpuPercent <=0)
							{
								JOptionPane.showMessageDialog(null,"Error: The CPU percentage has EXCEEDED 100 OR\n The CPU percentage entered is 0 or negative");

							}
							else {
								Multi_Observalble = Multi.Multi_alg(getProcessAsArrayList(),cpuPercent);
								showProcessesCalcForMulti();
								SJFLabel.setText("Multi Programming");
								tableView.setItems(Multi_Observalble);
								AVG_TA_LABEL.setText(String.valueOf(Multi.TA_sum/Multi_Observalble.size()));
								AVG_WT_LABEL.setText(String.valueOf(Multi.TOTALwaitTime/Multi_Observalble.size()));
							}
							
						}
						catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,"The CPU percentage MUST be integer");

						}
						
						
					}
					Multiflag=1;
					Multi.TA_sum=0;
					Multi.TOTALwaitTime=0;
				System.out.println("End of Multi");
				} catch(IOException e)
					{
					JOptionPane.showMessageDialog(null,"Please generate processes first");
	
					}
					
				}
				
		
			public void OpenNew()throws IOException {
				Stage stage = new Stage();
				
				stage.setTitle("Scatter Chart");
			 //   final NumberAxis xAxis = new NumberAxis(-1, 100, 5);
			   // final NumberAxis yAxis = new NumberAxis(0, Integer.parseInt(pNumText.getText())+1,1);   
				final NumberAxis xAxis ;
				final NumberAxis yAxis;
			 //   final ScatterChart<Number,Number> SChart = new ScatterChart<Number,Number>(xAxis,yAxis);
			 final ScatterChart<Number,Number> SChart ;

				//final ScatterChart<Number,Number> SChart;
			   
			    XYChart.Series series = new XYChart.Series();
			    ///////
			    if(SJFflag ==1)
			    {
			    	 xAxis = new NumberAxis(-1, SJF.i, 5);
					  yAxis = new NumberAxis(0, Integer.parseInt(pNumText.getText())+1,1);  
					  SChart = new ScatterChart<Number,Number>(xAxis,yAxis);
				      SChart.setTitle("SJF Chart");
				      xAxis.setLabel("Time");                
				      yAxis.setLabel("Process ID");

			    	   
			    	for(int i =0;i<SJF.P_GRANT_CHART.size();i++)
			    	{
				    series.getData().add(new XYChart.Data(i, SJF.P_GRANT_CHART.get(i).PID));
			    	}
			   
			    SChart.getData().add(series);
			    Scene scene  = new Scene(SChart,1500, 400);
			    stage.setScene(scene);
			    stage.show();
			    SJF.P_GRANT_CHART.clear();
				for(int i =0;i<  SJF.P_GRANT_CHART.size();i++)
					  SJF.P_GRANT_CHART.remove(i);
			    
			    }
			   /////
			    else if (EP_without_PreemptionFlag==1) 
			    {
			    	xAxis = new NumberAxis(-1, EP_Without_Preemption.i, 5);
					yAxis = new NumberAxis(0, Integer.parseInt(pNumText.getText())+1,1);  
					SChart = new ScatterChart<Number,Number>(xAxis,yAxis);
				    SChart.setTitle("SJF Chart");
				    xAxis.setLabel("Time");                
				    yAxis.setLabel("Process ID");
				    SChart.setTitle("EP without Preemption Chart");

			    	for(int i =0;i<EP_Without_Preemption.P_GRANT_CHART.size();i++)
			    	{
				    series.getData().add(new XYChart.Data(i, EP_Without_Preemption.P_GRANT_CHART.get(i).PID));
			    	}
			   
			    		SChart.getData().add(series);
			    		Scene scene  = new Scene(SChart,1500, 400);
			    		stage.setScene(scene);
			    		stage.show();
			    		EP_Without_Preemption.P_GRANT_CHART.clear();
			    		for(int i =0;i<EP_Without_Preemption.P_GRANT_CHART.size();i++)
			    			EP_Without_Preemption.P_GRANT_CHART.remove(i);
			    }
			    //////
			    else if(FCFSflag == 1)
			    {
			   	 xAxis = new NumberAxis(-1, FCFS.i, 5);
				  yAxis = new NumberAxis(0, Integer.parseInt(pNumText.getText())+1,1);  
				  SChart = new ScatterChart<Number,Number>(xAxis,yAxis);
			    SChart.setTitle("SJF Chart");
			    xAxis.setLabel("Time");                
			    yAxis.setLabel("Process ID");
				    SChart.setTitle("FCFS");

			    	for(int i =0;i<FCFS.P_GRANT_CHART.size();i++)
			    	{
				    series.getData().add(new XYChart.Data(i, FCFS.P_GRANT_CHART.get(i).PID));
			    	}
			   
			    		SChart.getData().add(series);
			    		Scene scene  = new Scene(SChart,1500, 400);
			    		stage.setScene(scene);
			    		stage.show();
			    		 FCFS.P_GRANT_CHART.clear();
			    			for(int i =0;i<  FCFS.P_GRANT_CHART.size();i++)
			    				FCFS.P_GRANT_CHART.remove(i);
			    }
			    /////
			    else if (SRTFflag == 1)
			    {
			   	 xAxis = new NumberAxis(-1, SRTF.i, 5);
				  yAxis = new NumberAxis(0, Integer.parseInt(pNumText.getText())+1,1);  
				  SChart = new ScatterChart<Number,Number>(xAxis,yAxis);
			    SChart.setTitle("SJF Chart");
			    xAxis.setLabel("Time");                
			    yAxis.setLabel("Process ID");
				    SChart.setTitle("SRTF Chart");

			    	for(int i =0;i<SRTF.P_GRANT_CHART.size();i++)
			    	{
				    series.getData().add(new XYChart.Data(i, SRTF.P_GRANT_CHART.get(i).PID));
			    	}
			   
			    		SChart.getData().add(series);
			    		Scene scene  = new Scene(SChart,1500, 400);
			    		stage.setScene(scene);
			    		stage.show();
			    		SRTF.P_GRANT_CHART.clear();
			    		for(int i =0;i<  SRTF.P_GRANT_CHART.size();i++)
			    			SRTF.P_GRANT_CHART.remove(i);
			    }
			    /////
			    else if (EP_with_PreemptionFlag==1)
			    {	
				   	 xAxis = new NumberAxis(-1, EP_With_Preemption.i, 5);
					 yAxis = new NumberAxis(0, Integer.parseInt(pNumText.getText())+1,1);  
					 SChart = new ScatterChart<Number,Number>(xAxis,yAxis);
				     SChart.setTitle("SJF Chart");
				     xAxis.setLabel("Time");                
				     yAxis.setLabel("Process ID");
				     SChart.setTitle("EP With Preemption Chart");
				     
			    	for(int i =0;i<EP_With_Preemption.P_GRANT_CHART.size();i++)
			    	{
				    series.getData().add(new XYChart.Data(i, EP_With_Preemption.P_GRANT_CHART.get(i).PID));
			    	}
			   
			    		SChart.getData().add(series);
			    		Scene scene  = new Scene(SChart,1500, 400);
			    		stage.setScene(scene);
			    		stage.show();
			    		EP_With_Preemption.P_GRANT_CHART.clear();
			    		for(int i =0;i<  EP_With_Preemption.P_GRANT_CHART.size();i++)
			    			EP_With_Preemption.P_GRANT_CHART.remove(i);
			    }
			    
				else if(Multiflag == 1)
				{
					JOptionPane.showMessageDialog(null,"No chart for the Multi-programming algorithm");

				}
			    	
			    }
		
			    
		}
	
		
		
		