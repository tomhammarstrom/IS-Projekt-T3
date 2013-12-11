package view;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import tengil.Controller;

import javax.swing.JList;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private Controller con;
	

	private JPanel contentPane = new JPanel();
	private JPanel studentTabPanel = new JPanel();
	private JPanel courseTabPanel = new JPanel();
	
	private DefaultListModel studentListModel = new DefaultListModel();
	
	private JList list = new JList(studentListModel);
	private JList list_1 = new JList();
	private final JButton btnLggTillStudent = new JButton("L\u00E4gg till student");
	private final JButton btnLggTillKurs = new JButton("L\u00E4gg till kurs");
	private final JPanel panel = new JPanel();
	

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
		
		
		tabbedPane.addTab("Studenter", null, studentTabPanel, null);
		tabbedPane.addTab("Kurser", null, courseTabPanel, null);
		
		studentTabPanel.setLayout(null);
		courseTabPanel.setLayout(null);
		
		list.setBounds(12, 13, 314, 419);
		list_1.setBounds(12, 13, 314, 421);
		
		studentTabPanel.add(list);
		btnLggTillStudent.setBounds(87, 448, 152, 25);
		
		studentTabPanel.add(btnLggTillStudent);
		courseTabPanel.add(list_1);
		btnLggTillKurs.setBounds(98, 448, 124, 25);
		
		courseTabPanel.add(btnLggTillKurs);
		panel.setBounds(397, 13, 389, 516);
		
		
		contentPane.add(panel);
	}
}