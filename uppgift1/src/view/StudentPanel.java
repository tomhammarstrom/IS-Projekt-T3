package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JComboBox;

import controller.Controller;

@SuppressWarnings("serial")
public class StudentPanel extends JPanel{
	private Controller con;
	private String currentStudent = null;
	private MainFrame mainFrame;
	
	private JTextField civicField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField addressField = new JTextField();
	
	private JLabel civicLbl = new JLabel("Personnummer");
	private JLabel nameLbl = new JLabel("Namn");
	private JLabel addressLbl = new JLabel("Adress");
	private JLabel activeCoursesLabel = new JLabel("Pågående kurser");
	private JLabel inactiveCoursesLabel = new JLabel("Avslutade kurser");
	private JLabel startNewCourseLbl = new JLabel("Välj kurs:");
	private JLabel gradeLabel = new JLabel("Betyg:");
	
	private JButton saveBtn = new JButton("Spara");
	private JButton deleteBtn = new JButton("Ta bort");
	private JButton finishCourseButton = new JButton("Kurs klar");
	private JButton cancelCourseButton = new JButton("Avregistera");
	private JButton startNewCourseButton = new JButton("Påbörja ny kurs");
	
	private DefaultListModel<String> activeCoursesModel = new DefaultListModel<String>();
	private DefaultListModel<String> inactiveCoursesModel = new DefaultListModel<String>();
	
	private JList<String> activeCoursesList = new JList<String>(activeCoursesModel);
	private JList<String> inactiveCoursesList = new JList<String>(inactiveCoursesModel);
	private JComboBox<String> gradeComboBox = new JComboBox<String>();
	private JComboBox<String> coursesComboBox = new JComboBox<String>();
	
	private ArrayList<String> activeCoursesRef = new ArrayList<String>();
	private ArrayList<String> inactiveCoursesRef = new ArrayList<String>();
	private ArrayList<String> startNewCourseRef = new ArrayList<String>();
	
	
	
	// Konstruktur
	public StudentPanel(Controller con, String civic, MainFrame mainFrame) {
		this.con = con;
		currentStudent = civic;
		this.mainFrame = mainFrame;
		
		initComponents();
		
		try {
			existingData();
		} catch (SQLException e) {
			System.out.println("problem med att ladda existerande data för student");
			e.printStackTrace();
		}
	}
	
	//värden och reset för betygslistan
	private void gradeComboValues(){
		gradeComboBox.removeAllItems();
		gradeComboBox.addItem("A");
		gradeComboBox.addItem("B");
		gradeComboBox.addItem("C");
		gradeComboBox.addItem("D");
		gradeComboBox.addItem("E");
		gradeComboBox.addItem("U");
	}

	// laddar information om student om den öppnas från listan. Laddar även pågående och avslutade kurser
	private void existingData() throws SQLException{
		gradeComboValues();

		if(currentStudent != null){
			civicField.setEditable(false);
			ResultSet r = con.getStudent(currentStudent);
			showHiddenComponents();
			
			while(r.next()){
				civicField.setText(r.getString("pnr").trim());
				nameField.setText(r.getString("name").trim());
				addressField.setText(r.getString("adr").trim());
			}
			
			r = con.getCoursesForStudent(currentStudent);
			activeCoursesModel.clear();
			
			while(r.next()){
				String id = r.getString(2).trim();
				String name = r.getString(3).trim();
				int points = r.getInt(4);
				activeCoursesModel.addElement(id + ": " + name + " - (" + points + "p)");
				activeCoursesRef.add(id);
			}
			
			inactiveCoursesModel.clear();
			r = con.getFinishedCoursesForStudent(currentStudent);
			
			while(r.next()){
				String id = r.getString(2).trim();
				String name = r.getString(3).trim();
				String grade = r.getString(4).trim();
				int points = r.getInt(5);
				inactiveCoursesModel.addElement(id + ": " + name + " - (" + points +"p)" + " - Betyg: " + grade);
				inactiveCoursesRef.add(id);
			}
			
			r = con.getCourses();
			coursesComboBox.removeAllItems();
			
			while (r.next()){
				String id = r.getString(1).trim();
				String name = r.getString(2).trim();
				int points = r.getInt(3);
				coursesComboBox.addItem(id + ": " + name + " (" + points + "p)");
				startNewCourseRef.add(id);
			}
			
		}
	}
	
