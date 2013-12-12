package view;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	private JButton saveBtn = new JButton("Spara");
	private JButton deleteBtn = new JButton("Ta bort");
	private final JList list = new JList();
	private final JList list_1 = new JList();
	private final JLabel lblAktifaStudenter = new JLabel("Aktifa studenter");
	private final JLabel lblStudenterKlarr = new JLabel("Studenter klarr");
	private final JButton btnVisaStudent = new JButton("Visa student");
	private final JButton btnVisaStudent_1 = new JButton("Visa student");

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
				idField.setText(r.getString("id"));
				nameField.setText(r.getString("name"));
				pointsField.setText(r.getString("point"));
				descrField.setText(r.getString("descr"));
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
		list.setBounds(489, 33, 280, 188);
		list_1.setBounds(489, 280, 271, 188);
		lblAktifaStudenter.setBounds(525, 13, 140, 16);
		lblStudenterKlarr.setBounds(525, 259, 140, 16);
		btnVisaStudent.setBounds(564, 221, 128, 25);
		btnVisaStudent_1.setBounds(568, 479, 124, 25);
		
		add(pointsField);		
		add(idField);		
		add(nameField);		
		add(descrField);
		add(idLbl);	
		add(nameLbl);	
		add(pointsLbl);	
		add(descrLbl);
		add(saveBtn);
		add(btnVisaStudent);
		
		
		add(list);		
		add(list_1);
		add(lblAktifaStudenter);
		add(lblStudenterKlarr);
		add(btnVisaStudent_1);
		
		
		btnVisaStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	showStudent("top");
			}
		});
		
		btnVisaStudent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	showStudent("bottom");
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
}
