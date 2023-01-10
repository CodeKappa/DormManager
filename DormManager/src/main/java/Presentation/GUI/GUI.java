package Presentation.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * The parent class of all GUIs. It contains the common methods for all GUIs.
 * @Author: Kovacs Paul-Adrian
 * @Since: 1.0.0
 */
public class GUI extends JFrame
{
    private static final Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE, 1);
    private static final Border noBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);

    public GUI()
    {
        this.setTitle("Dorm Manager");
        //this.setResizable(false);
        this.setBackground(new Color(39, 39, 39));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setUndecorated(true);
    }

    /**
     * This method creates a button with the given text with a specific style.
     * @param text The text of the button.
     * @return The created button.
     */
    public static JButton createButton(String text)
    {
        JButton button = new JButton(text)
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        button.setOpaque(false); // background of parent will be painted first

        button.setBackground(new Color(255, 255, 255, 100));
        button.setForeground(Color.WHITE);

        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        button.setBorder(noBorder);

        //hover effect
        button.getModel().addChangeListener(e -> {
            final ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover())
                button.setBorder(whiteBorder);
            else
                button.setBorder(noBorder);
        });

        button.setFont(new Font("Arial", Font.ITALIC, 13));

        return button;
    }

    /**
     * This method creates a field with a specific style.
     * @param columns The number of columns of the field.
     * @param isPasswordField If the value is 1, the field will be a password field else a text field.
     * @return The created field.
     */
    public static JTextComponent createField(int columns, boolean isPasswordField)
    {
        JTextField field = null;

        if (isPasswordField)
        {
            field = new JPasswordField(columns)
            {
                @Override
                protected void paintComponent(Graphics g)
                {
                    g.setColor( getBackground() );
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
        }
        else
        {
            field = new JTextField(columns)
            {
                @Override
                protected void paintComponent(Graphics g)
                {
                    g.setColor( getBackground() );
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
        }

        field.setOpaque(false); // background of parent will be painted first
        field.setBackground(new Color(255, 255, 255, 100));
        field.setBorder(noBorder);
        //textField.setForeground(Color.WHITE);

        field.setFont(new Font("Arial", Font.ITALIC, 13));

        JTextField finalField = field;
        field.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                finalField.setBorder(whiteBorder);
            }
            @Override
            public void focusLost(FocusEvent e)
            {
                finalField.setBorder(noBorder);
            }
        });


        return finalField;
    }

    public <T> void setTable(ArrayList<String> header, ArrayList<T> list, JTable table) throws IllegalAccessException, NoSuchFieldException
    {
        int tempNoColumns = 0;
        if(list.size() == 0)
        {
            table.setModel(new DefaultTableModel());
            repaint();
            return;
        }

        final int noColumns = tempNoColumns;
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public String getColumnName(int index)
            {
                return header.get(index);
            }

            @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }

            @Override
            public int getColumnCount()
            {
                return noColumns;
            }

            @Override
            public int getRowCount()
            {
                return list.size();
            }
        };

        int i = 0, j;
        for(Object row : list)
        {
            Object value;
            j = 0;
            for(Field field : row.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                value = field.get(row);
                System.out.println(i + ": " + value);
                //dtm.setValueAt(String.valueOf(value), i, j++);
            }
            i++;
        }

        table.setModel(dtm);
        repaint();
    }

    /**
     * This method makes the window shake.
     */
    public void shake()
    {
        Point currLocation;
        int xDisplacement = 5;
        int yDisplacement = -10;
        currLocation = this.getLocationOnScreen();

        Point position1 = new Point(currLocation.x + xDisplacement, currLocation.y + yDisplacement);
        Point position2 = new Point(currLocation.x - xDisplacement, currLocation.y - yDisplacement);
        for (int i = 0; i < 30; i++)
        {
            this.setLocation(position1);
            this.setLocation(position2);
        }
        this.setLocation(currLocation);
    }

    /**
     * This method displays an error message in a dialog box.
     * @param message The message to be displayed.
     */
    public static void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method displays a message in a dialog box.
     * @param message The message to be displayed.
     */
    public static void notificationMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
