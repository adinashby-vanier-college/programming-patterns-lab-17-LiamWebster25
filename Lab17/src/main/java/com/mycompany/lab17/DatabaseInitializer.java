/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab17;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author webst
 */
public class DatabaseInitializer {
    

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement statement = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS books (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TITLE VARCHAR(45) NOT NULL," +
                    "AUTHOR VARCHAR(30) NOT NULL" +
                    ");";
            
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
