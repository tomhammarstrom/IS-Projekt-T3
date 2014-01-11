package l2p;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/**
	 * Deklaration av 1 panel, 1 knapp och 1 lista
	 */
	private JPanel anslagstavlan;
	private JButton knappiz = new JButton("Knappiz");
	private List listan = new List();

	/**
	 * Main-metod, finns f�r att k�ra programmet
	 */
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
	

	/**
	 * Konstruktor, k�rs n�r framen instansieras (fr�n main-metoden)
	 * 
	 */
	public MainFrame() {
		initComponents();
	}
	
	/**
	 * Metoden som "ritar ut" grafiska komponenter
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 427);
		
		anslagstavlan = new JPanel();
		anslagstavlan.setBorder(new EmptyBorder(5, 5, 5, 5));
		anslagstavlan.setLayout(null);
		setContentPane(anslagstavlan);
		
		knappiz.setBounds(82, 151, 97, 25);
		listan.setBounds(294, 58, 318, 245);
		
		anslagstavlan.add(knappiz);
		anslagstavlan.add(listan);
		
		// listener f�r knappiz
		knappiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					testMetod();
					// testMetod, k�rs n�r man trycker p� knappen, se nedan
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	/**
	 * Metod f�r att h�mta namnen p� alla studenter, k�rs n�r man trycker p� knappen
	 */
	@SuppressWarnings("restriction")
	private void testMetod() throws SQLException{
		// Att "prata" med SQL g�rs i 4 steg, steg 1 (vilket bara beh�ver g�ras 1 g�ng) �r att registrera en drivrutin
		DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
		
		// Steg 2 �r att skapa en anslutning, viktigt h�r �r den sista delen (isprojekt_cool) som ska vara namnet p� din databas
		// Innan den kan anv�ndas, m�ste den registreras i controlpanelen p� datorn (se powerpoint)
		Connection connection = DriverManager.getConnection("JDBC:ODBC:isprojekt_cool");
		
		// Steg 3 �r att skapa ett statement
		Statement statement = connection.createStatement();
		
		// Steg 4 �r att "skicka" en SQL-sats till databasen (se parantesen), denna sparas sen i ett s.k. "result set"
		// Detta ineh�ller samma tabel-info du ser i SQL-server management
		ResultSet r = statement.executeQuery("select * from student");
		
		//F�r att sedan "redovisa" vad vi h�mtade, v�ljer vi att l�gga alla namn i listan.
		
		while(r.next()){ //s� l�nge r har fler rader
			listan.add(r.getString(2)); // 2 - eftersom namnet har kolumnposition 2 i SQL
		}
		
		
		
	}
}
