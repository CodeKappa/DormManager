package Presentation.GUI.BucatarGUI;

import Presentation.GUI.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BucatarGUI extends GUI
{
    JButton logoutButton = GUI.createButton("Log out");
    JButton logoutButton2 = GUI.createButton("Log out");
    JTable meniuTable = new JTable();
    JComponent meniu = makeTextPanel("Panel meniu");
    JTable preparateTable = new JTable();
    JComponent preparate = makeTextPanel("Panel menie");

    private JTabbedPane tabbedPane;

    public BucatarGUI()
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

        tabbedPane.addTab("Meniu", null, meniu, "Modifica meniul");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.getSelectedComponent();

        tabbedPane.addTab("Produse", null, preparate, "Modifica produsele");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

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
        logoutButton2.addActionListener(listener);
    }
}
