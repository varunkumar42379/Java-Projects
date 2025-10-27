package org.example;
import java.sql.*;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/Student_db"; // Database details
    private static String username = "root"; // MySQL credentials
    private static String password = "Vamshi@42379";
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
