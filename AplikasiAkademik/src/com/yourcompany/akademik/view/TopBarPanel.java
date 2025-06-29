package com.yourcompany.akademik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBarPanel extends JPanel {

    // Global Color Palette
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


    private JLabel pageTitleLabel;
    private MainFrame parentFrame; // Added reference to MainFrame

    public TopBarPanel(MainFrame parentFrame) { // Constructor now accepts MainFrame
        this.parentFrame = parentFrame;
        setBackground(BG_CONTENT);
        setPreferredSize(new Dimension(getWidth(), 70));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_LIGHT)); // Bottom border

        // --- Left Section (Title) ---
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 15));
        leftPanel.setBackground(BG_CONTENT);
        pageTitleLabel = new JLabel("Dashboard");
        pageTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        pageTitleLabel.setForeground(TEXT_DARK);
        leftPanel.add(pageTitleLabel);
        add(leftPanel, BorderLayout.WEST);

        // --- Center Section (Search Bar) ---
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        centerPanel.setBackground(BG_CONTENT);
        JTextField searchField = new JTextField("Search...", 30);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_LIGHT, 1), // Use global border color
            new EmptyBorder(5, 10, 5, 10)
        ));
        
        // Add ActionListener to searchField
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim().toLowerCase();
                if (searchText.contains("mahasiswa")) {
                    parentFrame.showPanel("Mahasiswa");
                } else if (searchText.contains("nilai")) {
                    parentFrame.showPanel("Nilai");
                } else if (searchText.contains("kuliah") || searchText.contains("mata kuliah") || searchText.contains("matkul")) {
                    parentFrame.showPanel("Mata Kuliah"); // Pastikan nama panel sesuai
                } else if (searchText.contains("dashboard") || searchText.contains("home") || searchText.contains("beranda")) {
                    parentFrame.showPanel("Dashboard");
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Halaman '" + searchField.getText() + "' tidak ditemukan.", "Pencarian", JOptionPane.INFORMATION_MESSAGE);
                }
                searchField.setText(""); // Clear search field after action
            }
        });

        centerPanel.add(searchField);
        add(centerPanel, BorderLayout.CENTER);

        // --- Right Section (Icons/User Info) ---
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        rightPanel.setBackground(BG_CONTENT);

        JLabel notificationIcon = createEmojiIcon("🔔", 24);
        notificationIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel userIcon = createEmojiIcon("👤", 24);
        userIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rightPanel.add(notificationIcon);
        rightPanel.add(userIcon);

        add(rightPanel, BorderLayout.EAST);
    }

    public void setPageTitle(String title) {
        this.pageTitleLabel.setText(title);
    }

    private JLabel createEmojiIcon(String emoji, int fontSize) {
        JLabel label = new JLabel(emoji);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        label.setForeground(TEXT_DARK); // Icon color
        return label;
    }
}