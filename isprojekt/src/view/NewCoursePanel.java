package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import tengil.Controller;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NewCoursePanel extends JPanel{
	public NewCoursePanel() {
	}
	private Controller con;
	private String currentCourse;
	
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField pointsField = new JTextField();
	private JLabel idLbl = new JLabel("Id");
	private JLabel nameLbl = new JLabel("Namn");
	private JLabel pointsLbl = new JLabel("Poäng");
	private final JButton saveBtn = new JButton("Spara");
	private MainFrame mainFrame;
	
	
	public NewCoursePanel(Controller con, String id, MainFrame mainFrame) {
		this.con = con;
		currentCourse = id;
		this.mainFrame = mainFrame;
		
		initComponents();
		setVisible(true);
		
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
		}
		
	}
	

	
	
	private void initComponents(){
		pointsField.setBounds(176, 160, 201, 22);
		pointsField.setColumns(10);
		nameField.setBounds(176, 93, 201, 22);
		nameField.setColumns(10);
		idField.setBounds(176, 33, 201, 22);
		idField.setColumns(10);
		idLbl.setBounds(12, 36, 152, 16);
		nameLbl.setBounds(12, 96, 152, 16);
		pointsLbl.setBounds(12, 163, 152, 16);
		
		setBounds(397, 13, 389, 516);
		setLayout(null);
		
		add(idField);
		add(nameField);
		add(pointsField);
		add(idLbl);
		add(nameLbl);
		add(pointsLbl);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//addCourse();
			}
		});
		saveBtn.setBounds(124, 430, 97, 25);
		
		add(saveBtn);
	}
	private void addCourse(){
		/*try {
			//con.addCourse(idField.getText(), nameField.getText(), contents pointsField.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		mainFrame.populateList();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	}
}