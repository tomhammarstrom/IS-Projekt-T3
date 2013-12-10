package view;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import tengil.Controller;

import javax.swing.JList;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private Controller con;
	

	private JPanel contentPane = new JPanel();
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	
	private DefaultListModel studentListModel = new DefaultListModel();
	
	private JList list = new JList(studentListModel);
	private JList list_1 = new JList();
	

	public MainFrame(Controller con) {
		this.con = con;
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1241, 589);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 343, 516);
		contentPane.add(tabbedPane);
		
		
		tabbedPane.addTab("Studenter", null, panel, null);
		panel.setLayout(null);
		
		
		list.setBounds(12, 13, 314, 419);
		panel.add(list);
		
		
		tabbedPane.addTab("Kurser", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		list_1.setBounds(12, 13, 314, 421);
		panel_1.add(list_1);
		
	
	}



}
