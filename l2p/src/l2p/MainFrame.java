package l2p;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	/**
	 * Deklaration av 1 panel, 1 knapp och 1 lista
	 */
	private JPanel anslagstavlan;
	private JButton knappiz = new JButton("Knappiz");
	private List listan = new List();

	/**
	 * Main-metod, finns för att köra programmet
	 */
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
	

	/**
	 * Konstruktor, körs när framen instansieras (från main-metoden)
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
		
		// listener för knappiz
		knappiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testMetod();
				// testMetod, körs när man trycker på knappen, se nedan
			}
		});
	}
	
	private void testMetod(){
		
	}
}
