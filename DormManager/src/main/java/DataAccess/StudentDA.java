package DataAccess;

import DataAccess.Connection.ConnectionFactory;
import DataAccess.DAO.ProgramSpalatorieDAO;
import Model.ProgramSpalatorie;
import Presentation.GUI.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDA
{
    ProgramSpalatorieDAO programSpalatorieDAO = new ProgramSpalatorieDAO();

    public int getRoom(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select camera from studenti s join persoane p on s.cnp = p.cnp where p.email = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            Integer result = null;

            if (resultSet.next())
            {
                result = resultSet.getInt("camera");
            }

            if(result == null)
            {
                return 0;
            }
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la preluare date");
            return 0;
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public ArrayList<ArrayList<String>> getSpalatorii(String camin)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query =  "select c.id, c.nr_camera from camera c where c.tip_camera = 'Spalatorie' and c.camin = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, camin);
            resultSet = statement.executeQuery();

            ArrayList<ArrayList<String>> result = new ArrayList<>();

            while (resultSet.next())
            {
                ArrayList<String> row = new ArrayList<>();
                row.add(resultSet.getString("id"));
                row.add(resultSet.getString("nr_camera"));
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

        String query = "select * from camera where tip_camera = 'Dormitor' and camin = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, camin);
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

    public String getCaminStudent(String cnp)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select camin from studenti s join camera c on s.camera = c.id where s.cnp = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, cnp);
            resultSet = statement.executeQuery();

            String result = null;

            if (resultSet.next())
            {
                result = resultSet.getString("camin");
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

        String query = "select cnp from persoane p where p.email = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
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

    public int getRoomName(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select c.nr_camera from studenti s " +
                    "join persoane p on s.cnp = p.cnp " +
                    "join camera c on s.camera = c.id " +
                    "where p.email = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            Integer result = null;

            if (resultSet.next())
            {
                result = resultSet.getInt("nr_camera");
            }

            if(result == null)
            {
                return 0;
            }
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la preluare date");
            return 0;
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public int getRoomName(int id)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select nr_camera from camera where id = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            Integer result = null;

            if (resultSet.next())
            {
                result = resultSet.getInt("nr_camera");
            }

            if(result == null)
            {
                return 0;
            }
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la preluare date");
            return 0;
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void addProgramare(ProgramSpalatorie programSpalatorie)
    {
        programSpalatorieDAO.insert(programSpalatorie);
    }

    public ArrayList<ArrayList<String>> getProgramari(int cameraSpalatorie)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select cameraStudenti, data_programare from programspalatorie where cameraSpalatorie = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, cameraSpalatorie);
            resultSet = statement.executeQuery();

            ArrayList<ArrayList<String>> result = new ArrayList<>();

            while (resultSet.next())
            {
                ArrayList<String> row = new ArrayList<>();
                row.add(resultSet.getString("cameraStudenti"));
                row.add(resultSet.getString("data_programare"));
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

    public void removeProgramare(int spalatorie, int camera, String data)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        String query = "delete from programspalatorie where cameraSpalatorie = ? and cameraStudenti = ? and data_programare = ?";

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, spalatorie);
            statement.setInt(2, camera);
            statement.setString(3, data);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la stergere programare");
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}