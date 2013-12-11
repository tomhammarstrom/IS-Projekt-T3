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

import javax.swing.JLabel;

import java.awt.CardLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private Controller con;
	

	private JPanel contentPane = new JPanel();
	private JPanel studentTabPanel = new JPanel();
	private JPanel courseTabPanel = new JPanel(); 
	
	private DefaultListModel studentListModel = new DefaultListModel();
	private JList studentList = new JList(studentListModel);
	
	private DefaultListModel courseListModel = new DefaultListModel();
	private JList courseList = new JList(courseListModel);
	
	
	
	
	
	private JButton btnLggTillStudent = new JButton("LEG TILL ELEF");
	private JButton btnLggTillKurs = new JButton("LEG TILL KURZ");
	private JPanel panel = new JPanel();
	

	public MainFrame(Controller con) {
		this.con = con;
		initComponents();
		
		try{
			populateList();	
		}
		catch(SQLException e){
			
		}
	}
	
	
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
		
		studentList.setBounds(12, 13, 314, 419);
		courseList.setBounds(12, 13, 314, 421);
		
		studentTabPanel.add(studentList);
		btnLggTillStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addStudent();
			}
		});
		btnLggTillStudent.setBounds(87, 448, 152, 25);
		
		studentTabPanel.add(btnLggTillStudent); 
		courseTabPanel.add(courseList);
		btnLggTillKurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCourse();
			}
		});
		btnLggTillKurs.setBounds(98, 448, 124, 25);
		
		courseTabPanel.add(btnLggTillKurs);
		panel.setBounds(409, 33, 352, 483);
		contentPane.add(panel);
		
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
	
	private void studentList_valueChanged(ListSelectionEvent e){
		 	try{
		 		openStudent(studentList.getSelectedValue().toString());
		 	}
		 	catch(NullPointerException f){
		 	}
	    }
	private void courseList_valueChanged(ListSelectionEvent e){
		try{
			openCourse(courseList.getSelectedValue().toString());
		}
		catch(NullPointerException f){
		}
	}
	 
	 private void addStudent(){
		 openStudent(null);
	 }
	 private void addCourse(){
		 openCourse(null);
	 }

	 private JPanel studentPanel;
	 private void openStudent(String civic){
		 try{
				contentPane.remove(studentPanel);
			}
			catch(NullPointerException e){
				
			}
		studentPanel = new NewStudentPanel(con,civic, this);
		repaint();
		contentPane.remove(panel);
		contentPane.add(studentPanel);
	} 
	 private void openCourse(String id){
		 try{
				contentPane.remove(studentPanel);
			}
			catch(NullPointerException e){
				
			}
		 studentPanel = new NewCoursePanel(con, id, this);
		repaint();
		contentPane.remove(panel);
		contentPane.add(studentPanel);
	}
	 
	 public void clearPanel(){
		 remove(studentPanel);
		 repaint();
	 }
	 
	 
}
