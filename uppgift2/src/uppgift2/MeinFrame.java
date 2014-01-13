package uppgift2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class MeinFrame extends JFrame {
	private Controller controller;
	
	private JPanel contentPane;
	
	private JButton btnCustomer = new JButton("Kund-tabellen");
	private JButton btnMeta = new JButton("Metadata");
	private JButton btnKey = new JButton("Alla nycklar");
	private JButton btnIndex = new JButton("Alla index");
	private JButton btnConstraints = new JButton("Constrains");
	private JButton btnTables = new JButton("Tabeller");
	private JButton btnColumns = new JButton("Kolumner");
	private JButton btnMaxRows = new JButton("Max rad");
	
	private List listis = new List();

	public MeinFrame() throws SQLException {
		controller = new Controller();
		initComponents();
		
	}
	
	
	private void initComponents() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		setBounds(500, 100, 1048, 511);
		setContentPane(contentPane);
		
		btnKey.setBounds(12, 224, 155, 25);
		btnIndex.setBounds(12, 262, 155, 25);
		btnConstraints.setBounds(12, 299, 155, 25);
		btnTables.setBounds(12, 339, 155, 25);
		btnMaxRows.setBounds(12, 426, 155, 25);
		btnColumns.setBounds(12, 380, 155, 25);
		listis.setBounds(206, 27, 753, 388);
		btnCustomer.setBounds(12, 41, 155, 25);
		btnMeta.setBounds(12, 98, 155, 25);
		
		contentPane.add(btnCustomer);
		contentPane.add(btnMeta);
		contentPane.add(btnKey);
		contentPane.add(btnIndex);
		contentPane.add(btnConstraints);
		contentPane.add(btnTables);
		contentPane.add(btnColumns);
		contentPane.add(btnMaxRows);
		contentPane.add(listis);
		
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listis.removeAll();
				getKeys();
			}
		});
		
		btnIndex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listis.removeAll();
				getIndex();
			}		
		});
		
		btnConstraints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listis.removeAll();
				getConstraints();
			}
		});
		
		btnTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listis.removeAll();
				getTables();
			}
		});
		
		btnMaxRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listis.removeAll();
				getMaxRow();
			}
		});
	
		btnColumns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listis.removeAll();
				getColumns();
			}
		});
		
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listis.removeAll();
				customerInfo();
				
			}
		});
		
		btnMeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listis.removeAll();
				getMetaData();
			}
		});
		
		
	}
	
	private void customerInfo(){
		
	}

	private void getMetaData(){
		try {
			DatabaseMetaData r = controller.getMetaData();
			listis.add(r.getDriverName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void getKeys() {
		try {
			ResultSet r = controller.getKeys();
			while(r.next()){
				listis.add(r.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void getIndex() {
		try {
			ResultSet r = controller.getIndex();
			while(r.next()){
				listis.add(r.getString(1) + " " + r.getString(2) + r.getString(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getConstraints(){
		try {
			ResultSet r = controller.getConstraints();
			while(r.next()){
				listis.add(r.getString(1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getTables(){
		try {
			ResultSet r = controller.getTables();
			while(r.next()){
				listis.add(r.getString(1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getColumns(){
		try {
			ResultSet r = controller.getColumns();
			while(r.next()){
				listis.add(r.getString(1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getMaxRow(){
		try {
			ResultSet r = controller.getMaxRow();
			while(r.next()){
				listis.add(r.getString(1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
