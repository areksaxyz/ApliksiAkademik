package com.yourcompany.akademik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    // Global Color Palette - Defined in each class for simplicity of copy-pasting
    // Ideally, these would be in a separate AppColors.java class
    protected static final Color BG_PRIMARY = new Color(245, 247, 249); // Main body background
    protected static final Color BG_CONTENT = Color.WHITE; // Card/Content background
    protected static final Color ACCENT_COLOR = new Color(69, 170, 159); // Primary accent teal
    protected static final Color TEXT_DARK = new Color(36, 41, 46); // Dark text for headings
    protected static final Color TEXT_MEDIUM = new Color(108, 117, 125); // Medium text for secondary
    protected static final Color BORDER_LIGHT = new Color(223, 227, 230); // Light border/separator
    protected static final Color HOVER_LIGHT = new Color(230, 248, 246); // Light hover for sidebar
    protected static final Color ACTIVE_LIGHT = new Color(200, 240, 235); // Active state for sidebar
    protected static final Color BUTTON_SUCCESS = new Color(40, 167, 69);
    protected static final Color BUTTON_DANGER = new Color(220, 53, 69);
    protected static final Color BUTTON_NEUTRAL = new Color(108, 117, 125);

    private JPanel contentPanel;
    private TopBarPanel topBarPanel;
    private SidebarPanel sidebarPanel;

    public MainFrame() {
        setTitle("Sistem Informasi Akademik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1350, 900); // Slightly larger for more space
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        topBarPanel = new TopBarPanel(this); // Pass MainFrame instance to TopBarPanel
        add(topBarPanel, BorderLayout.NORTH);

        sidebarPanel = new SidebarPanel(this);
        add(sidebarPanel, BorderLayout.WEST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        contentPanel.setBackground(BG_PRIMARY); // Main background for content area

        // Initialize content panels
        DashboardContentPanel dashboardContentPanel = new DashboardContentPanel("Ega Silfhia");
        MahasiswaPanel mahasiswaPanel = new MahasiswaPanel();
        NilaiPanel nilaiPanel = new NilaiPanel();
        MataKuliahPanel mataKuliahPanel = new MataKuliahPanel();
        

        // Add panels to CardLayout
        contentPanel.add(dashboardContentPanel, "Dashboard");
        contentPanel.add(mahasiswaPanel, "Mahasiswa");
        contentPanel.add(nilaiPanel, "Nilai");
        contentPanel.add(mataKuliahPanel, "Mata Kuliah");

        add(contentPanel, BorderLayout.CENTER);

        // Display Dashboard by default
        showPanel("Dashboard");
    }

    public void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, panelName);
        topBarPanel.setPageTitle(panelName);
        sidebarPanel.setActiveMenuItem(panelName); // Update active state in sidebar
    }

    public void logout() {
        int confirm = JOptionPane.showConfirmDialog(MainFrame.this, "Apakah Anda yakin ingin keluar dari aplikasi?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}