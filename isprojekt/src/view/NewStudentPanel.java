package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import tengil.Controller;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

@SuppressWarnings("serial")
public class NewStudentPanel extends JPanel{
	private Controller con;
	private String currentStudent = null;
	private MainFrame mainFrame;
	
	private JTextField civicField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField addressField = new JTextField();
	
	private JLabel civicLbl = new JLabel("Personnummer");
	private JLabel nameLbl = new JLabel("Namn");
	private JLabel addressLbl = new JLabel("Adress");
	private JLabel currentCoursesLabel = new JLabel("Alla kurser huru elefen studerar");
	private JLabel lblAfslutatKurz = new JLabel("Afslutat kurz");
	
	private JButton saveBtn = new JButton("Spara");
	private JButton deleteBtn = new JButton("Ta bort");
	private JButton btnBetygPls = new JButton("betyg pls");
	
	private DefaultListModel<String> activeCoursesModel = new DefaultListModel<String>();
	private DefaultListModel<String> inactiveCoursesModel = new DefaultListModel<String>();
	
	private JList<String> activeCoursesList = new JList<String>(activeCoursesModel);
	private JList<String> inactiveCoursesList = new JList<String>(inactiveCoursesModel);
	
	
	
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
			showHiddenComponents();
			
			while(r.next()){
				civicField.setText(r.getString("pnr"));
				nameField.setText(r.getString("name"));
				addressField.setText(r.getString("adr"));
			}
			r.close();
			
			r = con.getCoursesForStudent(currentStudent);
			
			activeCoursesModel.clear();
			
			r = con.getCoursesForStudent(currentStudent);
			
			while(r.next()){
				activeCoursesModel.addElement(r.getString(2));
				
			}
			
			inactiveCoursesModel.clear();
			r = con.getFinishedCoursesForStudent(currentStudent);
			while(r.next()){
				inactiveCoursesModel.addElement(r.getString(2));
				
			}
		}
	}
	
	private void showHiddenComponents(){
		add(deleteBtn);
		add(inactiveCoursesList);
		add(btnBetygPls);
		add(lblAfslutatKurz);
		add(activeCoursesList);
		add(currentCoursesLabel);
		repaint();
	}

	


	private void initComponents(){		
		setBounds(397, 13, 810, 516);
		setLayout(null);
		
		addressField.setBounds(176, 160, 201, 22);
		addressField.setColumns(10);
		nameField.setBounds(176, 93, 201, 22);
		nameField.setColumns(10);
		civicField.setBounds(176, 33, 201, 22);
		civicField.setColumns(10);
		civicLbl.setBounds(12, 36, 152, 16);
		nameLbl.setBounds(12, 96, 152, 16);
		addressLbl.setBounds(12, 163, 152, 16);
		currentCoursesLabel.setBounds(492, 36, 269, 16);
		activeCoursesList.setBounds(449, 65, 303, 152);		
		lblAfslutatKurz.setBounds(541, 282, 164, 16);
		inactiveCoursesList.setBounds(461, 317, 300, 152);
		btnBetygPls.setBounds(541, 222, 97, 25);
		deleteBtn.setBounds(12, 478, 97, 25);
		saveBtn.setBounds(280, 478, 97, 25);
		
		add(civicField);
		add(nameField);
		add(addressField);
		add(civicLbl);
		add(nameLbl);
		add(addressLbl);
		add(saveBtn);
		
		
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addStudent();
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeStudent();
			}
		});
		
		btnBetygPls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishCourse();
			}
		});
		
		
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
	
	private void finishCourse(){
		// skit
	}
	

}