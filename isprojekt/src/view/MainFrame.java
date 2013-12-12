package view;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTabbedPane;
import tengil.Controller;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private Controller con;
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel contentPane = new JPanel();
	private JPanel studentTabPanel = new JPanel();
	private JPanel courseTabPanel = new JPanel(); 
	private JPanel studentPanel;
	
	private DefaultListModel<String> studentListModel = new DefaultListModel<String>();
	private DefaultListModel<String> courseListModel = new DefaultListModel<String>();
	
	private JList<String> studentList = new JList<String>(studentListModel);
	private JList<String> courseList = new JList<String>(courseListModel);
	
	private JButton addStudentButton = new JButton("LEG TILL ELEF");
	private JButton addCourseButton = new JButton("LEG TILL KURZ");
	
	//konstruktor
	public MainFrame(Controller con) {
		this.con = con;
		initComponents();
		
		try{
			populateList();	
		}
		catch(SQLException e){
			
		}
	}
	
	// hämtar data och placerar den i listorna över studenter/kurser
	public void populateList() throws SQLException{
		studentListModel.clear();
		courseListModel.clear();
		
		ResultSet allStudents = con.getStudents();
		ResultSet allCourses = con.getCourses();
		
		while(allStudents.next()){
			studentListModel.addElement(allStudents.getString(1));
			
		}
		while(allCourses.next()){
			courseListModel.addElement(allCourses.getString(1));
		}
		
	}
	
	//skapar alla grafiska komponenter
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1241, 589);
		setContentPane(contentPane);
		
		studentList.setBounds(12, 13, 314, 419);
		courseList.setBounds(12, 13, 314, 421);
		addStudentButton.setBounds(87, 448, 152, 25);
		tabbedPane.setBounds(12, 13, 343, 516);
		addCourseButton.setBounds(98, 448, 124, 25);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(tabbedPane);

		tabbedPane.addTab("Studenter", null, studentTabPanel, null);
		tabbedPane.addTab("Kurser", null, courseTabPanel, null);
		
		studentTabPanel.setLayout(null);
		studentTabPanel.add(studentList);
		studentTabPanel.add(addStudentButton); 
		
		courseTabPanel.setLayout(null);
		courseTabPanel.add(courseList);
		courseTabPanel.add(addCourseButton);
		
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addStudent();
			}
		});
		
		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCourse();
			}
		});
		
		studentList.addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent e) {
	                studentList_valueChanged(e);
	                    }
	                });
		
		courseList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				courseList_valueChanged(e);
			}
		});
		
	}
	
	//listener för studentlistan
	private void studentList_valueChanged(ListSelectionEvent e){
		 	try{
		 		openStudent(studentList.getSelectedValue().toString());
		 	}
		 	catch(NullPointerException f){
		 	}
	    }
	
	//listener för kurslistan
	private void courseList_valueChanged(ListSelectionEvent e){
		try{
			openCourse(courseList.getSelectedValue().toString());
		}
		catch(NullPointerException f){
		}
	 }
	 
	
	//delegerare för att skapa nya studenter/kurser
	private void addStudent(){
		 openStudent(null);
	 }
	private void addCourse(){
		 openCourse(null);
	 }

	 //öppna en existerande student eller skapa en ny
	 private void openStudent(String civic){
		 try{
				contentPane.remove(studentPanel);
			}
			catch(NullPointerException e){
				
			}
		studentPanel = new NewStudentPanel(con,civic, this);
		repaint();
		contentPane.add(studentPanel);
	} 
	 
	 //öppna eller skapa en kurs
	 private void openCourse(String id){
		 try{
				contentPane.remove(studentPanel);
			}
			catch(NullPointerException e){
				
			}
		 studentPanel = new NewCoursePanel(con, id, this);
		repaint();
		contentPane.add(studentPanel);
	}
	 
	 //töm kurs-/studentpanelen, används innan en ny genereras
	 public void clearPanel(){
		 remove(studentPanel);
		 repaint();
	 }
}
