package com.yourcompany.akademik.view;

// Import Model dan DAO yang baru
import com.yourcompany.akademik.dao.MahasiswaDAO;
import com.yourcompany.akademik.dao.NilaiDAO;
import com.yourcompany.akademik.model.Mahasiswa;
import com.yourcompany.akademik.model.Nilai;
import com.yourcompany.akademik.dao.MataKuliahDAO; // Perlu untuk mendapatkan nama mata kuliah
import com.yourcompany.akademik.model.MataKuliah;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class NilaiPanel extends JPanel {

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
    private JTable nilaiTable;
    private JComboBox<Mahasiswa> studentComboBox; // Menggunakan JComboBox dengan objek Mahasiswa
    
    private MahasiswaDAO mahasiswaDAO;
    private NilaiDAO nilaiDAO;
    private MataKuliahDAO mataKuliahDAO; // Digunakan untuk memetakan kode MK ke nama MK

    // Map untuk menyimpan nama mata kuliah berdasarkan kode MK (untuk ditampilkan)
    private Map<String, String> kodeMkToNamaMkMap;

    public NilaiPanel() {
        mahasiswaDAO = new MahasiswaDAO();
        nilaiDAO = new NilaiDAO();
        mataKuliahDAO = new MataKuliahDAO();
        
        // Memuat semua nama mata kuliah ke dalam map saat inisialisasi panel
        // Ini agar kita bisa menampilkan nama mata kuliah di tabel Nilai, bukan hanya kodenya
        kodeMkToNamaMkMap = new HashMap<>();
        List<MataKuliah> allCourses = mataKuliahDAO.getAllMataKuliah();
        for (MataKuliah mk : allCourses) {
            kodeMkToNamaMkMap.put(mk.getKodeMk(), mk.getNamaMk());
        }

        setLayout(new BorderLayout(25, 25));
        setBackground(BG_PRIMARY);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = new JLabel("Lihat Data Nilai Mahasiswa");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_DARK);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Filter Section ---
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        filterPanel.setBackground(BG_PRIMARY);
        filterPanel.setBorder(new EmptyBorder(0,0,15,0));

        JLabel filterLabel = new JLabel("Pilih Mahasiswa:");
        filterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        filterLabel.setForeground(TEXT_DARK);
        
        // Memuat data mahasiswa ke ComboBox dari database
        DefaultComboBoxModel<Mahasiswa> studentModel = new DefaultComboBoxModel<>();
        studentModel.addElement(new Mahasiswa("", "Pilih Mahasiswa", "", "", 0)); // Placeholder awal
        List<Mahasiswa> allStudents = mahasiswaDAO.getAllMahasiswa();
        for (Mahasiswa mhs : allStudents) {
            studentModel.addElement(mhs); // Menambahkan objek Mahasiswa
        }
        
        studentComboBox = new JComboBox<>(studentModel);
        studentComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        studentComboBox.setBackground(BG_CONTENT);
        studentComboBox.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT));
        studentComboBox.addActionListener(e -> loadNilaiForSelectedStudent()); // Panggil metode saat pilihan berubah

        filterPanel.add(filterLabel);
        filterPanel.add(studentComboBox);
        
        JPanel topContentPanel = new JPanel(new BorderLayout());
        topContentPanel.setBackground(BG_PRIMARY);
        topContentPanel.add(filterPanel, BorderLayout.NORTH);

        // --- Nilai Table ---
        String[] columnNames = {"Mata Kuliah", "Absensi", "Quiz", "UTS", "UAS", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        nilaiTable = new JTable(tableModel);
        nilaiTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nilaiTable.setRowHeight(35);

        // Table Header Styling
        nilaiTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        nilaiTable.getTableHeader().setBackground(ACCENT_COLOR);
        nilaiTable.getTableHeader().setForeground(Color.WHITE);
        nilaiTable.getTableHeader().setReorderingAllowed(false);
        nilaiTable.getTableHeader().setResizingAllowed(true);

        // Table Body Styling
        nilaiTable.setSelectionBackground(HOVER_LIGHT);
        nilaiTable.setSelectionForeground(TEXT_DARK);
        nilaiTable.setGridColor(BORDER_LIGHT);
        nilaiTable.setShowVerticalLines(true);
        nilaiTable.setShowHorizontalLines(true);

        // Custom Cell Renderer for alternating row colors
        nilaiTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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


        JScrollPane scrollPane = new JScrollPane(nilaiTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT, 1));
        
        topContentPanel.add(scrollPane, BorderLayout.CENTER);

        add(topContentPanel, BorderLayout.CENTER);

        loadNilaiForSelectedStudent(); // Muat nilai awal (akan kosong jika belum ada pilihan)
    }

    // Mengambil nilai untuk mahasiswa yang dipilih dari database
    private void loadNilaiForSelectedStudent() {
        tableModel.setRowCount(0); // Bersihkan tabel

        Mahasiswa selectedMahasiswa = (Mahasiswa) studentComboBox.getSelectedItem();

        // Hanya muat jika ada mahasiswa yang valid dipilih (bukan placeholder)
        if (selectedMahasiswa != null && !selectedMahasiswa.getNim().isEmpty()) {
            List<Nilai> nilaiList = nilaiDAO.getNilaiByNim(selectedMahasiswa.getNim());
            for (Nilai nilai : nilaiList) {
                // Konversi kode_mk menjadi nama_mk menggunakan map
                String namaMk = kodeMkToNamaMkMap.getOrDefault(nilai.getKodeMk(), nilai.getKodeMk()); // Fallback to code if name not found
                tableModel.addRow(new Object[]{
                    namaMk, // Tampilkan nama mata kuliah
                    nilai.getAbsensi(),
                    nilai.getQuiz(),
                    nilai.getUts(),
                    nilai.getUas(),
                    nilai.getGrade()
                });
            }
        }
    }
}