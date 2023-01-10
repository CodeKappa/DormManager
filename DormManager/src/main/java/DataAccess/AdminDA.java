package DataAccess;

import DataAccess.Connection.ConnectionFactory;
import DataAccess.DAO.StudentDAO;
import Model.Studenti;
import Presentation.GUI.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDA
{
    StudentDAO studentDAO = new StudentDAO();

    public ArrayList<ArrayList<String>> getStudents()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select p.*, s.sex, s.camera from persoane p" +
                       " join studenti s on s.cnp = p.cnp";

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

    public ArrayList<ArrayList<String>> getRooms(String camin)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select * from camera where camin = '" + camin + "'";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            ArrayList<ArrayList<String>> result = new ArrayList<>();

            while (resultSet.next())
            {
                ArrayList<String> row = new ArrayList<>();
                row.add(resultSet.getString("id"));
                row.add(resultSet.getString("nr_camera"));
                row.add(resultSet.getString("nr_locuri"));
                row.add(resultSet.getString("tip_camera"));

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

    public String getCaminAdmin(String cnp)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select a.id_camin from admini a where a.cnp = " + cnp;

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            String result = null;

            if (resultSet.next())
            {
                result = resultSet.getString("id_camin");
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

    public String getCNP(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select cnp from persoane p where p.email = '" + email + "'";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            String result = null;

            if (resultSet.next())
            {
                result = resultSet.getString("cnp");
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

    public void assignRoom(String cnp, Integer id)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        String query = "update studenti set camera = " + id + " where cnp = '" + cnp + "'";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la atribuire camera");
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
