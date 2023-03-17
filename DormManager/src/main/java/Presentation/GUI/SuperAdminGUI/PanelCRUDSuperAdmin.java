package Presentation.GUI.SuperAdminGUI;

import Presentation.GUI.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelCRUDSuperAdmin extends JPanel
{
    JButton logoutButton;

    ArrayList<JTextField> dateUseri = new ArrayList<>();

    JTable usersTable;

    String[] choices = { "Student", "Admin", "Mester", "Bucatar", "Super Admin"};
    final JComboBox<String> cb = new JComboBox<String>(choices);

    ArrayList<JLabel> labels = new ArrayList<>();
    JPanel data = new JPanel();

    JButton addButton = GUI.createButton("Add");
    JButton deleteButton = GUI.createButton("Delete");
    JButton updateButton = GUI.createButton("Update");
    JButton viewButton = GUI.createButton("View");

    public PanelCRUDSuperAdmin(JTable usersTable, JButton logoutButton, ArrayList<JLabel> labels)
    {
        this.usersTable = usersTable;
        this.logoutButton = logoutButton;
        this.labels = labels;
        this.setBackground(new Color(39, 39, 39));

        data.setLayout(new GridBagLayout());
        data.setBackground(new Color(39, 39, 39));
        GridBagConstraints c = new GridBagConstraints();

        c.weighty = 1;
        c.gridy = 0; c.gridx = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth = 2;
        c.ipady = 1;
        c.ipadx = 1;
        JLabel labelTOP = new JLabel("CRUD Users");
        labelTOP.setForeground(Color.WHITE);
        data.add(labelTOP, c);

        cb.setPreferredSize(new Dimension(175, 20));
        c.weighty = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 1; c.gridx = 0;
        JLabel labelUtilizator = new JLabel("Tip utilizator:");
        labelUtilizator.setForeground(Color.WHITE);
        data.add(labelUtilizator, c);
        c.gridy = 1; c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;
        data.add(cb,c);

        for (JLabel label : this.labels)
        {
            label.setForeground(Color.WHITE);
        }

        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 1, 0);
        int i;
        for (i = 0; i < this.labels.size(); i++)
        {
            c.gridy = i + 2; c.gridx = 0; c.anchor = GridBagConstraints.LINE_START;
            data.add(this.labels.get(i), c);
            c.gridy = i + 2; c.gridx = 1; c.anchor = GridBagConstraints.LINE_END;
            JTextField field = (JTextField) GUI.createField(20, false);
            field.setBackground(new Color(200, 200, 200));
            data.add(field, c);
            dateUseri.add(field);
        }

        cb.addActionListener(e -> setLabels());
        setLabels();

        addButton.setPreferredSize(new Dimension(275, 25));
        deleteButton.setPreferredSize(new Dimension(275, 25));
        updateButton.setPreferredSize(new Dimension(275, 25));
        viewButton.setPreferredSize(new Dimension(275, 25));
        logoutButton.setPreferredSize(new Dimension(275, 25));

        i++;
        c.insets = new Insets(10, 0, 1, 0);
        c.gridy = i + 1; c.gridx = 0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridwidth = 2;
        data.add(addButton, c);
        c.insets = new Insets(1, 0, 1, 0);
        c.gridy = i + 2;
        data.add(updateButton, c);
        c.gridy = i + 3;
        data.add(deleteButton, c);
        c.gridy = i + 4;
        data.add(viewButton, c);
        c.gridy = i + 5;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        data.add(logoutButton, c);

        JScrollPane jsp = new JScrollPane(usersTable);
        jsp.getViewport().setBackground(new Color(39, 39, 39));
        jsp.getVerticalScrollBar().setBackground(new Color(143, 0, 0));
        jsp.getHorizontalScrollBar().setBackground(new Color(143, 0, 0));
        usersTable.setBackground(new Color(39, 39, 39));
        usersTable.setForeground(Color.WHITE);
        usersTable.setGridColor(new Color(143, 0, 0));
        usersTable.getTableHeader().setBackground(new Color(143, 0, 0));
        usersTable.getTableHeader().setForeground(Color.WHITE);
        usersTable.setAutoCreateRowSorter(true);
        addTableListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                int row = usersTable.getSelectedRow();
                for (int i = 0; i < usersTable.getColumnCount(); i++)
                {
                    String data = (String) usersTable.getValueAt(row, i);
                    dateUseri.get(i).setText(data);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
                        .addComponent(data, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addComponent(data, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );
    }

    public void addTableListener(MouseListener listener)
    {
        usersTable.addMouseListener(listener);
    }

    public void setComboBox(String tip)
    {
        cb.setSelectedItem(tip);
    }

    public void setLabels()
    {
        labels.get(6).setVisible(false);
        labels.get(7).setVisible(false);
        labels.get(8).setVisible(false);
        labels.get(9).setVisible(false);
        labels.get(10).setVisible(false);
        dateUseri.get(6).setVisible(false);
        dateUseri.get(7).setVisible(false);
        dateUseri.get(8).setVisible(false);
        dateUseri.get(9).setVisible(false);
        dateUseri.get(10).setVisible(false);
        if (cb.getSelectedItem().equals("Student"))
        {
            labels.get(6).setVisible(true);
            labels.get(7).setVisible(true);
            dateUseri.get(6).setVisible(true);
            dateUseri.get(7).setVisible(true);
        }
        else if (cb.getSelectedItem().equals("Admin"))
        {
            labels.get(8).setVisible(true);
            dateUseri.get(8).setVisible(true);
        }
        else if (cb.getSelectedItem().equals("Mester"))
        {
            labels.get(9).setVisible(true);
            dateUseri.get(9).setVisible(true);
        }
        else if (cb.getSelectedItem().equals("Bucatar"))
        {
            labels.get(10).setVisible(true);
            dateUseri.get(10).setVisible(true);
        }
    }

    public void setTable(JTable table, ArrayList< ArrayList<String> > list)
    {
        DefaultTableModel dtm = new DefaultTableModel()
        {
            @Override
            public String getColumnName(int index)
            {
                return labels.get(index).getText();
            }

            @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }

            @Override
            public int getColumnCount()
            {
                return labels.size() - 1;
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

    public JTable getUsersTable()
    {
        return usersTable;
    }

    public void setButtonListeners(ArrayList<ActionListener> listeners)
    {
        addButton.addActionListener(listeners.get(0));
        updateButton.addActionListener(listeners.get(1));
        deleteButton.addActionListener(listeners.get(2));
        viewButton.addActionListener(listeners.get(3));
    }

    public ArrayList<JTextField> getDateUseri()
    {
        return dateUseri;
    }

    public JComboBox<String> getCb()
    {
        return cb;
    }
}
