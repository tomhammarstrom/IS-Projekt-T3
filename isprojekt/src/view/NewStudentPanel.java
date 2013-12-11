package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class NewStudentPanel extends JPanel{
	private JTextField textField = new JTextField();
	private JTextField textField_1 = new JTextField();
	private JTextField textField_2 = new JTextField();
	private JLabel lblPersonnummer = new JLabel("Personnummer");
	private JLabel lblNamn = new JLabel("Namn");
	private JLabel lblAdress = new JLabel("Adress");
	
	
	public NewStudentPanel(){
		initComponents();
		setVisible(true);
	}
	
	
	
	private void initComponents(){
		textField_2.setBounds(176, 160, 201, 22);
		textField_2.setColumns(10);
		textField_1.setBounds(176, 93, 201, 22);
		textField_1.setColumns(10);
		textField.setBounds(176, 33, 201, 22);
		textField.setColumns(10);
		lblPersonnummer.setBounds(12, 36, 152, 16);
		lblNamn.setBounds(12, 96, 152, 16);
		lblAdress.setBounds(12, 163, 152, 16);
		
		setBounds(397, 13, 389, 516);
		setLayout(null);
		
		add(textField);
		add(textField_1);
		add(textField_2);
		add(lblPersonnummer);
		add(lblNamn);
		add(lblAdress);
	}
}