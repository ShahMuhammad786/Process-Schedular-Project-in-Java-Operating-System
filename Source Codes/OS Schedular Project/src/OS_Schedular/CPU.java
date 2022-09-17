package OS_Schedular;

import java.util.ArrayList;

import javax.swing.JOptionPane;

class Generator implements Runnable{
	ArrayList<Process> rdq = new ArrayList<Process>();
	ArrayList<Process> wtq = new ArrayList<Process>();
	
	/*Total Timings Timings*/
	long tot_TAT=0;
	long tot_WT=0;
	long tot_RT=0;
	long tot_CPB=0;
	
		
	/*PRE Waiting times*/
	long wst=0;
	long wet=0;
	long wtt=0;
			
	/*NonPre waiting times*/
	long nwst=0;
	long nwet=0;
	long nwtt=0;
	
	
	/*Others*/
	int Nos_prcs=0;
	boolean preflag ;
	boolean flag;
	
	public Generator(int num_prc , int num_pre_prc){
		Process memoryBarrier [] = new Process[10];
		int totProcess = (num_prc+num_pre_prc);
		Nos_prcs=totProcess;
		
		
		
		int preCount=0;
		int nPreCount=0;
		System.out.println("Processes Creating ... Wait !\n*********************************\n");
		
		//Memory Barrier Handling...
		for(int i=0 ; i<totProcess ; i++) {
			
			try {Thread.sleep(100);}catch(Exception ex) {ex.getStackTrace();} //System delay.					
			//for preemptive process.
			if(i<num_pre_prc) {
				Process prc = new Process("Process-"+(i+1), true);
				memoryBarrier[preCount]=prc;
				preCount++;		
				
				tot_CPB= tot_CPB+prc.cpu_b;
				/*Waiting START times*/
				wst=System.nanoTime();
				if(preCount==10) {
					for(int j=0 ; j<10 ; j++) {
						wtq.add(memoryBarrier[j]);
					}
					preCount=0;

				}else {  					
					if(i==(num_pre_prc-1)) {
						for(int j=0 ; j<preCount ; j++) {
							wtq.add(memoryBarrier[j]);
						}
					}
				}
			}
			//for non-preemptive processes.
			else {
				
				Process prc = new Process("Process-"+(i+1));				
				memoryBarrier[nPreCount]=prc;
				nPreCount++;
				
				tot_CPB= tot_CPB+prc.cpu_b;
				/*Waiting START time*/
				nwst= System.nanoTime();
				if(nPreCount==10) {
					for(int j=0; j<10 ; j++) {
						rdq.add(memoryBarrier[j]);
					}
					nPreCount=0;
				}else {
					if(i==(totProcess-1)) {
						for(int j=0 ; j<nPreCount ; j++) {
							rdq.add(memoryBarrier[j]);
						}						
					}					
				}
			}
		}
	}
	
	
	public void exe() {
		
		//For non preemptive processes;
		int upto = rdq.size();		
		for(int i=0 ; i<upto ; i++) {
			Process prc = rdq.get(0);
			try {Thread.sleep(prc.cpu_b);}catch(Exception e) {e.getStackTrace();}
				
			/*Waiting END time*/
			nwet = System.nanoTime();
			nwtt = nwet-nwst;
			tot_WT= tot_WT+nwtt;
			rdq.remove(prc);
			
			/*End Turn Around Time*/
			prc.et_TAT = System.nanoTime();
			long tot = prc.et_TAT-prc.st_TAT;
			tot_TAT = tot_TAT+tot;
			System.out.println(prc.pName+ "ends in "+ tot + " nano sec.");
			
		}
		flag=true;
	}

	@Override
	public void run() {
		
		int upto = wtq.size();
		for(int i=0 ; i<upto ; i++) {
			Process prc = wtq.get(0);
			try {Thread.sleep(prc.cpu_b);}catch(Exception e) {e.getStackTrace();}
			
			JOptionPane.showMessageDialog(null, "Input Any !");
						
			/*Waiting END time*/
			wet = System.nanoTime();
			wtt = wet-wst;
			tot_WT=tot_WT+wtt;
			
			wtq.remove(prc);
			
			/*End Turn Around Time*/
			prc.et_TAT = System.nanoTime();
			long tot = prc.et_TAT-prc.st_TAT;
			tot_TAT = tot_TAT+tot;
			System.out.println("*** PREEMPTIVE*** "+prc.pName+ "ends in "+ tot + " nano sec.");
		}	
		JOptionPane.showMessageDialog(null, "Preemptive Processes Done !" , "Info" , JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
	/*AVERAGE TIMINGS*/
	
	/*AVG Turn Around Time.*/
	public long avg_tat() throws Exception 	{	return tot_TAT/Nos_prcs;}
	
	/*AVG Waiting Time.*/
	public long avg_wt() throws Exception { return tot_WT/Nos_prcs;}
	
	/*AVG Response Time.*/
	public long avg_rt() throws Exception { tot_RT = tot_WT; return tot_RT/Nos_prcs; }
	
	/*AVG CPU Burst.*/
	public long avg_cpu() throws Exception {return tot_CPB/Nos_prcs; }
	
	/*All Parameters' Calculations.*/
	public void calculations() throws Exception{
		System.out.println("\n\nAVG Turn Around Time: "+(double)avg_tat()/1000000000+" sec" );
		System.out.println("AVG Response Time: "+(double)avg_rt()/1000000000+" sec." );
		System.out.println("AVG Waiting time: "+(double)avg_wt()/1000000000+" sec." );
		System.out.println("AVG_CPU_Burst: "+(double)avg_cpu()/1000+" sec.");	
		
		/*For Throughput*/
		double a = (double)avg_cpu()/1000;
		double tp = 1/a;
		System.out.println("Throughput : "+tp+ " processes/sec");
	}
	
	
}

public class CPU {

	public static void main(String args[]) {
//		Generator gen = new Generator(5, 3);
//		Thread t = new Thread(gen);
//		t.start();
//			
//		gen.exe();

	}
}
