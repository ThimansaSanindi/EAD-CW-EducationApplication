/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Database connection handler class
 * 
 */
public class dbConnection {

    private final String url = "jdbc:mysql://localhost:3306/registration_db"; // MySQL port 3306 and DB name
    private final String user = "root";
    private final String pass = "";

    // Open Connection With Database
    public Connection OpenConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("❌ Database Connection Problem: " + e.getMessage());
            return null;
        }
    }

    // Insert Data Into Database
    public void InsertData(String query) {
        try (Connection dbCon = OpenConnection(); Statement stmt = dbCon.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("✅ Data inserted successfully.");
        } catch (Exception e) {
            System.out.println("❌ Data Insertion Problem: " + e.getMessage());
        }
    }

    // Select Data from Database
    public ResultSet SelectData(String query) {
        try {
            Connection dbCon = OpenConnection();
            Statement stmt = dbCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("❌ Data Selecting Error: " + e.getMessage());
            return null;
        }
    }

    // Close Connection
    public void CloseConnection(Connection dbCon) {
        try {
            if (dbCon != null && !dbCon.isClosed()) {
                dbCon.close();
                System.out.println("🔒 Connection closed.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error while closing DB: " + e.getMessage());
        }
    }
}
