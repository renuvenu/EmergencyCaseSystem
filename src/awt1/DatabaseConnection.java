package awt1;

import java.sql.*;
public class DatabaseConnection {
    
    public static Connection con;

    public DatabaseConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "test", "sql");       
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }
        
        
}