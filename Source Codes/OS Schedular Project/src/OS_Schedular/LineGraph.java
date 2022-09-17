package OS_Schedular;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.chart.LineChart; 
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.XYChart; 
         
public class LineGraph extends Application { 
	static int jobs ;
	static double avgTat ;
	static double avgWat;
	static double avgRat;
	static double avgTap;
	static double avgCpu;
	
   @Override 
   public void start(Stage stage) {
      // Creating Axis            
      NumberAxis xAxis = new NumberAxis(-10, 50, 10); //Defining the x axis 
      xAxis.setLabel("Nos of Jobs");   
      NumberAxis yAxis = new NumberAxis(-10, 80, 1); //Defining the y axis 
      yAxis.setLabel("Timings (sec)"); 
        
      //Creating the line chart 
      LineChart linechart = new LineChart(xAxis, yAxis);  
        
      //Prepare XYChart.Series objects by setting data 
      XYChart.Series tat = new XYChart.Series(); 
      tat.setName("Average Turn Around time.");  
      tat.getData().add(new XYChart.Data(0, 0)); 
      tat.getData().add(new XYChart.Data(jobs, avgTat));
      
      XYChart.Series wat = new XYChart.Series(); 
      wat.setName("Average waiting time."); 
      wat.getData().add(new XYChart.Data(0, 0)); 
      wat.getData().add(new XYChart.Data(jobs, avgWat));
      
      XYChart.Series rat = new XYChart.Series(); 
      rat.setName("Average Response time."); 
      rat.getData().add(new XYChart.Data(0, 0)); 
      rat.getData().add(new XYChart.Data(jobs, avgRat));
      
      XYChart.Series tap = new XYChart.Series(); 
      tap.setName("Average Throughput time."); 
      tap.getData().add(new XYChart.Data(0, 0)); 
      tap.getData().add(new XYChart.Data(jobs, avgTap));
      
      XYChart.Series cpu = new XYChart.Series(); 
      cpu.setName("Average CPU Burst time."); 
      cpu.getData().add(new XYChart.Data(0, 0)); 
      cpu.getData().add(new XYChart.Data(jobs, avgCpu));
            
      //Setting the data to Line chart    
      linechart.getData().add(tat); 
      linechart.getData().add(wat);
      linechart.getData().add(rat);
      linechart.getData().add(tap);
      linechart.getData().add(cpu);
        
      //Creating a Group object  
      Group root = new Group(linechart); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 550, 400);  
      
      //Setting title to the Stage 
      stage.setTitle("Average Process Timings"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);
      stage.setResizable(false);
	   
      //Displaying the contents of the stage 
      stage.show();         
   } 
   
   public static void main(String args[]){ 
      launch(args); 
   } 
}