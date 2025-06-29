package com.yourcompany.akademik.dao;

import com.yourcompany.akademik.model.Nilai;
import com.yourcompany.akademik.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NilaiDAO {

    public List<Nilai> getNilaiByNim(String nimMahasiswa) {
        List<Nilai> nilaiList = new ArrayList<>();
        // Mengambil semua kolom yang diperlukan
        String query = "SELECT id, nim, kode_mk, absensi, quiz, uts, uas, grade FROM nilai WHERE nim = ? ORDER BY kode_mk";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nimMahasiswa);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nim = rs.getString("nim");
                    String kodeMk = rs.getString("kode_mk");
                    String absensi = rs.getString("absensi");
                    double quiz = rs.getDouble("quiz");
                    double uts = rs.getDouble("uts");
                    double uas = rs.getDouble("uas");
                    String grade = rs.getString("grade");
                    nilaiList.add(new Nilai(id, nim, kodeMk, absensi, quiz, uts, uas, grade));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching nilai by NIM: " + e.getMessage());
            e.printStackTrace();
        }
        return nilaiList;
    }
}