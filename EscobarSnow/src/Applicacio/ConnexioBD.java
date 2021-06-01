package Applicacio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexioBD {
    public static void connbd() throws SQLException{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escobarsnow2", "root", "");
        
    }

}
