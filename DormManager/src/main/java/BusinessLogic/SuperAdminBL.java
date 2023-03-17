package BusinessLogic;

import DataAccess.SuperAdminDA;
import Model.*;

import java.util.ArrayList;

public class SuperAdminBL
{
    private static final SuperAdminDA adminDA = new SuperAdminDA();

    public SuperAdminBL()
    {

    }

    public ArrayList<ArrayList<String>> getUserData()
    {
        return adminDA.getUserData();
    }

    public static UserType getUserType(String email)
    {
        return adminDA.getUserType(email);
    }

    public boolean addPersoana(ArrayList<String> data)
    {
        Persoane persoana = new Persoane(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5));
        if (adminDA.insert(persoana))
            return true;
        return false;
    }

    public void addStudent(ArrayList<String> data)
    {
        Integer camera = data.get(2) == null ? null : Integer.parseInt(data.get(2));
        Studenti studenti = new Studenti(data.get(0), data.get(1), camera);
        adminDA.insert(studenti);
    }

    public void addAdmin(ArrayList<String> data)
    {
        Admini admini = new Admini(data.get(0), data.get(1));
        adminDA.insert(admini);
    }

    public void addMester(ArrayList<String> data)
    {
        Mesteri mesteri = new Mesteri(data.get(0), data.get(1));
        adminDA.insert(mesteri);
    }

    public void addBucatar(ArrayList<String> data)
    {
        Bucatari bucatari = new Bucatari(data.get(0), data.get(1));
        adminDA.insert(bucatari);
    }

    public void addSuperAdmin(ArrayList<String> data)
    {
        SuperAdmini superAdmini = new SuperAdmini(data.get(0));
        adminDA.insert(superAdmini);
    }

    public void createUser(ArrayList<String> data)
    {
        adminDA.createUser(data.get(0), data.get(1));
    }

    public void updatePersoana(ArrayList<String> data)
    {
        Persoane persoana = new Persoane(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5));
        adminDA.update(persoana);
    }

    public void updateStudent(ArrayList<String> data)
    {
        Studenti studenti = new Studenti(data.get(0), data.get(1), Integer.parseInt(data.get(2)));
        adminDA.update(studenti);
    }

    public void updateAdmin(ArrayList<String> data)
    {
        Admini admini = new Admini(data.get(0), data.get(1));
        adminDA.update(admini);
    }

    public void updateMester(ArrayList<String> data)
    {
        Mesteri mesteri = new Mesteri(data.get(0), data.get(1));
        adminDA.update(mesteri);
    }

    public void updateBucatar(ArrayList<String> data)
    {
        Bucatari bucatari = new Bucatari(data.get(0), data.get(1));
        adminDA.update(bucatari);
    }

    public void updateSuperAdmin(ArrayList<String> data)
    {
        SuperAdmini superAdmini = new SuperAdmini(data.get(0));
        adminDA.update(superAdmini);
    }

    public void dropUser(String email)
    {
        adminDA.dropUser(email);
    }

    public void deletePersoana(String cnp)
    {
        adminDA.delete(cnp);
    }
}
