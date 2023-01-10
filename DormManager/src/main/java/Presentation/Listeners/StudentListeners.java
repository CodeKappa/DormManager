package Presentation.Listeners;

import Presentation.Controller;
import Presentation.GUI.StudentGUI;

public class StudentListeners
{
    StudentGUI theGUI;
    Controller controller;

    public StudentListeners(Controller controller, StudentGUI theGUI)
    {
        this.controller = controller;
        this.theGUI = theGUI;
        //addLoginListener();
    }
}
