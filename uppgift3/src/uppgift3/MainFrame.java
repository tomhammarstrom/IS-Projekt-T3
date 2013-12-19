package uppgift3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.List;

import javax.swing.JLabel;

import java.awt.Label;
import java.awt.Button;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;

	private JLabel excelLbl = new JLabel("Uppgift 1 Excel");
	private JLabel accessLbl = new JLabel("Uppgift 1 Access");
	private JLabel sqlLbl = new JLabel("Uppgift 1 SQL");
	private Label formLbl = new Label("Rapporter");
	
	private Button excelBtn = new Button("�ppna");
	private Button accessBtn = new Button("�ppna");
	private Button sqlBtn = new Button("�ppna");
	private Button formBtn = new Button("�ppna");
	
	private List excelList = new List();
	private List accessList = new List();
	private List sqlList = new List();
	private List formList = new List();


	public MainFrame() {
		initComponents();
	}
	
	private void fillExcelList(){
		excelList.add("");
	}
	
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1210, 504);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		excelList.setBounds(10, 69, 241, 324);
		accessList.setBounds(321, 69, 228, 324);
		sqlList.setBounds(593, 69, 211, 324);
		excelLbl.setBounds(49, 47, 147, 16);
		sqlLbl.setBounds(640, 47, 138, 16);
		accessLbl.setBounds(380, 47, 138, 16);
		formLbl.setBounds(882, 39, 70, 24);
		excelBtn.setBounds(84, 409, 79, 24);
		formList.setBounds(845, 70, 197, 323);
		accessBtn.setBounds(399, 409, 79, 24);
		sqlBtn.setBounds(661, 409, 79, 24);
		formBtn.setBounds(910, 409, 79, 24);
		
		contentPane.add(excelList);
		contentPane.add(accessList);
		contentPane.add(sqlList);
		contentPane.add(excelLbl);
		contentPane.add(accessLbl);
		contentPane.add(sqlLbl);
		contentPane.add(formList);
		contentPane.add(formLbl);
		contentPane.add(excelBtn);
		contentPane.add(accessBtn);
		contentPane.add(sqlBtn);
		contentPane.add(formBtn);
	}
}
