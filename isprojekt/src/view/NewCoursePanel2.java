package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import tengil.Controller;

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
	private JLabel pointsLbl = new JLabel("Poäng");
	private JLabel descrLbl = new JLabel("Beskrivning");
	private JButton saveBtn = new JButton("Spara");

	/**
	 * Create the panel.
	 */
	public NewCoursePanel2(Controller con, String id, MainFrame mainFrame) {
		this.con = con;
		currentCourse = id;
		this.mainFrame = mainFrame;
		
		initComponents();
		if(currentCourse != null){
			try {
				existingData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void existingData() throws SQLException{
		
		ResultSet r = con.getCourse(currentCourse);
		
		while(r.next()){
			idField.setText(r.getString("id"));
			nameField.setText(r.getString("name"));
			pointsField.setText(r.getString("point"));
			descrField.setText(r.getString("descr"));
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
		saveBtn.setBounds(49, 361, 89, 23);		
		add(saveBtn);
	}
}
