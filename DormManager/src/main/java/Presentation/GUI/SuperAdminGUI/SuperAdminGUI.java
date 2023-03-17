package Presentation.GUI.SuperAdminGUI;

import Presentation.GUI.GUI;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class constructs the GUI for the super administrator.
 *
 * @Author: Kovacs Paul-Adrian
 * @Since: 1.0.0
 */
public class SuperAdminGUI extends GUI
{
    JButton logoutButton = GUI.createButton("Logout");
    JTable usersTable = new JTable();

    ArrayList<JLabel> labels;

    PanelCRUDSuperAdmin crudUsers;
    JPanel crudFacilitati = new JPanel();

    JTabbedPane tabbedPane;

    public SuperAdminGUI()
    {
        ArrayList<JLabel> labels = new ArrayList<>();

        labels.add(new JLabel("CNP"));      //0
        labels.add(new JLabel("Nume"));     //1
        labels.add(new JLabel("Prenume"));  //2
        labels.add(new JLabel("Adresa"));   //3
        labels.add(new JLabel("Telefon"));  //4
        labels.add(new JLabel("Email"));    //5
        labels.add(new JLabel("Sex"));      //6
        labels.add(new JLabel("Camera"));   //7
        labels.add(new JLabel("Camin"));    //8
        labels.add(new JLabel("Complex"));  //9
        labels.add(new JLabel("Cantina"));  //10
        labels.add(new JLabel("Parola"));   //11

        crudUsers = new PanelCRUDSuperAdmin(usersTable, logoutButton, labels);

        this.setSize(1500, 700);
        this.setLocationRelativeTo(null);
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

        tabbedPane.addTab("Utilizatori", null, crudUsers, "CRUD pentru utilizatori");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.getSelectedComponent();

        crudFacilitati.setBackground(new Color(39, 39, 39));
        tabbedPane.addTab("Facilitati", null, crudFacilitati, "CRUD pentru facilitati");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        return tabbedPane;
    }

    public void addTabChangeListener(ChangeListener listener)
    {
        tabbedPane.addChangeListener(listener);
    }

    public void refreshTable(ArrayList<ArrayList<String>> users)
    {
        crudUsers.setTable(crudUsers.getUsersTable(), users);
    }

    public void addTableListerner(MouseListener listener)
    {
        crudUsers.addTableListener(listener);
    }

    public void setComboBox(String tip)
    {
        crudUsers.setComboBox(tip);
    }

    public int getSelectedTab()
    {
        return tabbedPane.getSelectedIndex();
    }

    public JTable getUsersTable()
    {
        return usersTable;
    }

    public void addLogoutListener(ActionListener listener)
    {
        logoutButton.addActionListener(listener);
    }

    public void setButtonListeners(ArrayList<ActionListener> listeners)
    {
        crudUsers.setButtonListeners(listeners);
    }

    public ArrayList<String> getUserData()
    {
        ArrayList<String> data = new ArrayList<>();
        for(JTextField jtf : crudUsers.getDateUseri())
        {
            data.add(jtf.getText());
        }

        switch ((String) crudUsers.getCb().getSelectedItem())
        {
            case "Admin": data.add("Admin"); break;
            case "Super Admin": data.add("Super Admin"); break;
            case "Student": data.add("Student"); break;
            case "Bucatar": data.add("Bucatar"); break;
            case "Mester": data.add("Mester"); break;
        }

        return data;
    }
}


