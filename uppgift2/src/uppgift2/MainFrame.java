package uppgift2;

import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private Controller con;

	private JPanel contentPane;

	private JComboBox<String> x1ComboBox = new JComboBox<String>();

	private DefaultTableModel tableModel;
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private final JLabel lblNewLabel = new JLabel("Metadata = kolumn-namnen");

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
	
		x1ComboBox.setBounds(66, 30, 256, 27);


		contentPane.add(x1ComboBox);

		
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//scrollPane.setViewportView(table);
		scrollPane.setBounds(23, 72, 950, 334);
		contentPane.add(scrollPane);
		
		x1ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					x1Event();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		comboBoxes();
		lblNewLabel.setBounds(466, 35, 308, 16);
		
		
		contentPane.add(lblNewLabel);
	}
	
	private void comboBoxes() throws SQLException{
		x1ComboBox.addItem("Employee");
		x1ComboBox.addItem("Employee Absence");
		x1ComboBox.addItem("Employee Qualification");
		x1ComboBox.addItem("Employee Relative");
		x1ComboBox.addItem("Nycklar");
		x1ComboBox.addItem("Indexes");
		x1ComboBox.addItem("Table_Constraints");
		x1ComboBox.addItem("Tabeller");
		x1ComboBox.addItem("Employee-kolumner");
		x1ComboBox.addItem("Flest rader");
	}
	
	private void x1Event() throws SQLException{
		int selectedIndex = x1ComboBox.getSelectedIndex();
		switch (selectedIndex){
		case 0: showEmployeeData();
				break;
		case 1: showEmployeeAbsence();
				break;
		case 2: showEmployeeQualifications();
				break;
		case 3: showEmployeeRelative();
				break;
		case 4: showKeys();
				break;
		case 5: showIndexes();
				break;
		case 6: showConstraints();
				break;
		case 7: showTables();
				break;
		case 8: showEmployee();
				break;
		case 9: showMostRows();
				break;
		}
	}
	
	private void showEmployeeData() throws SQLException{
		ResultSet r = con.getEmployeeData();
		buildModel(r);
	}
	
	private void showEmployeeAbsence() throws SQLException{
		ResultSet r = con.getEmployeeAbsenceData();
		buildModel(r);
	}
	
	private void showEmployeeQualifications() throws SQLException{
		ResultSet r = con.getEmployeeQualificationData();
		buildModel(r);
	}
	
	private void showEmployeeRelative() throws SQLException{
		ResultSet r = con.getEmployeeRelativeData();
		buildModel(r);
	}
	
	private void showKeys() throws SQLException{
		ResultSet r = con.getKeys();
		buildModel(r);
	}
	
	private void showIndexes() throws SQLException{
		ResultSet r = con.getIndex();
		buildModel(r);
	}
	
	private void showConstraints() throws SQLException{
		ResultSet r = con.getConstraints();
		buildModel(r);
	}
	
	private void showTables() throws SQLException{
		ResultSet r = con.getTables();
		buildModel(r);
	}
	
	
	private void showEmployee() throws SQLException{
		ResultSet r = con.getColumns();
		buildModel(r);
	}
	

	
	private void showMostRows() throws SQLException{
		ResultSet r = con.getMaxRow();
		buildModel(r);
	}


	
	private void buildModel(ResultSet r) throws SQLException{
		tableModel = new DefaultTableModel(data(r), columnNames(r));
		table.removeAll();
		table.setModel(tableModel);
	}
	
	private Vector<Vector<String>> data(ResultSet r) throws SQLException{
		ResultSetMetaData metaData = r.getMetaData();
		int columnCount = metaData.getColumnCount();
		
	    Vector<Vector<String>> data = new Vector<Vector<String>>();
	    while (r.next()) {
	        Vector<String> vector = new Vector<String>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(r.getString(columnIndex));
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
