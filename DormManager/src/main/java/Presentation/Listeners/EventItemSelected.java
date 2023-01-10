package Presentation.Listeners;

import java.util.ArrayList;

public interface EventItemSelected
{
    public void selected(int index);
    public void deleting(int index, ArrayList<String> nume, ArrayList<String> cnp);
}
