package Presentation.Listeners;

import BusinessLogic.SuperAdminBL;
import Model.UserType;
import Presentation.Controller;
import Presentation.GUI.GUI;
import Presentation.GUI.SuperAdminGUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SuperAdminListeners
{
    SuperAdminGUI theGUI;
    SuperAdminBL superAdminBL;
    Controller controller;
    public SuperAdminListeners(Controller controller, SuperAdminGUI theGUI)
    {
        this.theGUI = theGUI;
        this.controller = controller;
        superAdminBL = new SuperAdminBL();
        addCRUDuserListeners();
    }

    void addCRUDuserListeners()
    {
        //Initialize the table with the data from the database
        ArrayList<ArrayList<String>> users2 = superAdminBL.getUserData();
        theGUI.refreshTable(users2);

        theGUI.addTabChangeListener(e ->
        {
            if (theGUI.getSelectedTab() == 0)
            {
                ArrayList<ArrayList<String>> users = superAdminBL.getUserData();
                theGUI.refreshTable(users);
            }
        });

        theGUI.addTableListerner(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                JTable table = theGUI.getUsersTable();
                int row = table.getSelectedRow();
                int col = 5;
                String value = (String) table.getValueAt(row, col);
                UserType type = SuperAdminBL.getUserType(value);
                switch (type)
                {
                    case Admin -> theGUI.setComboBox("Admin");
                    case Student -> theGUI.setComboBox("Student");
                    case Mester -> theGUI.setComboBox("Mester");
                    case Bucatar -> theGUI.setComboBox("Bucatar");
                    case SuperAdmin -> theGUI.setComboBox("Super Admin");
                }
            }
        });

        theGUI.addLogoutListener(e ->
        {
            controller.logout();
        });

        ArrayList<ActionListener> listeners = new ArrayList<>();

        ActionListener addListener = e ->
        {
            ArrayList<String> userData = theGUI.getUserData();
            for(int i = 0; i <= 5; i++)
            {
                if(userData.get(i).equals(""))
                {
                    GUI.showErrorMessage("Toate campurile trebuie completate!");
                    return;
                }
            }
            if (userData.get(12).equals("Student") && (userData.get(6).equals("") || userData.get(7).equals("")))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }
            if(userData.get(12).equals("Admin") && userData.get(8).equals(""))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }
            if(userData.get(12).equals("Mester") && userData.get(9).equals(""))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }
            if(userData.get(12).equals("Bucatar") && userData.get(10).equals(""))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }
            if(userData.get(11).equals(""))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }

            superAdminBL.addPersoana(new ArrayList<String> (userData.subList(0, 6)));
            if (userData.get(12).equals("Student"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));//cnp
                data.add(userData.get(6));//sex
                data.add(userData.get(7));//camera
                superAdminBL.addStudent(data);
            }
            if (userData.get(12).equals("Admin"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(8));
                superAdminBL.addAdmin(data);
            }
            if (userData.get(12).equals("Mester"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(9));
                superAdminBL.addMester(data);
            }
            if (userData.get(12).equals("Bucatar"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(10));
                superAdminBL.addBucatar(data);
            }
            if (userData.get(12).equals("Super Admin"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(10));
                superAdminBL.addSuperAdmin(data);
            }
            if (!userData.get(11).equals(""))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(5));
                data.add(userData.get(11));
                superAdminBL.createUser(data);
            }

            ArrayList<ArrayList<String>> users = superAdminBL.getUserData();
            theGUI.refreshTable(users);
        };

        ActionListener updateListener = e ->
        {
            ArrayList<String> userData = theGUI.getUserData();
            for(int i = 0; i <= 5; i++)
            {
                if(userData.get(i).equals(""))
                {
                    GUI.showErrorMessage("Toate campurile trebuie completate!");
                    return;
                }
            }
            if (userData.get(12).equals("Student") && (userData.get(6).equals("") || userData.get(7).equals("")))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }
            if(userData.get(12).equals("Admin") && userData.get(8).equals(""))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }
            if(userData.get(12).equals("Mester") && userData.get(9).equals(""))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }
            if(userData.get(12).equals("Bucatar") && userData.get(10).equals(""))
            {
                GUI.showErrorMessage("Toate campurile trebuie completate!");
                return;
            }

            superAdminBL.updatePersoana(new ArrayList<String> (userData.subList(0, 6)));
            if (userData.get(12).equals("Student"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(6));
                data.add(userData.get(7));
                superAdminBL.updateStudent(data);
            }
            if (userData.get(12).equals("Admin"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(8));
                superAdminBL.updateAdmin(data);
            }
            if (userData.get(12).equals("Mester"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(9));
                superAdminBL.updateMester(data);
            }
            if (userData.get(12).equals("Bucatar"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(10));
                superAdminBL.updateBucatar(data);
            }
            if (userData.get(12).equals("Super Admin"))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(0));
                data.add(userData.get(10));
                superAdminBL.updateSuperAdmin(data);
            }

            if (!userData.get(11).equals(""))
            {
                ArrayList<String> data = new ArrayList<>();
                data.add(userData.get(5));
                data.add(userData.get(11));
                superAdminBL.createUser(data);
            }

            ArrayList<ArrayList<String>> users = superAdminBL.getUserData();
            theGUI.refreshTable(users);
        };

        ActionListener viewListener = e ->
        {
            ArrayList<ArrayList<String>> data = superAdminBL.getUserData();
            theGUI.refreshTable(data);
        };

        ActionListener deleteListener = e ->
        {
            ArrayList<String> userData = theGUI.getUserData();
            if(userData.get(0).equals("") || userData.get(5).equals(""))
            {
                GUI.showErrorMessage("Campurile de email si cnp trebuie completate!");
                return;
            }
            superAdminBL.deletePersoana(userData.get(0));
            superAdminBL.dropUser(userData.get(5));

            ArrayList<ArrayList<String>> users = superAdminBL.getUserData();
            theGUI.refreshTable(users);
        };

        listeners.add(addListener);
        listeners.add(updateListener);
        listeners.add(deleteListener);
        listeners.add(viewListener);

        theGUI.setButtonListeners(listeners);
    }
}
