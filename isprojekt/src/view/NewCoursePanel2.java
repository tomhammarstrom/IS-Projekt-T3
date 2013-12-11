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

public class NewCoursePanel2 extends JPanel {
	private Controller con;
	private String currentCourse; 
	private MainFrame mainFrame;
	private JTextField pointsField = new JTextField();
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField descrField = new JTextField();
	private JLabel idLbl = new JLabel("Kurs ID");
	private JLabel nameLbl = new JLabel("Namn");
	private JLabel pointsLbl = new JLabel("Po�ng");
	private JLabel descrLbl = new JLabel("Beskrivning");
	private JButton saveBtn = new JButton("Spara");
	private JButton deleteBtn = new JButton("Ta bort");

	/**
	 * Create the panel.
	 */
	public NewCoursePanel2(Controller con, String id, MainFrame mainFrame) {
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
	private void initComponents(){
		descrField.setBounds(179, 201, 140, 20);
		descrField.setColumns(10);
		nameField.setBounds(179, 82, 140, 20);
		nameField.setColumns(10);
		idField.setBounds(179, 28, 142, 20);
		idField.setColumns(10);
		pointsField.setBounds(179, 140, 140, 20);
		pointsField.setColumns(10);
		setBounds(397, 13, 389, 516);
		setLayout(null);
		
		add(pointsField);		
		add(idField);		
		add(nameField);		
		add(descrField);
		idLbl.setBounds(49, 31, 46, 14);		
		add(idLbl);
		nameLbl.setBounds(49, 85, 46, 14);		
		add(nameLbl);
		pointsLbl.setBounds(49, 143, 46, 14);		
		add(pointsLbl);
		descrLbl.setBounds(49, 204, 76, 14);		
		add(descrLbl);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCourse();
			}
		});
		saveBtn.setBounds(288, 480, 89, 23);		
		add(saveBtn);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCourse();
			}
		});
		deleteBtn.setBounds(12, 479, 97, 25);
		
		
	}
	private void addCourse(){
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
