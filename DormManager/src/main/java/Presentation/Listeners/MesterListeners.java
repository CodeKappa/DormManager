package Presentation.Listeners;

import Presentation.Controller;
import Presentation.GUI.MesterGUI;

public class MesterListeners
{
    MesterGUI theGUI;
    Controller controller;

    public MesterListeners(Controller controller, MesterGUI theGUI)
    {
        this.controller = controller;
        this.theGUI = theGUI;
        //addLoginListener();
    }
}
