package jd_prac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JD_PAK {
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME ="postgres";
    private static final String PASSWORD = "post";
     public  static Connection connection(){
         Connection connection =null;
         try {
             connection= DriverManager.getConnection(URL,USER_NAME,PASSWORD);
             System.out.println("Student connection");
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return connection;
     }

}
