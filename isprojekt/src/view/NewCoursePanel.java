package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import tengil.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

@SuppressWarnings("serial")
public class NewCoursePanel extends JPanel {
	private Controller con;
	private String currentCourse; 
	private MainFrame mainFrame;
	
	private JTextField pointsField = new JTextField();
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField descrField = new JTextField();
	
	private JLabel idLbl = new JLabel("Kurs ID");
	private JLabel nameLbl = new JLabel("Namn");
	private JLabel pointsLbl = new JLabel("Poäng");
	private JLabel descrLbl = new JLabel("Beskrivning");
	private JLabel activeStudentsLbl = new JLabel("Aktifa studenter");
	private JLabel inactiveStudentsLbl = new JLabel("Studenter klarr");
	private JLabel numberOfALbl = new JLabel("New label");
	
	private DefaultListModel<String> activeStudentsListModel = new DefaultListModel<String>();
	private DefaultListModel<String> inactiveStudentsListModel = new DefaultListModel<String>();
	
	private JList<String> activeStudentsList = new JList<String>(activeStudentsListModel);
	private JList<String> inactiveStudentsList = new JList<String>(inactiveStudentsListModel);
	
	private JButton saveBtn = new JButton("Spara");
	private JButton deleteBtn = new JButton("Ta bort");
	private JButton showActiveStudentsButton = new JButton("Visa student");
	private JButton showInactiveStudentsButton = new JButton("Visa student");
	

	// konstruktor
	public NewCoursePanel(Controller con, String id, MainFrame mainFrame) {
		this.con = con;
		currentCourse = id;
		this.mainFrame = mainFrame;
		
		initComponents();
		
			try {
				existingData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	// hämtar data för öppnade kurser
	private void existingData() throws SQLException{
		if(currentCourse != null){	
			ResultSet r = con.getCourse(currentCourse);
		
			while(r.next()){
				idField.setText(r.getString("id").trim());
				nameField.setText(r.getString("name").trim());
				pointsField.setText(r.getString("point"));
				descrField.setText(r.getString("descr").trim());
			}
			r = con.getNotFinishedWithCourse(currentCourse);
			activeStudentsListModel.clear();
			while (r.next()){
				activeStudentsListModel.addElement(r.getString(1));
			}
			r = con.getFinishedWithCourse(currentCourse);
			inactiveStudentsListModel.clear();
			while (r.next()){
				inactiveStudentsListModel.addElement(r.getString(1));
			}
			add(deleteBtn);
			idField.setEditable(false);
			repaint();
		}
		 
	}
	
	//skapar alla grafiska komponenter
	private void initComponents(){
		setBounds(397, 13, 810, 516);
		setLayout(null);
		
		descrField.setBounds(179, 201, 140, 20);
		descrField.setColumns(10);
		nameField.setBounds(179, 82, 140, 20);
		nameField.setColumns(10);
		idField.setBounds(179, 28, 142, 20);
		idField.setColumns(10);
		pointsField.setBounds(179, 140, 140, 20);
		pointsField.setColumns(10);
		idLbl.setBounds(49, 31, 46, 14);
		nameLbl.setBounds(49, 85, 46, 14);
		descrLbl.setBounds(49, 204, 76, 14);
		pointsLbl.setBounds(49, 143, 46, 14);
		saveBtn.setBounds(288, 480, 89, 23);
		deleteBtn.setBounds(12, 479, 97, 25);
		activeStudentsList.setBounds(489, 33, 280, 188);
		inactiveStudentsList.setBounds(489, 280, 271, 188);
		activeStudentsLbl.setBounds(525, 13, 140, 16);
		inactiveStudentsLbl.setBounds(525, 259, 140, 16);
		showActiveStudentsButton.setBounds(564, 221, 128, 25);
		showInactiveStudentsButton.setBounds(568, 479, 124, 25);
		numberOfALbl.setBounds(49, 354, 140, 20);
		
		add(pointsField);		
		add(idField);		
		add(nameField);		
		add(descrField);
		add(idLbl);	
		add(nameLbl);	
		add(pointsLbl);	
		add(descrLbl);
		add(saveBtn);
		add(showActiveStudentsButton);
		add(numberOfALbl);
		
		add(activeStudentsList);		
		add(inactiveStudentsList);
		add(activeStudentsLbl);
		add(inactiveStudentsLbl);
		add(showInactiveStudentsButton);
		
		float dan = 0;
		try {
			dan = con.numberOfA(currentCourse);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			numberOfALbl.setText("Procent A: " + Float.toString(dan));
		} catch (ArithmeticException ad){
		}
		
		
		showActiveStudentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showStudent("active");
			}
		});
		
		showInactiveStudentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStudent("inactive");
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCourse();
			}
		});
				
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCourse();
			}
		});
		
	}
	
	//körs att addCourse() för att se till så att det finns poäng(int) och ett id
	private boolean validateInput(){
		if (con.validateNotNull(idField.getText()) && con.validateNotNull(pointsField.getText()) && con.validateNumbers(pointsField.getText())){
			return true;
		}
		else{
			return false;
		}
	}
	
	//lägger till eller ändrar en kurs
	private void addCourse(){
		if(validateInput()){
			int success = 0;
			try{
				if(currentCourse == null){
					success = con.addCourse(idField.getText(), nameField.getText(), descrField.getText(), Integer.parseInt(pointsField.getText()));
				}
				else{
					success = con.changeCourse(idField.getText(), nameField.getText(), descrField.getText(), Integer.parseInt(pointsField.getText()));
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(success != 0){
				try {
					mainFrame.populateList();
					currentCourse = idField.getText();
					existingData();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Kurs finns redan");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Var god fyll i ett id och ett poängantal (i siffror)");
		}
		
	}
	
	//tar bort en kurs
	private void deleteCourse(){
		try {
			con.removeCourse(currentCourse);
			mainFrame.populateList();
			currentCourse = null;
			mainFrame.clearPanel();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showStudent(String list){
		if(list.equals("active")){
			if(activeStudentsList.getSelectedValue() != null){
				mainFrame.openStudent(activeStudentsList.getSelectedValue().toString());
			}
		}
		
		else if (list.equals("inactive")){
			if(inactiveStudentsList.getSelectedValue() != null){
				mainFrame.openStudent(inactiveStudentsList.getSelectedValue().toString());
			}
			
		}
		
		
	}
}
