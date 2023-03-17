package Presentation.GUI.StudentGUI;

import Presentation.GUI.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProgramSpalatorie extends JPanel
{
    private DateView dateView;
    private JPanel changeWeekPanel;
    private JButton refreshButton;
    private JButton logOutButton;
    JList<String> changeWeekList;

    ProgramSpalatorie(JButton logOutButton)
    {
        this.dateView = new DateView(1500,700);
        this.setBackground(new Color(39, 39, 39));
        JScrollPane panelProgramari = new JScrollPane();
        panelProgramari.getVerticalScrollBar().setBackground(new Color(143, 0, 0));
        panelProgramari.getHorizontalScrollBar().setBackground(new Color(143, 0, 0));
        panelProgramari.setViewportView(dateView);
        panelProgramari.setBackground(new Color(39, 39, 39));
        panelProgramari.getViewport().setOpaque(false);
        panelProgramari.setViewportBorder(null);
        panelProgramari.setBorder(null);

        changeWeekPanel = new JPanel();
        changeWeekPanel.setBackground(new Color(39, 39, 39));
        changeWeekPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        changeWeekList = new JList<>();
        changeWeekList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        changeWeekList.setVisibleRowCount(1);
        changeWeekList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        changeWeekList.setForeground(Color.WHITE);
        changeWeekList.setBackground(new Color(39, 39, 39));
        changeWeekPanel.add(changeWeekList);

        setArrows();

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(new Color(39, 39, 39));
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        refreshButton = GUI.createButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(225, 25));
        this.logOutButton = logOutButton;
        logOutButton.setPreferredSize(new Dimension(225, 25));
        panelButtons.add(refreshButton);
        panelButtons.add(logOutButton);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelProgramari, GroupLayout.DEFAULT_SIZE, 1500,  Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(changeWeekPanel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(panelProgramari, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(changeWeekPanel, GroupLayout.DEFAULT_SIZE, 40, 40)
                                        .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, 40, 40))))
        );

    }

    public void setArrows()
    {
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("     Saptamana Curenta    ");
        l1.addElement("   Urmatoarea Saptamana   ");
        changeWeekList.setModel(l1);
        repaint();
        revalidate();
    }

    public JList<String> getChangeWeekList()
    {
        return changeWeekList;
    }

    public void addChangeWeekListener(ListSelectionListener lsl)
    {
        changeWeekList.addListSelectionListener(lsl);
    }

    void addRefreshButtonListener(ActionListener listener)
    {
        refreshButton.addActionListener(listener);
    }

    public DateView getDateView()
    {
        return dateView;
    }
}
