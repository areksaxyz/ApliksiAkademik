package com.yourcompany.akademik.main;

import com.yourcompany.akademik.view.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
    public static void main(String[] args) {
       
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); 
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            System.err.println("Gagal mengatur FlatLaf Look and Feel. Pastikan JAR FlatLaf ada di classpath.");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}