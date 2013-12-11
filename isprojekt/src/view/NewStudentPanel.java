package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import tengil.Controller;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class NewStudentPanel extends JPanel{
	private Controller con;
	private String currentStudent = null;
	private String intent = null;
	
	private JTextField civicField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JLabel civicLbl = new JLabel("Personnummer");
	private JLabel nameLbl = new JLabel("Namn");
	private JLabel addressLbl = new JLabel("Adress");
	private final JButton saveBtn = new JButton("Spara");
	private MainFrame mainFrame;
	private JButton deleteBtn = new JButton("Ta bort");
	private final JLabel lblAllaKurserHuru = new JLabel("Alla kurser huru elefen studerar");
	private final JList activeCoursesList = new JList();
	private final JLabel lblAfslutatKurz = new JLabel("Afslutat kurz");
	private final JList inactiveCoursesList = new JList();
	private final JButton btnBetygPls = new JButton("betyg pls");
	
	
	public NewStudentPanel(Controller con, String civic, MainFrame mainFrame) {
		this.con = con;
		currentStudent = civic;
		this.mainFrame = mainFrame;
		
		initComponents();
		setVisible(true);
		
		try {
			existingData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		

	
	private void existingData() throws SQLException{
		if(currentStudent != null){
			civicField.setEditable(false);
			ResultSet r = con.getStudent(currentStudent);
			add(deleteBtn);
			repaint();
			
			while(r.next()){
				civicField.setText(r.getString("pnr"));
				nameField.setText(r.getString("name"));
				addressField.setText(r.getString("adr"));
				
			}
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
		
		setBounds(397, 13, 810, 516);
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
		saveBtn.setBounds(280, 478, 97, 25);
		
		add(saveBtn);
		lblAllaKurserHuru.setBounds(492, 36, 269, 16);
		
		add(lblAllaKurserHuru);
		activeCoursesList.setBounds(449, 65, 303, 152);
		
		add(activeCoursesList);
		lblAfslutatKurz.setBounds(541, 282, 164, 16);
		
		add(lblAfslutatKurz);
		inactiveCoursesList.setBounds(461, 317, 300, 152);
		
		add(inactiveCoursesList);
		btnBetygPls.setBounds(541, 222, 97, 25);
		
		add(btnBetygPls);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeStudent();
			}
		});
		deleteBtn.setBounds(12, 478, 97, 25);
		
		
	}
	
	private boolean validateInput(){
		if(con.validateNotNull(civicField.getText())){
			return true;
		}
		else{
			return false;
		}
	}
	private void addStudent(){
		if(validateInput()){
			int success = 0;
			try {
				if(currentStudent == null){
					success = con.addStudent(civicField.getText(), nameField.getText(), addressField.getText());
				}
				else{
					success = con.changeStudent(civicField.getText(), nameField.getText(), addressField.getText());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (success != 0){
				try {
					mainFrame.populateList();
					currentStudent = civicField.getText();
					existingData();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else{
				JOptionPane.showMessageDialog(null, "Studenten finns redan");
			}
				}
		else{
			JOptionPane.showMessageDialog(null, "Var god fyll i ett personnummer");
		}
		
	}
	
	private void removeStudent(){
		try {
			con.removeStudent(currentStudent);
			mainFrame.populateList();
			currentStudent = null;
			mainFrame.clearPanel();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}