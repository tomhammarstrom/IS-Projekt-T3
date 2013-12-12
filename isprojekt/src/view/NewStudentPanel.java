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
	private JLabel activeCoursesLabel = new JLabel("Alla kurser huru elefen studerar");
	private JLabel inactiveCoursesLabel = new JLabel("Afslutat kurz");
	
	private JButton saveBtn = new JButton("Spara");
	private JButton deleteBtn = new JButton("Ta bort");
	private JButton finishCourseButton = new JButton("hafa betyg tak");
	
	private DefaultListModel<String> activeCoursesModel = new DefaultListModel<String>();
	private DefaultListModel<String> inactiveCoursesModel = new DefaultListModel<String>();
	
	private JList<String> activeCoursesList = new JList<String>(activeCoursesModel);
	private JList<String> inactiveCoursesList = new JList<String>(inactiveCoursesModel);
	
	
	// Konstruktur
	public NewStudentPanel(Controller con, String civic, MainFrame mainFrame) {
		this.con = con;
		currentStudent = civic;
		this.mainFrame = mainFrame;
		
		initComponents();
		
		try {
			existingData();
		} catch (SQLException e) {
			System.out.println("problem med att ladda existerande data f�r student");
			e.printStackTrace();
		}
	}
		

	// laddar information om student om den �ppnas fr�n listan
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
			
			r = con.getCoursesForStudent(currentStudent);
			activeCoursesModel.clear();
			
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
	
	// visar extraknappar f�r nyskapade eller existerande studenter
	private void showHiddenComponents(){
		add(deleteBtn);
		add(inactiveCoursesList);
		add(finishCourseButton);
		add(inactiveCoursesLabel);
		add(activeCoursesList);
		add(activeCoursesLabel);
		repaint();
	}

	

	// skapar alla grafiska komponenter, k�rs av konstruktorn
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
		activeCoursesLabel.setBounds(492, 36, 269, 16);
		activeCoursesList.setBounds(449, 65, 303, 152);		
		inactiveCoursesLabel.setBounds(541, 282, 164, 16);
		inactiveCoursesList.setBounds(461, 317, 300, 152);
		finishCourseButton.setBounds(541, 222, 97, 25);
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
		
		finishCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishCourse();
			}
		});
		
		
	}
	
	// k�rs av addStudent() n�r man f�rs�ker spara f�r att se till s� att det finns ett personnummer
	private boolean validateInput(){
		if(con.validateNotNull(civicField.getText())){
			return true;
		}
		else{
			return false;
		}
	}
	
	//l�gger till eller uppdaterar en student
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
	
	// avslutar en kurs och ber anv�ndaren s�tta ett betyg p� den
	private void finishCourse(){
		// skit
	}
	

}