package com.yourcompany.akademik.view;

// Import Model dan DAO yang baru
import com.yourcompany.akademik.model.Mahasiswa;
import com.yourcompany.akademik.dao.MahasiswaDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List; // Penting untuk List

public class MahasiswaPanel extends JPanel {

    // Global Color Palette (Consistent with other files)
    protected static final Color BG_PRIMARY = new Color(245, 247, 249);
    protected static final Color BG_CONTENT = Color.WHITE;
    protected static final Color ACCENT_COLOR = new Color(69, 170, 159);
    protected static final Color TEXT_DARK = new Color(36, 41, 46);
    protected static final Color TEXT_MEDIUM = new Color(108, 117, 125);
    protected static final Color BORDER_LIGHT = new Color(223, 227, 230);
    protected static final Color HOVER_LIGHT = new Color(230, 248, 246);
    protected static final Color ACTIVE_LIGHT = new Color(200, 240, 235);
    protected static final Color BUTTON_SUCCESS = new Color(40, 167, 69);
    protected static final Color BUTTON_DANGER = new Color(220, 53, 69);
    protected static final Color BUTTON_NEUTRAL = new Color(108, 117, 125);


    private DefaultTableModel tableModel;
    private JTable studentTable;
    private JTextField nimField, namaField, kelasField, jurusanField, semesterField;
    private JButton addButton, editButton, deleteButton, clearButton;

    private MahasiswaDAO mahasiswaDAO; // Instansiasi DAO

