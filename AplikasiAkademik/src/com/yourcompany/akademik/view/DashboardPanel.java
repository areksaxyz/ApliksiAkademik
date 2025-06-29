package com.yourcompany.akademik.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new GridBagLayout()); // Menggunakan GridBagLayout untuk tata letak yang fleksibel
        setBackground(new Color(248, 248, 255)); // Warna latar belakang terang

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Padding antar komponen
        gbc.fill = GridBagConstraints.BOTH; // Isi ruang horizontal dan vertikal
        gbc.weightx = 1.0; // Beri bobot agar mengisi ruang kosong
        gbc.weighty = 1.0;

        // Selamat Datang Label
        JLabel welcomeLabel = new JLabel("Selamat Datang di Aplikasi Admin Akademik");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(50, 50, 50)); // Warna teks gelap
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Menempati 3 kolom
        gbc.anchor = GridBagConstraints.WEST; // Rata kiri
        add(welcomeLabel, gbc);

        // Reset gridwidth dan anchor untuk kartu
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // Kartu-kartu ringkasan data
        // Anda akan mengganti angka statis ini dengan data dari database di kemudian hari
        add(createStatCard("Total Mahasiswa", "120", new Color(77, 182, 172)), gbc); // Biru kehijauan
        gbc.gridx = 1;
        add(createStatCard("Total Dosen", "15", new Color(102, 187, 106)), gbc); // Hijau
        gbc.gridx = 2;
        add(createStatCard("Jadwal Aktif", "28", new Color(239, 83, 80)), gbc); // Merah

        gbc.gridx = 0;
        gbc.gridy = 2; // Baris kedua untuk kartu
        add(createStatCard("Mata Kuliah", "35", new Color(255, 202, 40)), gbc); // Kuning
        gbc.gridx = 1;
        add(createStatCard("Ruang Kelas", "20", new Color(66, 66, 66)), gbc); // Abu-abu gelap
        gbc.gridx = 2;
        add(createStatCard("Nilai", "40", new Color(171, 71, 188)), gbc); // Ungu
    }

    // Metode helper untuk membuat kartu statistik
    private JPanel createStatCard(String title, String value, Color bgColor) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10)); // Spasi antar komponen dalam kartu
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1), // Border tipis
                new EmptyBorder(20, 20, 20, 20) // Padding internal
        ));
        
        // Tambahkan efek bayangan sederhana (opsional, bisa lebih kompleks dengan Custom Painting)
        card.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Untuk memberi ruang bayangan
        card.setOpaque(false); // Agar bayangan bisa terlihat


        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Tengah

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER); // Tengah

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        // Membuat inner panel untuk latar belakang berwarna
        JPanel innerPanel = new JPanel(new BorderLayout(10, 10));
        innerPanel.setBackground(bgColor);
        innerPanel.add(titleLabel, BorderLayout.NORTH);
        innerPanel.add(valueLabel, BorderLayout.CENTER);
        innerPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding internal

        card.add(innerPanel, BorderLayout.CENTER);

        return card;
    }
}