	// visar extraknappar för nyskapade eller existerande studenter
	private void showHiddenComponents(){
		add(deleteBtn);
		add(inactiveCoursesList);
		add(finishCourseButton);
		add(inactiveCoursesLabel);
		add(activeCoursesList);
		add(activeCoursesLabel);
		add(cancelCourseButton);
		add(gradeComboBox);
		add(startNewCourseButton);
		add(startNewCourseLbl);
		add(coursesComboBox);
		add(gradeLabel);
		
		repaint();
	}

	

	// skapar alla grafiska komponenter, körs av konstruktorn
	private void initComponents(){		
		setBounds(397, 13, 810, 516);
		setLayout(null);
		setVisible(true);

		addressField.setBounds(176, 160, 201, 22);
		addressField.setColumns(10);
		nameField.setBounds(176, 93, 201, 22);
		nameField.setColumns(10);
		civicField.setBounds(176, 33, 201, 22);
		civicField.setColumns(10);
		civicLbl.setBounds(12, 36, 152, 16);
		nameLbl.setBounds(12, 96, 152, 16);
		addressLbl.setBounds(12, 163, 152, 16);
		activeCoursesLabel.setBounds(449, 13, 269, 16);
		activeCoursesList.setBounds(446, 42, 303, 152);		
		inactiveCoursesLabel.setBounds(445, 287, 164, 16);
		inactiveCoursesList.setBounds(449, 316, 300, 187);
		finishCourseButton.setBounds(636, 242, 113, 25);
		deleteBtn.setBounds(12, 478, 97, 25);
		saveBtn.setBounds(280, 478, 97, 25);
		gradeComboBox.setBounds(703, 207, 46, 22);
		cancelCourseButton.setBounds(449, 207, 133, 25);
		startNewCourseButton.setBounds(23, 388, 164, 25);
		startNewCourseLbl.setBounds(23, 318, 132, 14);
		coursesComboBox.setBounds(12, 345, 181, 31);
		gradeLabel.setBounds(621, 207, 56, 16);
		
		add(civicField);
		add(nameField);
		add(addressField);
		add(civicLbl);
		add(nameLbl);
		add(addressLbl);
		add(saveBtn);
		
		
		startNewCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNewCourse();
			}	
		});		
		
		cancelCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelCourse();
			}
		});
		
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
		
		finishCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishCourse();
			}
		});
		
		
	}
	
	// körs av addStudent() när man försöker spara för att se till så att det finns ett personnummer
	private boolean validateInput(){
		if(con.validateNotNull(civicField.getText())){
			return true;
		}
		else{
			return false;
		}
	}
	
	//lägger till eller uppdaterar en student
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
				System.out.println("problem med att skapa/uppdatera student");
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
	
	// tar bort en student
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
	
	// avslutar en kurs och ber användaren sätta ett betyg på den
	private void finishCourse(){
		if(activeCoursesList.getSelectedValue() != null){
			String selectedCourse = activeCoursesRef.get(activeCoursesList.getSelectedIndex());
			String fullName = activeCoursesList.getSelectedValue();
			String grade = (String) gradeComboBox.getSelectedItem();
			
			int yes = JOptionPane.showConfirmDialog(null, "Avsluta kurs \"" + fullName + "\" med betyg " + grade + "?");
			if (yes == JOptionPane.YES_OPTION){
				try {
					con.endCourse(currentStudent, selectedCourse, grade);
					existingData();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	
	}
	
	// avregistrerar från en kurs
	private void cancelCourse(){
		if (activeCoursesList.getSelectedValue() != null){
			String selectedCourse = activeCoursesRef.get(activeCoursesList.getSelectedIndex());
			String fullName = activeCoursesList.getSelectedValue();
			
			int yes = JOptionPane.showConfirmDialog(null, "Avregistrera från \"" + fullName + "\"?");
			
			if (yes == JOptionPane.YES_OPTION){
				try {
					con.cancelCourse(currentStudent, selectedCourse);
					existingData();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	// startar ny kurs för student
	private void startNewCourse(){
		if (coursesComboBox.getSelectedItem() != null){
			int success = 0;
			try {
				success = con.startCourse(currentStudent, startNewCourseRef.get(coursesComboBox.getSelectedIndex()));
				existingData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (success == 0){
				JOptionPane.showMessageDialog(null, "Så himla inte ok, hörru");
			}
		}
	
	}
}