package view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NewStudentPanel extends JFrame {

	private JPanel fetchPanel;

	private JButton saveItemBtn = new JButton("Spara");
	private JButton removeItemBtn = new JButton("Ta bort");
	private JLabel nameError = new JLabel("");
	private JLabel priceError = new JLabel("");
	private JLabel detailsError = new JLabel("");
	

	public NewStudentPanel(JPanel fetchPanel) {

		//this.fetchPanel = fetchPanel;


		//fetchPanel.removeAll();
		jbInit();
	}

	
	private void jbInit() {
		setBounds(10, 35, 328, 415);
		
		 /**
		  * 
		  */
		 fetchPanel = new JPanel(); 
		 setContentPane(fetchPanel); 
		 /**
		  * 
		  */
		

		fetchPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		fetchPanel.setLayout(null);
		saveItemBtn.setBounds(10, 381, 89, 23);
		removeItemBtn.setBounds(226, 381, 89, 23);
		nameError.setBounds(132, 45, 183, 14);
		priceError.setBounds(132, 84, 183, 14);
		detailsError.setBounds(132, 351, 170, 14);

		
		fetchPanel.add(saveItemBtn);
		fetchPanel.add(removeItemBtn);
		fetchPanel.add(nameError);
		fetchPanel.add(priceError);
		fetchPanel.add(detailsError);

	}

	


	

}