package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import tengil.Controller;
import javax.swing.JList;

public class MainFrame extends JFrame {
	private Controller con;
	

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JList list;
	private JList list_1;

	public MainFrame(Controller con) {
		this.con = con;
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1241, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 343, 516);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		list = new JList();
		list.setBounds(12, 13, 314, 419);
		panel.add(list);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		list_1 = new JList();
		list_1.setBounds(12, 13, 314, 421);
		panel_1.add(list_1);
	}
}
