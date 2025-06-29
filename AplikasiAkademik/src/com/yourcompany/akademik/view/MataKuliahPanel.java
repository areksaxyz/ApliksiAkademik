package com.yourcompany.akademik.view;

// Import Model dan DAO yang baru
import com.yourcompany.akademik.dao.MataKuliahDAO;
import com.yourcompany.akademik.model.MataKuliah;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List; // Penting untuk List

public class MataKuliahPanel extends JPanel {

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
    private JTable mataKuliahTable;
    private MataKuliahDAO mataKuliahDAO; // Instansiasi DAO

    public MataKuliahPanel() {
        mataKuliahDAO = new MataKuliahDAO(); // Inisialisasi DAO

        setLayout(new BorderLayout(25, 25));
        setBackground(BG_PRIMARY);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = new JLabel("Daftar Mata Kuliah & Jadwal");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_DARK);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- Table Area ---
        String[] columnNames = {"Kode MK", "Nama Mata Kuliah", "SKS", "Dosen Pengampu", "Jadwal", "Ruangan"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        mataKuliahTable = new JTable(tableModel);
        mataKuliahTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mataKuliahTable.setRowHeight(35);

        // Table Header Styling
        mataKuliahTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        mataKuliahTable.getTableHeader().setBackground(ACCENT_COLOR);
        mataKuliahTable.getTableHeader().setForeground(Color.WHITE);
        mataKuliahTable.getTableHeader().setReorderingAllowed(false);
        mataKuliahTable.getTableHeader().setResizingAllowed(true);

        // Table Body Styling
        mataKuliahTable.setSelectionBackground(HOVER_LIGHT);
        mataKuliahTable.setSelectionForeground(TEXT_DARK);
        mataKuliahTable.setGridColor(BORDER_LIGHT);
        mataKuliahTable.setShowVerticalLines(true);
        mataKuliahTable.setShowHorizontalLines(true);

        // Custom Cell Renderer for alternating row colors
        mataKuliahTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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


        JScrollPane scrollPane = new JScrollPane(mataKuliahTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT, 1));
        add(scrollPane, BorderLayout.CENTER);

        loadMataKuliahData(); // Muat data dari DB
    }

    // Mengambil data dari database menggunakan DAO
    private void loadMataKuliahData() {
        tableModel.setRowCount(0); // Bersihkan tabel terlebih dahulu
        List<MataKuliah> mataKuliahList = mataKuliahDAO.getAllMataKuliah();
        for (MataKuliah mk : mataKuliahList) {
            tableModel.addRow(new Object[]{mk.getKodeMk(), mk.getNamaMk(), mk.getSks(), mk.getDosenPengampu(), mk.getJadwal(), mk.getRuangan()});
        }
    }
}