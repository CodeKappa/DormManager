package Presentation.GUI.AdminGUI;

import java.util.ArrayList;

public class RoomData
{
    int id;
    int nrCamera;
    int nrLocuri;
    ArrayList<String> students;
    ArrayList<String> studentsCNP;
    String tipCamera;
    int cameraComuna;
    int etaj;

    RoomData(int id, int nrCamera, int nrLocuri, ArrayList<String> students, String tipCamera, int cameraComuna, ArrayList<String> studentsCNP)
    {
        this.id = id;
        this.nrCamera = nrCamera;
        this.nrLocuri = nrLocuri;
        this.students = students;
        this.tipCamera = tipCamera;
        this.cameraComuna = cameraComuna;
        this.etaj = nrCamera / 100;
        this.studentsCNP = studentsCNP;
    }
}
