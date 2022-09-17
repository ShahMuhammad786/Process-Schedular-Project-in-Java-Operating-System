package OS_Schedular;

public class Process {

	public String pName; 
	public long cpu_b;
	public boolean isPreemptive;  
	
	public long st_TAT;
	public long et_TAT;
	 
	
	/*For simple processes*/
	Process(String name)
	{ 
		pName = name;
	    st_TAT = System.nanoTime();
	    cpu_b = 2000+(long)(Math.random()*(4000-2000)+1);
	    isPreemptive = false;
	}
	
	/*For preemptive processes*/
	Process(String name, boolean isPre)
	{ 
		pName = name;
	    st_TAT = System.nanoTime();
	    cpu_b = 2000+(long)(Math.random()*(4000-2000)+1);
	    isPreemptive = isPre;
	}
	
}
