package com.yourcompany.akademik.util;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_elearning";
    private static final String DB_USER = "root"; // <-- GANTI dengan username MySQL Anda
    private static final String DB_PASSWORD = ""; // <-- GANTI dengan password MySQL Anda

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Memuat JDBC Driver (untuk MySQL 8+ tidak lagi wajib di Java 6+, tapi baik untuk eksplisit)
            // Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // System.out.println("Koneksi database berhasil!"); // Untuk debugging
        } catch (SQLException e) {
            System.err.println("Gagal terhubung ke database!");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database!\nPastikan MySQL berjalan dan konfigurasi DB_URL, DB_USER, DB_PASSWORD benar.\nError: " + e.getMessage(), "Error Koneksi Database", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database.");
                e.printStackTrace();
            }
        }
    }
}