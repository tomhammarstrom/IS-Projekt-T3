package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import tengil.Controller;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewStudentPanel extends JPanel{
	private Controller con;
	private String currentStudent;
	
	private JTextField civicField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JLabel civicLbl = new JLabel("Personnummer");
	private JLabel nameLbl = new JLabel("Namn");
	private JLabel addressLbl = new JLabel("Adress");
	private final JButton saveBtn = new JButton("Spara");
	private MainFrame mainFrame;
	
	
	public NewStudentPanel(Controller con, String civic, MainFrame mainFrame) {
		this.con = con;
		currentStudent = civic;
		this.mainFrame = mainFrame;
		
		initComponents();
		setVisible(true);
		
		if(currentStudent != null){
			try {
				existingData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void existingData() throws SQLException{
		
		ResultSet r = con.getStudent(currentStudent);
		
		while(r.next()){
			civicField.setText(r.getString("pnr"));
			nameField.setText(r.getString("name"));
			addressField.setText(r.getString("adr"));	
		}
		
	}
	

	
	
	private void initComponents(){
		addressField.setBounds(176, 160, 201, 22);
		addressField.setColumns(10);
		nameField.setBounds(176, 93, 201, 22);
		nameField.setColumns(10);
		civicField.setBounds(176, 33, 201, 22);
		civicField.setColumns(10);
		civicLbl.setBounds(12, 36, 152, 16);
		nameLbl.setBounds(12, 96, 152, 16);
		addressLbl.setBounds(12, 163, 152, 16);
		
		setBounds(397, 13, 389, 516);
		setLayout(null);
		
		add(civicField);
		add(nameField);
		add(addressField);
		add(civicLbl);
		add(nameLbl);
		add(addressLbl);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addStudent();
			}
		});
		saveBtn.setBounds(124, 430, 97, 25);
		
		add(saveBtn);
	}
	private void addStudent(){
		try {
			con.addStudent(civicField.getText(), nameField.getText(), addressField.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		mainFrame.populateList();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}