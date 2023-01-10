package Presentation.Listeners;

import BusinessLogic.AdminBL;
import Presentation.Controller;
import Presentation.GUI.AdminGUI;
import Presentation.GUI.GUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminListeners
{
    AdminGUI theGUI;
    AdminBL adminBL;
    Controller controller;

    String camin = null;
    String cnp;

    public AdminListeners(Controller controller, AdminGUI theGUI, String email)
    {
        this.theGUI = theGUI;
        this.controller = controller;
        adminBL = new AdminBL();

        camin = adminBL.getCamin(adminBL.getCNP(email));

        addCazareListeners();
    }

    void addCazareListeners()
    {
        ArrayList<ArrayList<String>> initStudents = adminBL.getStudents();
        ArrayList<ArrayList<String>> initCamere = adminBL.getCamere(camin);
        theGUI.refresh(initStudents, initCamere, 0);
        theGUI.getList().setSelectedIndex(0);

        theGUI.addTableListerner(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                JTable table = theGUI.getUsersTable();
                int selectedRow = table.getSelectedRow();
                int col = 0;
                cnp = (String) table.getValueAt(selectedRow, col);
            }
        });

        theGUI.addLogoutListener(e ->
        {
            controller.logout();
        });

        theGUI.setRefreshListener(e ->
        {
            ArrayList<ArrayList<String>> students = adminBL.getStudents();
            ArrayList<ArrayList<String>> camere = adminBL.getCamere(camin);
            theGUI.refresh(students, camere, theGUI.getList().getSelectedIndex());
        });

        theGUI.addFloorListListener(e ->
        {
            ArrayList<ArrayList<String>> students = adminBL.getStudents();
            ArrayList<ArrayList<String>> camere = adminBL.getCamere(camin);
            theGUI.refresh(students, camere, theGUI.getList().getSelectedIndex());
        });

        theGUI.setRoomListener(new EventItemSelected()
        {
            @Override
            public void selected(int index)
            {
                adminBL.assignRoom(cnp, index);
                ArrayList<ArrayList<String>> students = adminBL.getStudents();
                ArrayList<ArrayList<String>> camere = adminBL.getCamere(camin);
                theGUI.refresh(students, camere, theGUI.getList().getSelectedIndex());
                //GUI.showErrorMessage("Studentul a fost introdus in camera");
            }

            @Override
            public void deleting(int index, ArrayList<String> nume, ArrayList<String> cnp)
            {
                int x = 1;
                ArrayList<String> aux = new ArrayList<>();
                for(int i=0; i<nume.size(); i++)
                {
                    aux.add(cnp.get(i) + " " + nume.get(i));
                }
                if(aux.size() == 0) return;
                Object[] possibilities = aux.toArray();
                String s = (String)JOptionPane.showInputDialog(
                        theGUI,
                        "Selecteaza stundetul care sa fie scos din camera:\n",
                        "Scoate student din camera",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]);

                //If a string was returned, say so.
                if ((s != null) && (s.length() > 0))
                {
                    String[] parts = s.split(" ");
                    adminBL.assignRoom(parts[0], null);
                    ArrayList<ArrayList<String>> students = adminBL.getStudents();
                    ArrayList<ArrayList<String>> camere = adminBL.getCamere(camin);
                    theGUI.refresh(students, camere, theGUI.getList().getSelectedIndex());
                }

                //If you're here, the return value was null/empty.
                //setLabel("Come on, finish the sentence!");


                //JOptionPane.showMessageDialog(null, "Nu se poate sterge un student dintr-o camera");
               // adminBL.deleteStudent(index, nume, cnp);
               // ArrayList<ArrayList<String>> students = adminBL.getStudents();
               // ArrayList<ArrayList<String>> camere = adminBL.getCamere(camin);
               // theGUI.refresh(students, camere, theGUI.getList().getSelectedIndex());
            }
        });
    }
}
