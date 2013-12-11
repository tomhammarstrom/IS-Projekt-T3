package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import tengil.Controller;

public class NewStudentPanel extends JPanel{
	private Controller con;
	private String currentStudent;
	
	private JTextField civicField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JLabel lblPersonnummer = new JLabel("Personnummer");
	private JLabel lblNamn = new JLabel("Namn");
	private JLabel lblAdress = new JLabel("Adress");
	
	
	public NewStudentPanel(Controller con, String civic) {
		this.con = con;
		currentStudent = civic;
		
		initComponents();
		setVisible(true);
		
		if(currentStudent != null){
			try {
				existingData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void existingData() throws SQLException{
		
		ResultSet r = con.getStudent(currentStudent);
		
		while(r.next()){
			civicField.setText(r.getString("pnr"));
			nameField.setText(r.getString("name"));
			addressField.setText(r.getString("adr"));	
		}
		
	}
	

	
	
	private void initComponents(){
		addressField.setBounds(176, 160, 201, 22);
		addressField.setColumns(10);
		nameField.setBounds(176, 93, 201, 22);
		nameField.setColumns(10);
		civicField.setBounds(176, 33, 201, 22);
		civicField.setColumns(10);
		lblPersonnummer.setBounds(12, 36, 152, 16);
		lblNamn.setBounds(12, 96, 152, 16);
		lblAdress.setBounds(12, 163, 152, 16);
		
		setBounds(397, 13, 389, 516);
		setLayout(null);
		
		add(civicField);
		add(nameField);
		add(addressField);
		add(lblPersonnummer);
		add(lblNamn);
		add(lblAdress);
	}
}