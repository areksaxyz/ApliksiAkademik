package com.yourcompany.akademik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter; // Required for MouseAdapter in card
import java.awt.event.MouseEvent;   // Required for MouseEvent in card

public class DashboardContentPanel extends JPanel {

    private String userName;

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


    public DashboardContentPanel(String userName) {
        this.userName = userName;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Menggunakan BoxLayout vertikal
        setBackground(BG_PRIMARY);
        setBorder(new EmptyBorder(40, 40, 40, 40)); // Padding lebih besar

        // --- Welcome Message Container ---
        JPanel welcomeContainer = new JPanel();
        welcomeContainer.setLayout(new BoxLayout(welcomeContainer, BoxLayout.Y_AXIS));
        welcomeContainer.setBackground(BG_CONTENT);
        welcomeContainer.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_LIGHT, 1),
            new EmptyBorder(50, 80, 50, 80)
        ));
        welcomeContainer.setAlignmentX(Component.CENTER_ALIGNMENT); // Pusatkan container secara horizontal

        JLabel welcomeEmoji = createEmojiIcon("👋", 90);
        welcomeEmoji.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeContainer.add(welcomeEmoji);

        welcomeContainer.add(Box.createVerticalStrut(20));

        JLabel welcomeTitle = new JLabel("Halo, " + this.userName + "!");
        welcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 52));
        welcomeTitle.setForeground(TEXT_DARK);
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeContainer.add(welcomeTitle);

        welcomeContainer.add(Box.createVerticalStrut(15));

        JLabel welcomeSubtitle = new JLabel("Selamat datang kembali di Sistem Informasi Akademik Anda.");
        welcomeSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        welcomeSubtitle.setForeground(TEXT_MEDIUM);
        welcomeSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeContainer.add(welcomeSubtitle);

        welcomeContainer.add(Box.createVerticalStrut(25));

        JLabel guidanceText = new JLabel("Gunakan menu di samping kiri untuk mengelola data akademik Anda.");
        guidanceText.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        guidanceText.setForeground(TEXT_MEDIUM);
        guidanceText.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeContainer.add(guidanceText);

        add(welcomeContainer); // Add the welcome container
        add(Box.createVerticalStrut(40)); // Spacer between welcome and new section


        // --- New Section: Quick Overview / Summary Cards ---
        JPanel summarySectionPanel = new JPanel();
        summarySectionPanel.setLayout(new GridLayout(1, 2, 30, 0)); // 1 row, 2 columns with spacing
        summarySectionPanel.setBackground(BG_PRIMARY); // Use main background
        summarySectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Pusatkan horizontal

        // Card 1: Upcoming Events/Deadlines
        JPanel upcomingEventsCard = createInfoCard("Aktivitas Mendatang", "Cek tugas dan jadwal Anda.", "🗓️");
        summarySectionPanel.add(upcomingEventsCard);

        // Card 2: Quick Links/Important Notices
        JPanel quickLinksCard = createInfoCard("Pemberitahuan Penting", "Informasi terbaru dari kampus.", "📢");
        summarySectionPanel.add(quickLinksCard);

        add(summarySectionPanel); // Add the new summary section
        add(Box.createVerticalGlue()); // Push content to the top
    }

    // Helper method to create a sleek info card
    private JPanel createInfoCard(String title, String description, String emoji) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 15)); // Spacing inside card
        card.setBackground(BG_CONTENT);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_LIGHT, 1),
            new EmptyBorder(25, 25, 25, 25)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Make it look clickable

        JLabel cardEmoji = createEmojiIcon(emoji, 40); // Larger emoji for card
        cardEmoji.setHorizontalAlignment(SwingConstants.LEFT);
        card.add(cardEmoji, BorderLayout.WEST);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(BG_CONTENT);
        textPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align text to left within its panel

        JLabel cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        cardTitle.setForeground(TEXT_DARK);
        cardTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(cardTitle);

        textPanel.add(Box.createVerticalStrut(8));

        JTextArea cardDescription = new JTextArea(description);
        cardDescription.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cardDescription.setForeground(TEXT_MEDIUM);
        cardDescription.setEditable(false);
        cardDescription.setWrapStyleWord(true);
        cardDescription.setLineWrap(true);
        cardDescription.setBackground(BG_CONTENT);
        cardDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(cardDescription);
        
        card.add(textPanel, BorderLayout.CENTER);

        // Add a subtle hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(HOVER_LIGHT); // Subtle color change on hover
                textPanel.setBackground(HOVER_LIGHT); // Apply to inner text panel too
                cardDescription.setBackground(HOVER_LIGHT); // Apply to text area too
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(BG_CONTENT);
                textPanel.setBackground(BG_CONTENT);
                cardDescription.setBackground(BG_CONTENT);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Here you can add actions, e.g., parentFrame.showPanel("Schedules");
                JOptionPane.showMessageDialog(card, "Anda mengklik: " + title, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return card;
    }

    private JLabel createEmojiIcon(String emoji, int fontSize) {
        JLabel label = new JLabel(emoji);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        label.setForeground(ACCENT_COLOR); // Emojis in accent color
        return label;
    }
}