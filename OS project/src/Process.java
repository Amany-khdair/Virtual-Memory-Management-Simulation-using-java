import java.util.ArrayList;
public  class Process implements Runnable{


    String pid;
    double startTime=-1;
    double finishTime = -1;
    double arrivalTime;
    double start;
    double dur;
    int size;
    double burstTime;
    double remainingTime;
    int numberOfFaults = 0;
    int pageLocation;   // where we r at each process file ( in it's page file ) 
    double waitTime;
    double turnaround;
    double elapsedtime;
    Page page[] = new Page[100];


    public Process(String pid, double start, double dur, int size) {
        this.pid = pid;
        this.start = start;
        this.dur = dur;
        this.size = size;
        this.remainingTime=dur;
    }

    public Process() {

    }
   
    
    @Override 
    public void run() {
    	System.out.println("pid :"+ pid + "  start : " + start + "   dur :" + dur +"  size :" + size );

    }
    @Override 
    public String toString() {
    	return "pid :"+ pid + "  start : " + start + "   dur :" + dur +"  size :" + size;
    }
	
}