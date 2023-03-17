package Presentation.GUI.MesterGUI;

import Presentation.GUI.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MesterGUI extends GUI
{
    JButton logoutButton = GUI.createButton("Log out");
    JTable usersTable = new JTable();
    JComponent reparatii = makeTextPanel("Panel reparatii");


    private JTabbedPane tabbedPane;

    public MesterGUI()
    {
        this.setSize(1500, 700);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.getContentPane().add(makeTabbedPane());
    }

    private JTabbedPane makeTabbedPane()
    {
        Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
        insets.top = 0;
        insets.bottom = 0;
        insets.left = 0;
        insets.right = 0;
        UIManager.put("TabbedPane.contentBorderInsets", insets);
        UIManager.put("TabbedPane.selected", new Color(39, 39, 39));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFocusable(false);
        tabbedPane.setBackground(new Color(143, 0, 0));
        tabbedPane.setForeground(Color.WHITE);

        tabbedPane.addTab("Reparatii", null, reparatii, "Gestioneaza reparatiile");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.getSelectedComponent();

        return tabbedPane;
    }

    private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setForeground(new Color(255, 255, 255));
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        panel.setBackground(new Color(39, 39, 39));
        return panel;
    }

    public void addLogoutListener(ActionListener listener)
    {
        logoutButton.addActionListener(listener);
    }


}
