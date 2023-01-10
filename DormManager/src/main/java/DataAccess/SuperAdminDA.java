package DataAccess;

import DataAccess.Connection.ConnectionFactory;
import DataAccess.DAO.*;
import Model.*;
import Presentation.GUI.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all the methods that the super admin interacts with the database
 * @Author: Kovacs Paul-Adrian
 * @Since: May 02, 2022
 */
public class SuperAdminDA
{
    private final PersonaneDAO personaneDAO = new PersonaneDAO();
    private final SuperAdminDAO superAdminDAO = new SuperAdminDAO();
    private final AdminiDAO adminiDAO = new AdminiDAO();
    private final MesterDAO mesteriDAO = new MesterDAO();
    private final BucatarDAO bucatariDAO = new BucatarDAO();
    private final StudentDAO studentiDAO = new StudentDAO();

    private final ComplexDAO complexDAO = new ComplexDAO();
    private final CaminDAO caminDAO = new CaminDAO();
    private final CameraDAO cameraDAO = new CameraDAO();
    private final ConexiuneCameraDAO conexiuneCameraDAO = new ConexiuneCameraDAO();
    private final TerenDAO terenDAO = new TerenDAO();
    private final CantinaDAO cantinaDAO = new CantinaDAO();

    public void insert(ComplexStudentesc complex)
    {
        complexDAO.insert(complex);
    }
    public void insert(Camin camin)
    {
        caminDAO.insert(camin);
    }
    public void insert(Camera camera)
    {
        cameraDAO.insert(camera);
    }

    public void insert(Persoane persoana)
    {
        personaneDAO.insert(persoana);
    }
    public void insert(Studenti studenti)
    {
        studentiDAO.insert(studenti);
    }
    public void insert(Mesteri mesteri)
    {
        mesteriDAO.insert(mesteri);
    }
    public void insert(Bucatari bucatari)
    {
        bucatariDAO.insert(bucatari);
    }
    public void insert(Admini admini)
    {
        adminiDAO.insert(admini);
    }
    public void insert(SuperAdmini superAdmini)
    {
        superAdminDAO.insert(superAdmini);
    }

    public void update(Persoane persoana)
    {
        personaneDAO.update(persoana);
    }
    public void update(Studenti studenti)
    {
        studentiDAO.update(studenti);
    }
    public void update(Mesteri mesteri)
    {
        mesteriDAO.update(mesteri);
    }
    public void update(Bucatari bucatari)
    {
        bucatariDAO.update(bucatari);
    }
    public void update(Admini admini)
    {
        adminiDAO.update(admini);
    }
    public void update(SuperAdmini superAdmini)
    {
        superAdminDAO.update(superAdmini);
    }

    public void delete(String cnp)
    {
        personaneDAO.delete(cnp);
    }

    public ArrayList<ArrayList<String>> getUserData()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select p.*, s.sex, s.camera, a.id_camin, m.complex, b.cantina from persoane p" +
                       " left join studenti s on s.cnp = p.cnp" +
                       " left join admini a on a.cnp = p.cnp" +
                       " left join mesteri m on m.cnp = p.cnp" +
                       " left join bucatari b on b.cnp = p.cnp";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            ArrayList<ArrayList<String>> result = new ArrayList<>();

            while (resultSet.next())
            {
                ArrayList<String> row = new ArrayList<>();
                row.add(resultSet.getString("cnp"));
                row.add(resultSet.getString("nume"));
                row.add(resultSet.getString("prenume"));
                row.add(resultSet.getString("adresa"));
                row.add(resultSet.getString("nr_telefon"));
                row.add(resultSet.getString("email"));
                row.add(resultSet.getString("sex"));
                row.add(resultSet.getString("camera"));
                row.add(resultSet.getString("id_camin"));
                row.add(resultSet.getString("complex"));
                row.add(resultSet.getString("cantina"));

                result.add(row);
            }
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la preluare date");
            return null;
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public UserType getUserType(String email)
    {
        if (superAdminDAO.find(email))
            return UserType.SuperAdmin;
        if (adminiDAO.find(email))
            return UserType.Admin;
        if (mesteriDAO.find(email))
            return UserType.Mester;
        if (bucatariDAO.find(email))
            return UserType.Bucatar;
        if (studentiDAO.find(email))
            return UserType.Student;
        return UserType.Invalid;
    }

    public void createUser(String name, String password)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        String user = name + "@" + ConnectionFactory.getUrl();
        ArrayList<String> query = new ArrayList<>();
        query.add( "DROP USER IF EXISTS " + user);
        query.add( "CREATE USER " + user + " IDENTIFIED BY '" + password + "'" );
        query.add( "GRANT ALL PRIVILEGES ON *.* TO " + user + " WITH GRANT OPTION" );

        try
        {
            connection = ConnectionFactory.getConnection();
            for (String q : query)
            {
                statement = connection.prepareStatement(q);
                statement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la creare utilizator");
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void dropUser(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        String user = email + "@" + ConnectionFactory.getUrl();
        String query = "DROP USER IF EXISTS " + user;

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la stergere utilizator");
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
