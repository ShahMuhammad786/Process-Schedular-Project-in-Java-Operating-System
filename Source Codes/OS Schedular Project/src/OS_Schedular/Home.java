package OS_Schedular;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class Home implements Runnable{
	private JFrame frmHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
						
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}
	
	/*Global fields*/
	JLabel NosResults, preResults ;
	JPanel processCreation , statusPanel , graphPanel; 
	int min, max;
	JProgressBar pb;
	Generator gen;
	
	JLabel avgWT, avgRT, avgCPU, avgTP, avgTAT;
	static String tat="" , wat="", rt="",cpu="",tp="";
//	static boolean done= false;
	
	private JTextField num_pc_in;
	private JTextField pre_pc_in;
	
	static int inA, inB;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHome = new JFrame();
		frmHome.setResizable(false);
		frmHome.setAlwaysOnTop(true);
		frmHome.setTitle("Home\r\n");
		frmHome.getContentPane().setBackground(UIManager.getColor("CheckBox.darkShadow"));
		frmHome.setBounds(100, 100, 816, 486);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		
/* ************************ Process Creation Panel ********************************** */
		processCreation = new JPanel();
		processCreation.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		processCreation.setBackground(new Color(192, 192, 192));
		processCreation.setBounds(268, 11, 522, 425);
		frmHome.getContentPane().add(processCreation);
		processCreation.setLayout(null);
		processCreation.setVisible(false);
		
		JLabel lbl_pc = new JLabel("Process Creation");
		lbl_pc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbl_pc.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_pc.setBounds(180, 52, 168, 21);
		processCreation.add(lbl_pc);
		
		JPanel pc_contents = new JPanel();
		pc_contents.setBackground(new Color(230, 230, 250));
		pc_contents.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pc_contents.setBounds(75, 84, 359, 271);
		processCreation.add(pc_contents);
		pc_contents.setLayout(null);
		
		JLabel num_pc = new JLabel("Nos Of Process: ");
		num_pc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		num_pc.setBounds(45, 68, 142, 23);
		pc_contents.add(num_pc);
		
		num_pc_in = new JTextField("");
		num_pc_in.setBounds(215, 70, 86, 20);
		pc_contents.add(num_pc_in);
		num_pc_in.setColumns(10);
		
		JLabel pre_pc = new JLabel("Preemptive Process:");
		pre_pc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pre_pc.setBounds(45, 120, 142, 23);
		pc_contents.add(pre_pc);
		
		pre_pc_in = new JTextField("");
		pre_pc_in.setColumns(10);
		pre_pc_in.setBounds(215, 122, 86, 20);
		pc_contents.add(pre_pc_in);
		
		JButton btnCreate = new JButton("Ok !");
		btnCreate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCreate.setBackground(new Color(175, 238, 238));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
				/*Assigning*/
				if(! (num_pc_in.getText().equals(""))) {
					int pre = 0 ;
					int Pno = Integer.parseInt(num_pc_in.getText());
					if(! pre_pc_in.getText().equals("")) {pre = Integer.parseInt(pre_pc_in.getText());}
					
					String numPcExist=NosResults.getText();
					String prePcExist=preResults.getText();
					
					/*Converting to integer for total Number of Processes.*/
					int tot_pc=0;
					int tot_pre=0;
					if(! numPcExist.equals("")) {
						tot_pc = Pno+(Integer.parseInt(numPcExist));					
					}else {					
						tot_pc=Pno;
					}
					if(! prePcExist.equals("")) {
						tot_pre = pre+(Integer.parseInt(prePcExist));
					}else {
						tot_pre=pre;
					}
					
					NosResults.setText(tot_pc+"");
					preResults.setText(tot_pre+"");
					
					processCreation.setVisible(false);
					statusPanel.setVisible(true);
					graphPanel.setVisible(true);
										
					
				}else {
					num_pc_in.setBackground(new Color(225,0,0));
					pre_pc_in.setBackground(new Color(225,0,0));
					JOptionPane.showMessageDialog(btnCreate, "Fill the Highlited Feild at least !",
							"Error", JOptionPane.ERROR_MESSAGE);
					num_pc_in.setBackground(new Color(225,225,225));
					pre_pc_in.setBackground(new Color(225,225,225));
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate.setBounds(133, 196, 89, 23);
		pc_contents.add(btnCreate);
		
		JLabel warning = new JLabel("Enter values in Integer !\r\n");
		warning.setHorizontalAlignment(SwingConstants.CENTER);
		warning.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		warning.setBounds(109, 21, 153, 14);
		pc_contents.add(warning);
		
		JButton btnX = new JButton("");
		btnX.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processCreation.setVisible(false);
				statusPanel.setVisible(true);
				graphPanel.setVisible(true);
			}
		});
		btnX.setForeground(new Color(192, 192, 192));
		btnX.setIcon(new ImageIcon("E:\\Eclipse\\javaProjects\\OS Schedular Project\\src\\OS_Schedular\\img\\close.png"));
		btnX.setBackground(new Color(255, 0, 0));
		btnX.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnX.setBounds(489, 11, 23, 21);
		processCreation.add(btnX);
		
