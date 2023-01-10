package BusinessLogic;

import DataAccess.AdminDA;

import java.util.ArrayList;

public class AdminBL
{
    private AdminDA adminDA;

    public AdminBL()
    {
        adminDA = new AdminDA();
    }

    public ArrayList<ArrayList<String>> getStudents()
    {
        return adminDA.getStudents();
    }

    public String getCamin(String cnp)
    {
        return adminDA.getCaminAdmin(cnp);
    }

    public String getCNP(String email)
    {
        return adminDA.getCNP(email);
    }

    public ArrayList<ArrayList<String>> getCamere(String camin)
    {
        return adminDA.getRooms(camin);
    }

    public void assignRoom(String cnp, Integer index)
    {
        adminDA.assignRoom(cnp, index);
    }
}
