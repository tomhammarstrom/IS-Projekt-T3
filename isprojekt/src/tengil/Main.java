package isprojekt.src.tengil;


import classes.isprojekt.src.view.MainFrame;

import java.sql.SQLException;


public class Main {

    public static void main (String []args) throws SQLException {

        new MainFrame(new Controller()).setVisible(true);
    }
    
}
