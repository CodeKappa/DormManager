package Presentation;

import BusinessLogic.SuperAdminBL;
import DataAccess.Connection.ConnectionFactory;
import Presentation.GUI.*;
import Presentation.GUI.AdminGUI.AdminGUI;
import Presentation.GUI.BucatarGUI.BucatarGUI;
import Presentation.GUI.MesterGUI.MesterGUI;
import Presentation.GUI.StudentGUI.StudentGUI;
import Presentation.GUI.SuperAdminGUI.SuperAdminGUI;
import Presentation.Listeners.*;

import java.sql.Connection;

/**
 * This class constructs the GUI and adds listeners for it.
 *
 * @Author: Kovacs Paul-Adrian
 * @Since: 1.0.0
 */
public class Controller
{
    private GUI theGUI = null;
    enum GUIType
    {
        LOGIN,
        SUPER_ADMIN,
        ADMINISTRATOR,
        STUDENT,
        BUCATAR,
        MESTER
    }

    /**
     * Constructs an instance of the LoginGUI.
     */
    public Controller()
    {
        theGUI = new LoginGUI();
        addLoginListener((LoginGUI) theGUI);
        theGUI.setVisible(true);
    }

    /**
     * Constructs an instance of the LoginGUI.
     */
    public void switchGUI(GUI theGUI, GUIType guiType, String name)
    {
        this.theGUI.dispose();
        this.theGUI = theGUI;

        switch (guiType)
        {
            case STUDENT ->  new StudentListeners(this, (StudentGUI) theGUI, name);
            case ADMINISTRATOR ->  new AdminListeners(this, (AdminGUI) theGUI, name);
            case MESTER ->  new MesterListeners(this, (MesterGUI) theGUI);
            case BUCATAR ->  new BucatarListerners(this, (BucatarGUI) theGUI);
            case SUPER_ADMIN ->  new SuperAdminListeners(this, (SuperAdminGUI) theGUI);
        }

        theGUI.setVisible(true);
    }

    private void addLoginListener(LoginGUI loginGui)
    {
        loginGui.addLoginListener(e ->
        {
            //details for the MySQL JDBC driver to develop the program
            String username = loginGui.getUsernameField().getText();
            String password = String.valueOf(loginGui.getPasswordField().getPassword());
            Connection connection = null;
            ConnectionFactory.setUsername(username);
            ConnectionFactory.setPassword(password);
            connection = ConnectionFactory.getConnection();
            if(connection == null)
            {
                theGUI.shake();
            }
            else
            {
                switch (SuperAdminBL.getUserType(username))
                {
                    case Student -> switchGUI(new StudentGUI(), GUIType.STUDENT, username);
                    case Admin -> switchGUI(new AdminGUI(), GUIType.ADMINISTRATOR, username);
                    case Mester -> switchGUI (new MesterGUI(), GUIType.MESTER, null);
                    case Bucatar -> switchGUI(new BucatarGUI(), GUIType.BUCATAR, null);
                    case SuperAdmin -> switchGUI(new SuperAdminGUI(), GUIType.SUPER_ADMIN, null);
                }
            }
            ConnectionFactory.close(connection);
        });
    }

    public void logout()
    {
        theGUI.dispose();
        theGUI = new LoginGUI();
        addLoginListener((LoginGUI) theGUI);
        theGUI.setVisible(true);
    }
}
