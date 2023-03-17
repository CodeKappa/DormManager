package Presentation.Listeners;

import java.util.ArrayList;

public interface EventItemSelected
{
    public void selected(int index, String camera);
    public void removeStudentFromRoom(int index, ArrayList<String> nume, ArrayList<String> cnp);
}
