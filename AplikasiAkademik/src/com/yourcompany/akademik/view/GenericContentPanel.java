package com.yourcompany.akademik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GenericContentPanel extends JPanel {

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


    public GenericContentPanel(String title, String placeholderText) {
        setLayout(new BorderLayout(25, 25));
        setBackground(BG_PRIMARY);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_DARK);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentArea = new JPanel();
        contentArea.setLayout(new BorderLayout(20,20));
        contentArea.setBackground(BG_CONTENT);
        contentArea.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_LIGHT, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JTextArea infoText = new JTextArea(placeholderText);
        infoText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        infoText.setEditable(false);
        infoText.setWrapStyleWord(true);
        infoText.setLineWrap(true);
        infoText.setForeground(TEXT_MEDIUM);
        infoText.setBackground(BG_CONTENT);

        contentArea.add(new JScrollPane(infoText), BorderLayout.CENTER);

        add(contentArea, BorderLayout.CENTER);
    }
}