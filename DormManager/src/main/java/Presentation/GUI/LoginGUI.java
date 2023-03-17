package Presentation.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class constructs the GUI for logging in.
 *
 * @Author: Kovacs Paul-Adrian
 * @Since: 1.0.0
 */
public class LoginGUI extends GUI
{
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    //todo make private after testing
    public final JButton loginButton;

    private BufferedImage background;

    public LoginGUI()
    {
        setTitle("Login");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //load images
        ImageIcon userIcon = null;
        ImageIcon userIcon2 = null;
        ImageIcon passwordIcon = null;
        //ImageIcon passwordIcon = null;
        try
        {
            background = ImageIO.read(new File("src/main/resources/images/utcn.png"));
            userIcon = new ImageIcon("src/main/resources/images/UserIcon.png");
            userIcon2 = new ImageIcon("src/main/resources/images/BigUser.png");
            passwordIcon = new ImageIcon("src/main/resources/images/PasswordIcon.png");
            //userIcon = ImageIO.read(new File("src/main/resources/images/UserIcon.png"));
            //Image image = userIcon.getImage(); // transform it
            //Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            //userIcon = new ImageIcon(newimg);  // transform it back
            //passwordIcon = ImageIO.read(new File("src/main/resources/images/PasswordIcon.png"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        // Create a panel to hold the login form
        JPanel loginPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                //Graphics2D g2d = (Graphics2D) g;
                //GradientPaint gp = new GradientPaint(0, 0, Color.RED, 0, getHeight(), Color.WHITE, true);
                //g2d.setPaint(gp);
                //g2d.fillRect(0, 0, getWidth(), getHeight());
                //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                //g2d.fillRect(0, 0, getWidth(), getHeight());
                if (background != null)
                {
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        loginPanel.setLayout(new GridBagLayout());

        // Labels, fields and buttons
        JLabel usernameLabel = new JLabel("Username: ", userIcon, JLabel.CENTER);
        usernameLabel.setForeground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password: ", passwordIcon, JLabel.CENTER);
        passwordLabel.setForeground(Color.WHITE);
        usernameField = (JTextField) createField(15, false);
        passwordField = (JPasswordField) createField(15, true);

        passwordField.setText("3321");

        loginButton = createButton("LOGIN");

        // Set up the constraints for the login form
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //constraints.gridx = 0;
        //constraints.gridy = 0;
        //constraints.gridwidth = 2;
        //loginPanel.add(new JLabel("", userIcon2, JLabel.CENTER), constraints);

        constraints.gridwidth = 1;
        // Add the username label and field to the panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        loginPanel.add(usernameField, constraints);

        // Add the password label and field to the panel
        constraints.gridx = 0;
        constraints.gridy = 2;
        loginPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        loginPanel.add(passwordField, constraints);

        // Add the login button to the panel
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        loginPanel.add(loginButton, constraints);

        this.add(loginPanel, BorderLayout.CENTER);
    }

    public void addLoginListener(ActionListener listenForLoginButton)
    {
        loginButton.addActionListener(listenForLoginButton);
    }

    public JTextField getUsernameField()
    {
        return usernameField;
    }

    public JPasswordField getPasswordField()
    {
        return passwordField;
    }
}