package Presentation.Listeners;

import Presentation.Controller;
import Presentation.GUI.BucatarGUI;

public class BucatarListerners
{
    BucatarGUI theGUI;
    Controller controller;

    public BucatarListerners(Controller controller, BucatarGUI theGUI)
    {
        this.controller = controller;
        this.theGUI = theGUI;
        //addLoginListener();
    }
}
