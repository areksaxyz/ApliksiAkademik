package com.yourcompany.akademik.dao;

import com.yourcompany.akademik.model.Mahasiswa;
import com.yourcompany.akademik.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String query = "SELECT nim, nama, kelas, jurusan, semester FROM mahasiswa ORDER BY nim"; // Urutkan berdasarkan NIM
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String kelas = rs.getString("kelas");
                String jurusan = rs.getString("jurusan");
                int semester = rs.getInt("semester");
                mahasiswaList.add(new Mahasiswa(nim, nama, kelas, jurusan, semester));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all mahasiswa: " + e.getMessage());
            e.printStackTrace();
        }
        return mahasiswaList;
    }
    
    // Metode untuk menambah mahasiswa
    public boolean addMahasiswa(Mahasiswa mhs) {
        String query = "INSERT INTO mahasiswa (nim, nama, kelas, jurusan, semester) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mhs.getNim());
            stmt.setString(2, mhs.getNama());
            stmt.setString(3, mhs.getKelas());
            stmt.setString(4, mhs.getJurusan());
            stmt.setInt(5, mhs.getSemester());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding mahasiswa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Metode untuk update mahasiswa
    public boolean updateMahasiswa(Mahasiswa mhs) {
        String query = "UPDATE mahasiswa SET nama = ?, kelas = ?, jurusan = ?, semester = ? WHERE nim = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mhs.getNama());
            stmt.setString(2, mhs.getKelas());
            stmt.setString(3, mhs.getJurusan());
            stmt.setInt(4, mhs.getSemester());
            stmt.setString(5, mhs.getNim());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating mahasiswa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Metode untuk delete mahasiswa
    public boolean deleteMahasiswa(String nim) {
        // PERHATIAN: Jika ada FOREIGN KEY constraint dari tabel 'nilai' ke 'mahasiswa',
        // maka Anda perlu menghapus data di tabel 'nilai' terlebih dahulu
        // atau mengkonfigurasi FOREIGN KEY ON DELETE CASCADE di database.
        String query = "DELETE FROM mahasiswa WHERE nim = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nim);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting mahasiswa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}