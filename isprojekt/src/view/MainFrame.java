package view;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JButton;

import controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//wazzap
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
	
	private ArrayList<String> studentRef = new ArrayList<String>();
	private ArrayList<String> courseRef = new ArrayList<String>();
	
	JScrollPane studentScroll = new JScrollPane();
	JScrollPane courseScroll = new JScrollPane();
	
	private JButton addStudentButton = new JButton("LEG TILL ELEF");
	private JButton addCourseButton = new JButton("LEG TILL KURZ");
	private JButton highestFlowButton = new JButton("Kurs med st�rst genomstr�mning");
	
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
	
	// h�mtar data och placerar den i listorna �ver studenter/kurser
	public void populateList() throws SQLException{
		studentListModel.clear();
		courseListModel.clear();
		
		studentRef.clear();
		courseRef.clear();
		
		ResultSet allStudents = con.getStudents();
		ResultSet allCourses = con.getCourses();
		
		while(allStudents.next()){
			String civ = allStudents.getString(1).trim();
			String name = allStudents.getString(2).trim();
			
			studentRef.add(civ);
			studentListModel.addElement(civ + ": " + name);
			
		}
		while(allCourses.next()){
			String id = allCourses.getString(1).trim();
			String name = allCourses.getString(2).trim();
			courseRef.add(id);
			courseListModel.addElement(id + ": " + name);
		}
		
	}
	
	//skapar alla grafiska komponenter
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1241, 589);
		setContentPane(contentPane);
		
		studentList.setBounds(12, 13, 314, 419);
		courseList.setBounds(12, 13, 314, 387);
		addStudentButton.setBounds(87, 448, 152, 25);
		tabbedPane.setBounds(12, 13, 343, 516);
		addCourseButton.setBounds(186, 431, 124, 41);
		highestFlowButton.setBounds(12, 424, 124, 48);
		
		studentScroll.setViewportView(studentList);
		courseScroll.setViewportView(courseList);
		
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
		courseTabPanel.add(highestFlowButton);
		highestFlowButton.setText("<html><center>"+"Kurs med st�rst"+"<br>"+"genomstr�mning"+"</center></html>");
		
		highestFlowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showHighestFlow();
			}
		});
		
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
	
	//listener f�r studentlistan
	private void studentList_valueChanged(ListSelectionEvent e){
		if(studentList.getSelectedValue() != null){
			try{
		 		openStudent(studentRef.get(studentList.getSelectedIndex()));
		 	}
		 	catch(NullPointerException f){
		 	}
		}
		 	
	    }
	
	//listener f�r kurslistan
	private void courseList_valueChanged(ListSelectionEvent e){
		if(courseList.getSelectedValue() != null){
			try{
				openCourse(courseRef.get(courseList.getSelectedIndex()));
			}
			catch(NullPointerException f){
			}
		}
		
	 }
	 
	
	//delegerare f�r att skapa nya studenter/kurser
	private void addStudent(){
		 openStudent(null);
	 }
	private void addCourse(){
		 openCourse(null);
	 }

	 //�ppna en existerande student eller skapa en ny
	 public void openStudent(String civic){
		 try{
				contentPane.remove(studentPanel);
			}
			catch(NullPointerException e){
				
			}
		studentPanel = new StudentPanel(con,civic, this);
		repaint();
		contentPane.add(studentPanel);
	} 
	 
	 //�ppna eller skapa en kurs
	 public void openCourse(String id){
		 try{
				contentPane.remove(studentPanel);
			}
			catch(NullPointerException e){
				
			}
		 studentPanel = new CoursePanel(con, id, this);
		repaint();
		contentPane.add(studentPanel);
	}
	 
	 //t�m kurs-/studentpanelen, anv�nds innan en ny genereras
	 public void clearPanel(){
		 remove(studentPanel);
		 repaint();
	 }
	 
	 private void showHighestFlow(){
		try {
			String best = con.highestFlow();
			openCourse(best);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
}
