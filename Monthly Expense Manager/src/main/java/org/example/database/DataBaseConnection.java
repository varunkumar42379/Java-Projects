package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class DataBaseConnection {

    public static String connection(){

        String url="jdbc:mysql://localhost:3306/crud_db";
        String username="root";
        String password="Vamshi@42379";

        try(Connection connection= DriverManager.getConnection(url,username,password)){
            return "Connection Successful....";
        }catch (SQLException e){
            e.printStackTrace();
        }

        return "DataBase Not Connected....";
    }
}