/* *************************** Processes Creation Panel END *************************************** */ 
		
		
		
		JPanel verticleLine = new JPanel();
		verticleLine.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		verticleLine.setBackground(new Color(128, 128, 128));
		verticleLine.setBounds(268, 11, 2, 425);
		frmHome.getContentPane().add(verticleLine);
		
		JPanel horizantalLine = new JPanel();
		horizantalLine.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		horizantalLine.setBackground(new Color(128, 128, 128));
		horizantalLine.setBounds(268, 110, 522, 2);
		frmHome.getContentPane().add(horizantalLine);
		
		
/* ******************* GRAPHING PANEL ******************************/
		
		graphPanel = new JPanel();
		graphPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
//		graphPanel.setVisible(false); //Temporary********************************
		graphPanel.setBackground(new Color(192, 192, 192));
		graphPanel.setBounds(278, 120, 512, 316);
		frmHome.getContentPane().add(graphPanel);
		graphPanel.setLayout(null);
				
		JLabel lblGraphingParameters = new JLabel("Graphing Parameters... [Results]");
		lblGraphingParameters.setBounds(10, 11, 177, 14);
		graphPanel.add(lblGraphingParameters);
		
		JLabel lblAverageTurnAround = new JLabel("Average Turn Around Time :");
		lblAverageTurnAround.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAverageTurnAround.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAverageTurnAround.setBounds(49, 61, 203, 26);
		graphPanel.add(lblAverageTurnAround);
		
		JLabel lblAverageWaitingTime = new JLabel("Average Waiting Time :");
		lblAverageWaitingTime.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAverageWaitingTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAverageWaitingTime.setBounds(49, 98, 203, 26);
		graphPanel.add(lblAverageWaitingTime);
		
		JLabel lblAverageResponseTime = new JLabel("Average Response Time :");
		lblAverageResponseTime.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAverageResponseTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAverageResponseTime.setBounds(49, 135, 203, 26);
		graphPanel.add(lblAverageResponseTime);
		
		JLabel lblAverageThroughputsec = new JLabel("Average ThroughPut(jobs/sec) :");
		lblAverageThroughputsec.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAverageThroughputsec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAverageThroughputsec.setBounds(49, 176, 203, 26);
		graphPanel.add(lblAverageThroughputsec);
		
		JLabel lblAverageCpuBurst = new JLabel("Average CPU Burst :");
		lblAverageCpuBurst.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAverageCpuBurst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAverageCpuBurst.setBounds(49, 219, 203, 26);
		graphPanel.add(lblAverageCpuBurst);
		
		avgTAT = new JLabel("0000");
		avgTAT.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		avgTAT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		avgTAT.setBounds(301, 61, 131, 26);
		graphPanel.add(avgTAT);
		
		avgWT = new JLabel("0000");
		avgWT.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		avgWT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		avgWT.setBounds(301, 98, 131, 26);
		graphPanel.add(avgWT);
		
		avgRT = new JLabel("0000");
		avgRT.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		avgRT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		avgRT.setBounds(301, 135, 131, 26);
		graphPanel.add(avgRT);
		
		avgTP = new JLabel("0000");
		avgTP.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		avgTP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		avgTP.setBounds(301, 176, 131, 26);
		graphPanel.add(avgTP);
		
		avgCPU = new JLabel("0000");
		avgCPU.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		avgCPU.setFont(new Font("Tahoma", Font.PLAIN, 14));
		avgCPU.setBounds(301, 219, 131, 26);
		graphPanel.add(avgCPU);
		
		JButton btnGraphIt = new JButton("Graph it !");
		btnGraphIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LineGraph lg = new LineGraph();
				int a = Integer.parseInt(NosResults.getText());
				int b = Integer.parseInt(preResults.getText());
				int c = a+b ;
				lg.jobs = c;
				
				lg.avgTat = Double.parseDouble(avgTAT.getText());
				lg.avgWat = Double.parseDouble(avgWT.getText()); 
				lg.avgRat = Double.parseDouble(avgRT.getText()); 
				lg.avgTap = Double.parseDouble(avgTP.getText()); 
				lg.avgCpu = Double.parseDouble(avgCPU.getText());
				
				lg.main(null);
				
			}
		});
		btnGraphIt.setBackground(new Color(175, 238, 238));
		btnGraphIt.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGraphIt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGraphIt.setBounds(201, 272, 116, 33);
		graphPanel.add(btnGraphIt);
		
		JButton btnShowResults = new JButton("Show Results");
		btnShowResults.setBackground(new Color(175, 238, 238));
		btnShowResults.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnShowResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avgTAT.setText(Home.tat);
				avgWT.setText(Home.wat);
				avgRT.setText(Home.rt);
				avgTP.setText(Home.tp);
				avgCPU.setText(Home.cpu);
				
			}
		});
		btnShowResults.setBounds(233, 7, 116, 18);
		graphPanel.add(btnShowResults);
		
			
				
		
