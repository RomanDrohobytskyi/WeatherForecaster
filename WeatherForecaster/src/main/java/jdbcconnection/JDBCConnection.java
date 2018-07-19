package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private String user = "root";
    private String pass = "root";
    private String url = "jdbc:mysql://localhost:3306/weather_db";
    private Connection connection;

    /*
    Method with Connection type, to make a JDBC and return connection.
     */
    public Connection connectionJDBC(){
        try{
            System.out.println("Connecting to jdbc...");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected!");
        }catch (SQLException ex){
            System.out.println("SQL Exception, exception : " + ex);
            ex.printStackTrace();
        }
        return this.connection;
    }

}
