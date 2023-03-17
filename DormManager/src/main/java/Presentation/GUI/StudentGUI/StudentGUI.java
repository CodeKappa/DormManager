package Presentation.GUI.StudentGUI;

import Presentation.GUI.GUI;
import Presentation.GUI.WrapLayout;
import Presentation.Listeners.EventSpalatorie;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StudentGUI extends GUI
{
    JButton logoutButton = GUI.createButton("Log out");

    private ProgramSpalatorie panelProgram = new ProgramSpalatorie(logoutButton);
    ArrayList<WeekData> programari;

    JTable terenTable = new JTable();
    JComponent teren = makeTextPanel("Panel teren");

    JTable reparatiiTable = new JTable();
    JComponent reparatiiStudent = makeTextPanel("Panel reparatiiStudent");

    JTable anunturiTable = new JTable();
    JComponent anunturi = makeTextPanel("Panel anunturi");

    JTable meniuTable = new JTable();
    JComponent meniuStudent = makeTextPanel("Panel meniuStudent");

    JTable tcamereTable = new JTable();
    JComponent transferCamere = makeTextPanel("Panel transferCamere");

    JTable tcaminTable = new JTable();
    JComponent transferCamin = makeTextPanel("Panel transferCamin");

    private JTabbedPane tabbedPane;

    public StudentGUI()
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

        tabbedPane.addTab("Spalatorie", null, panelProgram, "Programari spalatorie");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.getSelectedComponent();

        tabbedPane.addTab("Teren de fotbal", null, teren, "Programari teren de fotbal");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Reparatii", null, reparatiiStudent, "Cerere reparatii");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.addTab("Anunturi", null, anunturi, "Anunturi");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        tabbedPane.addTab("Meniu", null, meniuStudent, "All students");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

        tabbedPane.addTab("Trasnfer intre camere", null, transferCamere, "Cerere de transfer intre camere");
        tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);

        tabbedPane.addTab("Transfer intre camine", null, transferCamin, "Cerere de transfer intre camine");
        tabbedPane.setMnemonicAt(6, KeyEvent.VK_6);


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

    public void refresh(ArrayList<ArrayList<String>> programare)
    {
        programari = new ArrayList<>();
        for(int i = 0; i < 6 * 7; i++)
        {
            programari.add(new WeekData(8 + i / 7 * 3 , 0));
        }

        for(ArrayList<String> p : programare)
        {
            int ora = Integer.parseInt(p.get(0));
            int zi = Integer.parseInt(p.get(1));
            int camera = Integer.parseInt(p.get(2));
            programari.set(zi + (ora - 8) / 3 * 7, new WeekData(ora, camera));
        }

        panelProgram.getDateView().setItems(programari);
    }

    public void addLogoutListener(ActionListener listener)
    {
        logoutButton.addActionListener(listener);
    }

    public void setRefreshListener(ActionListener listeners)
    {
        panelProgram.addRefreshButtonListener(listeners);
    }

    public void setProgramareListener(EventSpalatorie es)
    {
        panelProgram.getDateView().setEvent(es);
    }

    public JList<String> getList()
    {
        return panelProgram.getChangeWeekList();
    }

    public void addChangeWeekListener(ListSelectionListener listener)
    {
        panelProgram.addChangeWeekListener(listener);
    }
}