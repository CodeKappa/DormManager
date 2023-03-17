package Presentation.Listeners;

import BusinessLogic.StudentBL;
import Presentation.Controller;
import Presentation.GUI.GUI;
import Presentation.GUI.StudentGUI.StudentGUI;

import javax.swing.event.ListSelectionEvent;
import java.time.LocalDate;

public class StudentListeners
{
    StudentGUI theGUI;
    Controller controller;
    StudentBL studentBL;

    LocalDate currentDate;
    int camera;
    int spalatorie;
    String email;

    public StudentListeners(Controller controller, StudentGUI theGUI, String email)
    {
        this.controller = controller;
        this.theGUI = theGUI;
        this.studentBL = new StudentBL();
        this.email = email;

        currentDate = LocalDate.now();
        camera = studentBL.getRoom(email);
        spalatorie = studentBL.getSpalatorie(email);

        addCalendarListeners();
    }

    void addCalendarListeners()
    {
        theGUI.refresh(studentBL.getProgramari(currentDate, spalatorie, camera, email));

        theGUI.addLogoutListener(e ->
        {
            controller.logout();
        });

        theGUI.setRefreshListener(e ->
        {
            currentDate = LocalDate.now();
            theGUI.refresh(studentBL.getProgramari(currentDate, spalatorie, camera, email));
        });

        theGUI.addChangeWeekListener((ListSelectionEvent e) ->
        {
            if(theGUI.getList().getSelectedIndex() == 0)
            {
                currentDate = LocalDate.now();
                theGUI.refresh(studentBL.getProgramari(currentDate, spalatorie, camera, email));
            }
            else
            {
                currentDate = LocalDate.now().plusWeeks(1);
                theGUI.refresh(studentBL.getProgramari(currentDate, spalatorie, camera, email));
            }
        });

        theGUI.setProgramareListener(new EventSpalatorie()
        {
            @Override
            public void selected(int zi, int ora)
            {
                if(camera == 0)
                {
                    GUI.showErrorMessage("Nu aveti camera asignata!");
                    return;
                }
                if(spalatorie == 0)
                {
                    GUI.showErrorMessage("Nu exista spalatorie!");
                    return;
                }
                studentBL.addProgramare(currentDate, zi, ora, camera, spalatorie);
                theGUI.refresh(studentBL.getProgramari(currentDate, spalatorie, camera, email));
            }

            @Override
            public void removeProgramare(int zi, int ora)
            {
                studentBL.removeProgramare(currentDate, zi, ora, camera, spalatorie);
                theGUI.refresh(studentBL.getProgramari(currentDate, spalatorie, camera, email));
            }
        });
    }
}
