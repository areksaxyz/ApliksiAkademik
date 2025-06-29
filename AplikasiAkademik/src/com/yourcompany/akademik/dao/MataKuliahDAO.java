package com.yourcompany.akademik.dao;

import com.yourcompany.akademik.model.MataKuliah;
import com.yourcompany.akademik.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MataKuliahDAO {

    public List<MataKuliah> getAllMataKuliah() {
        List<MataKuliah> mataKuliahList = new ArrayList<>();
        // Order by jadwal for better presentation (e.g., if jadwal contains "Senin", "Selasa", etc.)
        String query = "SELECT kode_mk, nama_mk, sks, dosen_pengampu, jadwal, ruangan FROM matakuliah ORDER BY jadwal, nama_mk";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String kodeMk = rs.getString("kode_mk");
                String namaMk = rs.getString("nama_mk");
                int sks = rs.getInt("sks");
                String dosenPengampu = rs.getString("dosen_pengampu");
                String jadwal = rs.getString("jadwal");
                String ruangan = rs.getString("ruangan");
                mataKuliahList.add(new MataKuliah(kodeMk, namaMk, sks, dosenPengampu, jadwal, ruangan));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all mata kuliah: " + e.getMessage());
            e.printStackTrace();
        }
        return mataKuliahList;
    }
}