    public MahasiswaPanel() {
        mahasiswaDAO = new MahasiswaDAO(); // Inisialisasi DAO

        setLayout(new BorderLayout(25, 25));
        setBackground(BG_PRIMARY);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = new JLabel("Kelola Data Mahasiswa");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_DARK);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Input Form Area ---
        JPanel inputFormPanel = new JPanel(new GridBagLayout());
        inputFormPanel.setBackground(BG_CONTENT);
        inputFormPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_LIGHT, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] fieldLabels = {"NIM:", "Nama:", "Kelas:", "Jurusan:", "Semester:"};
        JTextField[] fields = {nimField = new JTextField(), namaField = new JTextField(),
                               kelasField = new JTextField(), jurusanField = new JTextField(),
                               semesterField = new JTextField()};

        for (int i = 0; i < fieldLabels.length; i++) {
            JLabel label = new JLabel(fieldLabels[i]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            label.setForeground(TEXT_DARK);
            gbc.gridx = 0; gbc.gridy = i; gbc.weightx = 0; inputFormPanel.add(label, gbc);

            fields[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
            fields[i].setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_LIGHT, 1),
                new EmptyBorder(5, 8, 5, 8)
            ));
            gbc.gridx = 1; gbc.gridy = i; gbc.weightx = 1.0; inputFormPanel.add(fields[i], gbc);
        }
        nimField = fields[0];
        namaField = fields[1];
        kelasField = fields[2];
        jurusanField = fields[3];
        semesterField = fields[4];


        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonPanel.setBackground(BG_CONTENT);

        addButton = new JButton("Tambah");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Hapus");
        clearButton = new JButton("Clear");

        styleButton(addButton, BUTTON_SUCCESS); // Green for Add
        styleButton(editButton, ACCENT_COLOR); // Primary Blue for Edit
        styleButton(deleteButton, BUTTON_DANGER); // Red for Delete
        styleButton(clearButton, BUTTON_NEUTRAL); // Gray for Clear

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0; gbc.gridy = fieldLabels.length; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.EAST; inputFormPanel.add(buttonPanel, gbc);

        add(inputFormPanel, BorderLayout.WEST);

        // --- Table Area ---
        String[] columnNames = {"NIM", "Nama", "Kelas", "Jurusan", "Semester"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        studentTable.setRowHeight(35);

        // Table Header Styling
        studentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        studentTable.getTableHeader().setBackground(ACCENT_COLOR);
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.getTableHeader().setResizingAllowed(true);

        // Table Body Styling
        studentTable.setSelectionBackground(HOVER_LIGHT);
        studentTable.setSelectionForeground(TEXT_DARK);
        studentTable.setGridColor(BORDER_LIGHT);
        studentTable.setShowVerticalLines(true);
        studentTable.setShowHorizontalLines(true);

        // Custom Cell Renderer for alternating row colors and padding
        studentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? BG_CONTENT : BG_PRIMARY);
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                }
                c.setForeground(TEXT_DARK);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                return c;
            }
        });


        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT, 1));
        add(scrollPane, BorderLayout.CENTER);

        // --- Event Listeners ---
        addButton.addActionListener(e -> addMahasiswa());
        editButton.addActionListener(e -> editMahasiswa());
        deleteButton.addActionListener(e -> deleteMahasiswa());
        clearButton.addActionListener(e -> clearFields());
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && studentTable.getSelectedRow() != -1) {
                displaySelectedMahasiswa();
            }
        });

        loadMahasiswaData(); // Muat data dari DB saat panel diinisialisasi
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 38));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    // Mengambil data dari database menggunakan DAO
    private void loadMahasiswaData() {
        tableModel.setRowCount(0); // Bersihkan tabel terlebih dahulu
        List<Mahasiswa> mahasiswaList = mahasiswaDAO.getAllMahasiswa();
        for (Mahasiswa mhs : mahasiswaList) {
            tableModel.addRow(new Object[]{mhs.getNim(), mhs.getNama(), mhs.getKelas(), mhs.getJurusan(), mhs.getSemester()});
        }
    }

    // Metode untuk menambah mahasiswa ke database
    private void addMahasiswa() {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String kelas = kelasField.getText().trim();
        String jurusan = jurusanField.getText().trim();
        String semesterStr = semesterField.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || kelas.isEmpty() || jurusan.isEmpty() || semesterStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int semester;
        try {
            semester = Integer.parseInt(semesterStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Semester harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cek duplikasi NIM sebelum mencoba menambah ke DB
        // Mahasiswa DAO harus memiliki metode untuk cek NIM exists
        // Untuk saat ini, kita bisa cek di tabel yang sudah dimuat (kurang akurat jika DB belum direfresh)
        // Idealnya, DAO punya Mahasiswa getMahasiswaByNim(String nim)
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(nim)) {
                JOptionPane.showMessageDialog(this, "NIM sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        Mahasiswa newMahasiswa = new Mahasiswa(nim, nama, kelas, jurusan, semester);
        if (mahasiswaDAO.addMahasiswa(newMahasiswa)) {
            JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadMahasiswaData(); // Refresh tabel dari DB
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data mahasiswa. Kemungkinan NIM sudah ada atau error database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metode untuk mengedit mahasiswa di database
    private void editMahasiswa() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String kelas = kelasField.getText().trim();
        String jurusan = jurusanField.getText().trim();
        String semesterStr = semesterField.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || kelas.isEmpty() || jurusan.isEmpty() || semesterStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int semester;
        try {
            semester = Integer.parseInt(semesterStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Semester harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Jika NIM diubah, cek duplikasi NIM baru (kecuali NIM yang sedang diedit)
        String originalNim = tableModel.getValueAt(selectedRow, 0).toString();
        if (!originalNim.equals(nim)) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (i != selectedRow && tableModel.getValueAt(i, 0).equals(nim)) {
                    JOptionPane.showMessageDialog(this, "NIM sudah ada pada data lain!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        Mahasiswa updatedMahasiswa = new Mahasiswa(nim, nama, kelas, jurusan, semester); // Gunakan NIM yang di field (bisa berubah)
        if (mahasiswaDAO.updateMahasiswa(updatedMahasiswa)) {
            JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil diupdate.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadMahasiswaData(); // Refresh tabel dari DB
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengupdate data mahasiswa. Cek NIM ada di DB.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metode untuk menghapus mahasiswa dari database
    private void deleteMahasiswa() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nimToDelete = tableModel.getValueAt(selectedRow, 0).toString(); // Ambil NIM dari baris yang dipilih
        
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data mahasiswa dengan NIM " + nimToDelete + "?\n(Ini juga mungkin menghapus data nilai yang terkait jika database dikonfigurasi demikian)", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (mahasiswaDAO.deleteMahasiswa(nimToDelete)) {
                JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadMahasiswaData(); // Refresh tabel dari DB
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data mahasiswa. Pastikan tidak ada data terkait (misal: nilai) atau error database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        nimField.setText("");
        namaField.setText("");
        kelasField.setText("");
        jurusanField.setText("");
        semesterField.setText("");
        studentTable.clearSelection(); // Hilangkan seleksi di tabel
    }

    // Menampilkan data dari tabel ke form
    private void displaySelectedMahasiswa() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            nimField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            namaField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            kelasField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            jurusanField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            semesterField.setText(tableModel.getValueAt(selectedRow, 4).toString());
        }
    }
}