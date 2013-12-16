package uppgift2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class MeinFrame extends JFrame {
	private Controller controller;
	
	private JPanel contentPane;
	
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
		
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listis.removeAll();
				try {
					getKeys();
				} catch (SQLException e) {
					System.out.println("FEEEEEEEEEEEL");
				}
			}
		});
		
		
		btnKey.setBounds(12, 36, 155, 25);
		btnIndex.setBounds(12, 90, 155, 25);
		btnConstraints.setBounds(12, 151, 155, 25);
		btnTables.setBounds(12, 206, 155, 25);
		btnMaxRows.setBounds(12, 331, 155, 25);
		btnColumns.setBounds(12, 269, 155, 25);
		listis.setBounds(206, 27, 753, 388);
		
		contentPane.add(btnKey);
		contentPane.add(btnIndex);
		contentPane.add(btnConstraints);
		contentPane.add(btnTables);
		contentPane.add(btnColumns);
		contentPane.add(btnMaxRows);
		contentPane.add(listis);
		
		System.out.println(controller.getMetaData().getDriverName());
		
	}
	
	private void getKeys() throws SQLException{
		ResultSet r = controller.getKeys();
		while(r.next()){
			listis.add(r.getString(1));

		}
	}
	
	
}