/* ***************************** START: Status Panel ********************************************** */
		statusPanel = new JPanel();
		statusPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		statusPanel.setBackground(new Color(192, 192, 192));
		statusPanel.setBounds(280, 11, 510, 88);
		frmHome.getContentPane().add(statusPanel);
		statusPanel.setLayout(null);
		
		JButton btnStart = new JButton("Start Processes' Creation and Processings.");
		btnStart.setBackground(new Color(175, 238, 238));
		btnStart.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(!(NosResults.getText().equals("") && preResults.getText().equals(""))) {
					
					Home.inA=Integer.parseInt(NosResults.getText());
					Home.inB=Integer.parseInt(preResults.getText());
							
					Thread t = new Thread(new Home());					
					t.start();
					JOptionPane.showMessageDialog(null, "Start Processes...", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				}else {
					JOptionPane.showMessageDialog( btnStart, "Please ! Create Processes First !" , "Warning", JOptionPane.WARNING_MESSAGE, null);
				}	
			}
		});
		btnStart.setBounds(65, 45, 377, 32);
		statusPanel.add(btnStart);
		
		JLabel lblThisWillActivate = new JLabel("This will activate Generate the processes auto \r\nand will schedule auto.");
		lblThisWillActivate.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisWillActivate.setBounds(28, 11, 426, 14);
		statusPanel.add(lblThisWillActivate);
		
		
		
		
/* ************************* END: Status Panel ************************************************ */
		
		
		
