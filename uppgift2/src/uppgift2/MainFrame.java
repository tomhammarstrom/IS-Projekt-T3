package uppgift2;

import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private Controller con;

	private JPanel contentPane;

	private JComboBox<String> x1ComboBox = new JComboBox<String>();
	private JComboBox<String> x2ComboBox = new JComboBox<String>();

	private JLabel x1lbl = new JLabel("Uppgift 1?");
	private JLabel x2lbl = new JLabel("Uppgift2!");

	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane();

	public MainFrame(Controller con) throws SQLException {
		this.con = con;
		initComponents();
	}

	private void initComponents() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 480);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	
		x1ComboBox.setBounds(160, 31, 163, 27);
		x2ComboBox.setBounds(622, 31, 200, 27);
		x2lbl.setBounds(504, 36, 86, 16);
		x1lbl.setBounds(23, 36, 93, 16);
		scrollPane.setBounds(23, 103, 950, 303);

		contentPane.add(x1ComboBox);
		contentPane.add(x2ComboBox);
		contentPane.add(x1lbl);
		contentPane.add(x2lbl);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		x1ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					x1Event();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		comboBoxes();
	}
	
	private void comboBoxes(){
		x1ComboBox.addItem("");
		x1ComboBox.addItem("Employee");
		x1ComboBox.addItem("Employee Absence");
		x1ComboBox.addItem("Employee Qualification");
		x1ComboBox.addItem("Employee Relative");
		
		x2ComboBox.addItem("");
		x2ComboBox.addItem("Nycklar");
		x2ComboBox.addItem("Indexes");
		x2ComboBox.addItem("Table Constraints");
		x2ComboBox.addItem("Tabeller 1");
		x2ComboBox.addItem("Tabeller 2");
		x2ComboBox.addItem("Kolumner i employee 1");
		x2ComboBox.addItem("Kolumner i employee 1");
		x2ComboBox.addItem("Tabell med flest rader");
		
	}
	
	private void x1Event() throws SQLException{
		ResultSet r = null;
		
	        switch (x1ComboBox.getSelectedIndex()) {
	            case 1:  r = con.getEmployeeData();
	                     break;
	            case 2:  r = con.getEmployeeAbsenceData();
	                     break;
	            case 3:  r = con.getEmployeeQualificationData();
	            		 break;
	            case 4:  r = con.getEmployeeRelativeData();
                		 break;
                default: 
                		 break;
	        }
	        
	}
	
	
	
	
	
	private Vector<Vector<Object>> data(ResultSet r) throws SQLException{
		ResultSetMetaData metaData = r.getMetaData();
		int columnCount = metaData.getColumnCount();
		
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (r.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(r.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    return data;
	}
	
	private Vector<String> columnNames(ResultSet r) throws SQLException{
		ResultSetMetaData metaData = r.getMetaData();

		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		return columnNames;
	}

	
}
