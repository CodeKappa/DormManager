package Presentation.GUI.AdminGUI;

import Presentation.GUI.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelCazare extends JPanel
{
    private RoomView roomView;
    private JTable studentTable;
    private JPanel floorPanel;
    private JButton refreshButton;
    private JButton logOutButton;
    JList<String> floorList;
    String[] header = { "CNP", "Student", "Sex"};
    private int nr_etaje;

    PanelCazare(JButton logOutButton)
    {
        this.roomView = new RoomView(1000,700);
        this.setBackground(new Color(39, 39, 39));
        JScrollPane panelRooms = new JScrollPane();
        panelRooms.getVerticalScrollBar().setBackground(new Color(143, 0, 0));
        panelRooms.getHorizontalScrollBar().setBackground(new Color(143, 0, 0));
        panelRooms.setViewportView(roomView);
        panelRooms.setBackground(new Color(39, 39, 39));
        panelRooms.getViewport().setOpaque(false);
        panelRooms.setViewportBorder(null);
        panelRooms.setBorder(null);

        this.studentTable = new JTable();
        JScrollPane panelStudenti = new JScrollPane(studentTable);
        panelStudenti.getViewport().setBackground(new Color(39, 39, 39));
        panelStudenti.getVerticalScrollBar().setBackground(new Color(143, 0, 0));
        panelStudenti.getHorizontalScrollBar().setBackground(new Color(143, 0, 0));
        studentTable.setBackground(new Color(39, 39, 39));
        studentTable.setForeground(Color.WHITE);
        studentTable.setGridColor(new Color(143, 0, 0));
        studentTable.getTableHeader().setBackground(new Color(143, 0, 0));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setAutoCreateRowSorter(true);

        floorPanel = new JPanel();
        floorPanel.setBackground(new Color(39, 39, 39));
        floorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        floorList = new JList<>();
        floorList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        floorList.setVisibleRowCount(1);
        floorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        floorList.setForeground(Color.WHITE);
        floorList.setBackground(new Color(39, 39, 39));
        floorPanel.add(floorList);

        setFloors(0);

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(new Color(39, 39, 39));
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        refreshButton = GUI.createButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(225, 25));
        this.logOutButton = logOutButton;
        logOutButton.setPreferredSize(new Dimension(225, 25));
        panelButtons.add(refreshButton);
        panelButtons.add(logOutButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panelRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                                .addComponent(floorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(panelStudenti, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(panelRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                                .addComponent(panelStudenti, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(floorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );
    }

    public void setFloors(int n)
    {
        //floorPanel.removeAll();
        this.nr_etaje = n;
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for(int i = 1; i <= nr_etaje; i++)
        {
            l1.addElement(" Etajul " + i + " ");
        }
        floorList.setModel(l1);
        repaint();
        revalidate();
    }

    public JList<String> getFloorList()
    {
        return floorList;
    }

    JTable getStudentTable()
    {
        return studentTable;
    }

    public void addTableListener(MouseListener listener)
    {
        studentTable.addMouseListener(listener);
    }

    public void addFloorListListener(ListSelectionListener lsl)
    {
        floorList.addListSelectionListener(lsl);
    }

    public void setTable(JTable table, ArrayList<ArrayList<String>> list)
    {
        DefaultTableModel dtm = new DefaultTableModel()
        {
            @Override
            public String getColumnName(int index)
            {
                return header[index];
            }

            @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }

            @Override
            public int getColumnCount()
            {
                return header.length;
            }

            @Override
            public int getRowCount()
            {
                return list.size();
            }
        };

        int i = 0, j;
        for(ArrayList<String> row : list)
        {
            j = 0;
            for(String col : row)
            {
                dtm.setValueAt(col, i, j);
                j++;
            }
            i++;
        }
        table.setModel(dtm);
        repaint();
    }

    RoomView getRoomView()
    {
        return roomView;
    }

    void setRoomView(RoomView roomView)
    {
        this.roomView = roomView;
    }

    void addRefreshButtonListener(ActionListener listener)
    {
        refreshButton.addActionListener(listener);
    }

    public int getSelectedFloor()
    {
        return floorList.getSelectedIndex()+1;
    }

    public void setSelectedFloor(int selectedFloor)
    {
        floorList.setSelectedIndex(selectedFloor);
    }

    public int getEtaje()
    {
        return nr_etaje;
    }
}
