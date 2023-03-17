package Presentation.GUI.AdminGUI;

import Presentation.GUI.GUI;
import Presentation.GUI.WrapLayout;
import Presentation.Listeners.EventItemSelected;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class AdminGUI extends GUI
{
    JButton logoutButton = GUI.createButton("Log out");
    private PanelCazare panelCazare = new PanelCazare(logoutButton);
    ArrayList<RoomData> lista;
    private JComponent panel2 = makeTextPanel("Panel #2");
    private JComponent panel3 = makeTextPanel("Panel #3");

    private JTabbedPane tabbedPane;

    public AdminGUI()
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

        tabbedPane.addTab("Cazare", null, panelCazare, "Administrateaza cazarea");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.getSelectedComponent();

        tabbedPane.addTab("Cereri transfer", null, panel2, "Cereri de transfer");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Anunturi", null, panel3, "Anunturi");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        return tabbedPane;
    }

    private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        panel.setBackground(new Color(39, 39, 39));
        return panel;
    }

    public void setCazareRefreshListener(ActionListener actionListener)
    {
        panelCazare.addRefreshButtonListener(actionListener);
    }

    public JTable getUsersTable()
    {
        return panelCazare.getStudentTable();
    }

    public void refresh(ArrayList<ArrayList<String>> users, ArrayList<ArrayList<String>> camere, int selectedFloor)
    {
        ArrayList<ArrayList<String>> displayData = new ArrayList<>();
        lista = new ArrayList<>();
        int etaje = 0;

        for(ArrayList<String> row : camere)
        {
            lista.add(new RoomData(Integer.parseInt(row.get(0)),
                                   Integer.parseInt(row.get(1)),
                                   Integer.parseInt(row.get(2)),
                                   new ArrayList<String>(),
                                   null,
                                   0,
                                   new ArrayList<String>()));
            if(Integer.parseInt(row.get(1)) / 100 > etaje);
            {
                etaje = Integer.parseInt(row.get(1)) / 100;
            }
        }

        if( panelCazare.getEtaje() != etaje)
            panelCazare.setFloors(etaje);

        boolean added;
        for(ArrayList<String> row : users)
        {
            ArrayList<String> displayRow = new ArrayList<>();
            added = false;

            for(RoomData rd : lista)
            {
                if(row.get(7) != null)
                    if(rd.id == Integer.parseInt(row.get(7)))
                    {
                        rd.students.add(row.get(1) + " " + row.get(2));
                        rd.studentsCNP.add(row.get(0));
                        rd.tipCamera = row.get(6);
                        added = true;
                    }
            }

            if(added == false)
            {
                displayRow.add(row.get(0)); //cnp
                displayRow.add(row.get(1) + " " + row.get(2)); //nume + prenume
                displayRow.add(row.get(6)); //sex
                displayData.add(displayRow);
            }
        }

        panelCazare.getRoomView().setItems(lista, selectedFloor + 1);
        panelCazare.setTable(panelCazare.getStudentTable(), displayData);
    }

    public void addTableListerner(MouseListener listener)
    {
        panelCazare.addTableListener(listener);
    }

    public void addLogoutListener(ActionListener listener)
    {
        logoutButton.addActionListener(listener);
    }

    public void setRefreshListener(ActionListener listeners)
    {
        panelCazare.addRefreshButtonListener(listeners);
    }

    public void addFloorListListener(ListSelectionListener lsl)
    {
        panelCazare.addFloorListListener(lsl);
    }

    public JList<String> getList()
    {
        return panelCazare.getFloorList();
    }

    public void setRoomListener(EventItemSelected eis)
    {
        panelCazare.getRoomView().setEvent(eis);
    }
}






