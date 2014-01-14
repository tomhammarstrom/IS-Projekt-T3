package uppgift2;

import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {
	private Controller con;

	private JPanel contentPane;

	private JComboBox<String> comboBox = new JComboBox<String>();
	private JComboBox<String> comboBox_1 = new JComboBox<String>();

	private JLabel lblUppgift = new JLabel("Uppgift 1?");
	private JLabel lblUppgift_1 = new JLabel("Uppgift2");

	private DefaultListModel<String> model = new DefaultListModel<String>();
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
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBox.setBounds(160, 31, 163, 27);

		contentPane.add(comboBox);
		comboBox_1.setBounds(622, 31, 200, 27);

		contentPane.add(comboBox_1);
		lblUppgift.setBounds(23, 36, 93, 16);

		contentPane.add(lblUppgift);
		lblUppgift_1.setBounds(504, 36, 86, 16);

		contentPane.add(lblUppgift_1);
		


		scrollPane.setBounds(23, 103, 950, 303);

		contentPane.add(scrollPane);

		JTable table = new JTable(data(), columnNames());
		scrollPane.setViewportView(table);
		
		getEmployeeData();

	}
	
	private Vector<Vector<Object>> data() throws SQLException{
		ResultSet r = con.getEmployeeData();
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
	
	private Vector<String> columnNames() throws SQLException{
		ResultSet r = con.getEmployeeData();

		ResultSetMetaData metaData = r.getMetaData();

		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		return columnNames;
	}

	private void getEmployeeData() throws SQLException {
		
	
	}
}
