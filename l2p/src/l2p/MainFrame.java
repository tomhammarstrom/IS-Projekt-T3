package l2p;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.List;

public class MainFrame extends JFrame {

	private JPanel anslagstavlan;
	private JButton btnKnappiz = new JButton("Knappiz");
	private List list = new List();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initComponents();
	}
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 427);
		anslagstavlan = new JPanel();
		anslagstavlan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(anslagstavlan);
		anslagstavlan.setLayout(null);
		btnKnappiz.setBounds(82, 151, 97, 25);
		
		anslagstavlan.add(btnKnappiz);
		list.setBounds(294, 58, 318, 245);
		
		anslagstavlan.add(list);
	}
}
