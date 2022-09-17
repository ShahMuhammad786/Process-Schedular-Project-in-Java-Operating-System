package OS_Schedular;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import java.awt.Canvas;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;

public class UI {

	private JFrame frmOperatingSystemSchedular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmOperatingSystemSchedular.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOperatingSystemSchedular = new JFrame();
		frmOperatingSystemSchedular.getContentPane().setBackground(new Color(153, 51, 153));
		frmOperatingSystemSchedular.setResizable(false);
		frmOperatingSystemSchedular.setTitle("Operating System Schedular [FIFO]");
		frmOperatingSystemSchedular.setBounds(100, 100, 754, 472);
		frmOperatingSystemSchedular.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOperatingSystemSchedular.getContentPane().setLayout(null);
		
		JButton btnStartSchedular = new JButton("Start Schedular");
		btnStartSchedular.setForeground(Color.WHITE);
		btnStartSchedular.setBackground(new Color(153, 51, 153));
		btnStartSchedular.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStartSchedular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home hm = new Home();
				hm.main(null);
				
				frmOperatingSystemSchedular.dispose();
			}
		});
		btnStartSchedular.setBounds(0, 401, 749, 31);
		frmOperatingSystemSchedular.getContentPane().add(btnStartSchedular);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("E:\\Eclipse\\javaProjects\\OS Schedular Project\\src\\OS_Schedular\\img\\os3.jpg"));
		label.setBounds(0, 32, 749, 400);
		frmOperatingSystemSchedular.getContentPane().add(label);
	}
}
