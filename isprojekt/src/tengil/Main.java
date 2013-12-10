package isprojekt.src.tengil;

import classes.isprojekt.src.view.MainFrame;

import isprojekt.src.view.StudentPanel;

import java.sql.SQLException;

import javax.swing.JPanel;


public class Main {

    public static void main (String []args) throws SQLException {
        new MainFrame(new Controller()).setVisible(true);
    }
    
}
