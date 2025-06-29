package com.yourcompany.akademik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class SidebarPanel extends JPanel {

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


    private MainFrame parentFrame;
    private JPanel activeMenuItemPanel = null;
    private Map<String, JPanel> menuPanelMap;

    public SidebarPanel(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
        setBackground(BG_CONTENT); // White background for sidebar
        setPreferredSize(new Dimension(220, getHeight()));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_LIGHT)); // Right border

        menuPanelMap = new HashMap<>(); // Initialize the map

        // --- Logo Area ---
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(BG_CONTENT);
        logoPanel.setBorder(new EmptyBorder(25, 15, 25, 15));
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel logoLabel = new JLabel("e-learning"); // <--- PERUBAHAN DI SINI
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoLabel.setForeground(ACCENT_COLOR); // Logo uses accent color
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.NORTH);

        // --- Menu Buttons ---
        JPanel menuButtonsPanel = new JPanel();
        menuButtonsPanel.setBackground(BG_CONTENT);
        menuButtonsPanel.setLayout(new BoxLayout(menuButtonsPanel, BoxLayout.Y_AXIS));
        menuButtonsPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        createMenuItem(menuButtonsPanel, "Dashboard", "🏠", "Dashboard");
        createMenuItem(menuButtonsPanel, "Mahasiswa", "👨‍🎓", "Mahasiswa");
        createMenuItem(menuButtonsPanel, "Nilai", "💯", "Nilai");
        createMenuItem(menuButtonsPanel, "Mata Kuliah", "📚", "Mata Kuliah");
       

        menuButtonsPanel.add(Box.createVerticalGlue());

        add(menuButtonsPanel, BorderLayout.CENTER);

        // --- Logout Button ---
        JPanel logoutPanel = new JPanel();
        logoutPanel.setBackground(BG_CONTENT);
        logoutPanel.setBorder(new EmptyBorder(10, 15, 20, 15));
        logoutPanel.setLayout(new BorderLayout());
        JButton btnLogout = createStyledButton("Log Out", "➡️", BUTTON_DANGER); // Red for logout
        btnLogout.addActionListener(e -> parentFrame.logout());
        logoutPanel.add(btnLogout, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);

        // Set Dashboard as active by default
        SwingUtilities.invokeLater(() -> setActiveMenuItem("Dashboard"));
    }

    private JButton createStyledButton(String text, String emoji, Color bgColor) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        if (emoji != null) {
            button.setText(emoji + "  " + text);
            button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        }

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private void createMenuItem(JPanel parent, String text, String emoji, String panelName) {
        JPanel menuItemPanel = new JPanel();
        menuItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        menuItemPanel.setBackground(BG_CONTENT);
        menuItemPanel.setPreferredSize(new Dimension(200, 45)); // Consistent item height

        if (emoji != null) {
            JLabel emojiLabel = new JLabel(emoji);
            emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            menuItemPanel.add(emojiLabel);
        }

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textLabel.setForeground(TEXT_DARK); // Default text color
        menuItemPanel.add(textLabel);

        menuItemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (menuItemPanel != activeMenuItemPanel) {
                    menuItemPanel.setBackground(HOVER_LIGHT); // Lighter accent for hover
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (menuItemPanel != activeMenuItemPanel) {
                    menuItemPanel.setBackground(BG_CONTENT);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showPanel(panelName); // showPanel will call setActiveMenuItem
            }
        });
        
        menuPanelMap.put(panelName, menuItemPanel); // Store panel in map

        parent.add(menuItemPanel);
        parent.add(Box.createVerticalStrut(5));
    }

    public void setActiveMenuItem(String panelName) {
        if (activeMenuItemPanel != null) {
            activeMenuItemPanel.setBackground(BG_CONTENT); // Reset previous active item
            for (Component comp : activeMenuItemPanel.getComponents()) {
                if (comp instanceof JLabel) {
                    ((JLabel) comp).setForeground(TEXT_DARK);
                }
            }
        }
        
        JPanel targetPanel = menuPanelMap.get(panelName);
        if (targetPanel != null) {
            targetPanel.setBackground(ACTIVE_LIGHT); // Set new active item background
            for (Component comp : targetPanel.getComponents()) {
                if (comp instanceof JLabel) {
                    ((JLabel) comp).setForeground(ACCENT_COLOR); // Active text uses accent color
                }
            }
            activeMenuItemPanel = targetPanel;
        }
    }
}