/* ************************* START: Side Menu Panel************************************************* */
		JPanel sideMenuPanel = new JPanel();
		sideMenuPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		sideMenuPanel.setBackground(new Color(192, 192, 192));
		sideMenuPanel.setBounds(10, 11, 248, 425);
		frmHome.getContentPane().add(sideMenuPanel);
		sideMenuPanel.setLayout(null);
		
		JButton btnCreateProcess = new JButton("  Create Process  ");
		btnCreateProcess.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCreateProcess.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCreateProcess.setForeground(new Color(0, 0, 0));
		btnCreateProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusPanel.setVisible(false);
				graphPanel.setVisible(false);
				processCreation.setVisible(true);
				
			}
		});
		
		btnCreateProcess.setBounds(22, 26, 175, 51);
		sideMenuPanel.add(btnCreateProcess);
		btnCreateProcess.setHorizontalAlignment(SwingConstants.RIGHT);
		btnCreateProcess.setIcon(new ImageIcon("E:\\Eclipse\\javaProjects\\OS Schedular Project\\src\\OS_Schedular\\img\\add.png"));
		btnCreateProcess.setBackground(new Color(175, 238, 238));
		btnCreateProcess.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		JLabel processes = new JLabel("Nos. of Processes: ");
		processes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		processes.setBounds(22, 115, 148, 22);
		sideMenuPanel.add(processes);
		processes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		NosResults = new JLabel("");
		NosResults.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		NosResults.setBounds(180, 115, 55, 22);
		sideMenuPanel.add(NosResults);
		NosResults.setHorizontalAlignment(SwingConstants.CENTER);
		NosResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
			
		JLabel preemptive = new JLabel("Preemptive Processes: ");
		preemptive.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		preemptive.setBounds(22, 162, 148, 22);
		sideMenuPanel.add(preemptive);
		preemptive.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		preResults = new JLabel("");
		preResults.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		preResults.setBounds(183, 162, 55, 22);
		sideMenuPanel.add(preResults);
		preResults.setHorizontalAlignment(SwingConstants.CENTER);
		preResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel AttributesPanel = new JPanel();
		AttributesPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		AttributesPanel.setBackground(new Color(176, 224, 230));
		AttributesPanel.setBounds(22, 261, 216, 147);
		sideMenuPanel.add(AttributesPanel);
		AttributesPanel.setLayout(null);
		
		JLabel waiting = new JLabel("WaitingTime");
		waiting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		waiting.setBounds(10, 16, 119, 17);
		AttributesPanel.add(waiting);
		
		JLabel Response = new JLabel("Response Time");
		Response.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Response.setBounds(10, 44, 119, 17);
		AttributesPanel.add(Response);
		
		JLabel Throughput = new JLabel("Throughput ");
		Throughput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Throughput.setBounds(10, 72, 119, 17);
		AttributesPanel.add(Throughput);
		
		JLabel turnaround = new JLabel("TurnAround Time");
		turnaround.setFont(new Font("Tahoma", Font.PLAIN, 14));
		turnaround.setBounds(10, 99, 119, 17);
		AttributesPanel.add(turnaround);
		
		JLabel utilization = new JLabel("CPU Burst");
		utilization.setFont(new Font("Tahoma", Font.PLAIN, 14));
		utilization.setBounds(10, 124, 119, 17);
		AttributesPanel.add(utilization);
		
		JPanel wait = new JPanel();
		wait.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		wait.setBackground(new Color(255, 165, 0));
		wait.setBounds(161, 11, 45, 22);
		AttributesPanel.add(wait);
		
		JPanel response = new JPanel();
		response.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		response.setBackground(new Color(50, 205, 50));
		response.setBounds(161, 40, 45, 22);
		AttributesPanel.add(response);
		
		JPanel throughput = new JPanel();
		throughput.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		throughput.setBackground(new Color(30, 144, 255));
		throughput.setBounds(161, 68, 45, 22);
		AttributesPanel.add(throughput);
		
		JPanel turnaround_1 = new JPanel();
		turnaround_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		turnaround_1.setBackground(new Color(255, 0, 0));
		turnaround_1.setBounds(161, 94, 45, 22);
		AttributesPanel.add(turnaround_1);
		
		JPanel utilization_1 = new JPanel();
		utilization_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		utilization_1.setBackground(new Color(0, 0, 255));
		utilization_1.setBounds(161, 119, 45, 22);
		AttributesPanel.add(utilization_1);
		
		JLabel lblAverageTimings = new JLabel("Average Timings");
		lblAverageTimings.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageTimings.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblAverageTimings.setBounds(70, 236, 113, 14);
		sideMenuPanel.add(lblAverageTimings);
		
		JButton refresh = new JButton("");
		refresh.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		refresh.setBackground(new Color(176, 224, 230));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frmHome.setVisible(false);
				frmHome.setVisible(true);
								
				NosResults.setText("");
				preResults.setText("");
				
				Home.tat=Home.wat=Home.rt=Home.cpu=Home.tp="0.00";
				avgTAT.setText("");
				avgRT.setText("");
				avgWT.setText("");
				avgCPU.setText("");
				avgTP.setText("");
				
			}
		});
		refresh.setIcon(new ImageIcon("E:\\Eclipse\\javaProjects\\OS Schedular Project\\src\\OS_Schedular\\img\\refresh.png"));
		refresh.setBounds(207, 26, 31, 51);
		sideMenuPanel.add(refresh);
		
		/* END : de Menu Panel */
	}

	@Override
	public void run() {			
		Generator gen = new Generator(Home.inA,Home.inB);
		Thread t = new Thread(gen);
		t.start();		
		
		gen.exe();
		if(gen.flag==true) {
			JOptionPane.showMessageDialog(null, "Non-Preemptive Processes Done !" , "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		try {
			Home.tat= (double)gen.avg_tat()/1000000000+"";
			Home.wat= (double)gen.avg_wt()/1000000000+"" ;
			Home.rt = (double)gen.avg_rt()/1000000000+"" ;
			Home.cpu = (double)gen.avg_cpu()/1000+"" ;
			
			/*For Throughput*/
			double a = (double)gen.avg_cpu()/1000;
			double tp = 1/a;
			Home.tp = tp+"";
		
		}catch(Exception exc) {
			JOptionPane.showMessageDialog(null, "Enter valid values !"," Warning" , JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
}

