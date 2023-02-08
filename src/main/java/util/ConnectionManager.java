package util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@UtilityClass
public class ConnectionManager {
    private static final String PASSWORD = "40984098";
    private static final String USER = "postgres";
    private static final String URL = "jdbc:tc:postgresql:15:///localhost:5432/postgres?TC_INITSCRIPT=file:src/test/resources/INITIALSCRIPT.sql";

    static {
        loadDriver();
    }
    private static void loadDriver(){
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver found.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found.");
            throw new RuntimeException(e);
        }
    }

 /*
    public static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
 public static Connection open(){
     try{
         Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         System.out.println("Connected to database.");
         return connection;
     } catch (SQLException e) {
         System.out.println("Not connected to database.");
         throw new RuntimeException(e);
     }
 }
}
