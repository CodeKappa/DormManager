package DataAccess;

import DataAccess.Connection.ConnectionFactory;
import Presentation.GUI.GUI;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class AbstractDAO<T>
{
    private final Class<T> type;

    private final String selectAllQuery;
    private final String deleteQuery;

    @SuppressWarnings("unchecked")
    public AbstractDAO()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        selectAllQuery = "Select * from " + type.getSimpleName();
        deleteQuery = "Delete from " + type.getSimpleName() + " where cnp = ?";
    }

    /**
     * Creates a insert query based on a number of values it will have
     *
     * @param noValues      number of values to be inserted
     * @return              the insert query
     */
    private String createInsertQuery(int noValues)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Insert INTO ");
        sb.append(type.getSimpleName());
        sb.append(" Values ( ");
        for(int i = 1; i < noValues; i++)
        {
            sb.append("?, ");
        }
        sb.append("? )");

        return sb.toString();
    }

    /**
     * Insert a tuple into the database
     *
     * @param t     tuple to be inserted
     */
    public void insert(T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t.getClass().getDeclaredFields().length);
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for(Field field : t.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                Object value;
                try
                {
                    value = field.get(t);
                    statement.setObject(i++, value);
                }
                catch (SQLException | IllegalAccessException e)
                {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la inserare");
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates a list with all the tuples from current table
     *
     * @return      the list with all the tuples
     */
    public List<T> viewAll()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectAllQuery);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la afisare");
            return null;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates an update query based on the parameter received
     *
     * @param t     object that will be updated
     * @return      the update query
     */
    private String createUpdateQuery(T t)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Update ");
        sb.append(type.getSimpleName());
        sb.append(" set ");

        for(Field field : t.getClass().getDeclaredFields())
        {
            sb.append(field.getName() + " = ?,");
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append(" where cnp = ?");

        return sb.toString();
    }

    /**
     * Update the tuple received
     *
     * @param t     the tuple to be updated
     */
    public T update(T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int i = 1;
            for(Field field : t.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                Object value;
                try
                {
                    value = field.get(t);
                    statement.setObject(i++, value);
                }
                catch (SQLException | IllegalAccessException e)
                {
                    e.printStackTrace();
                    GUI.showErrorMessage("Eroare la actualizare");
                }
            }

            Field field= t.getClass().getDeclaredFields()[0];
            field.setAccessible(true);
            Object value = field.get(t);
            statement.setObject(i, value);

            statement.execute();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la update");
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Delete the tuple with the receved id
     *
     * @param cnp     the cnp of the tuple to be deleted
     */
    public void delete(String cnp)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(deleteQuery);

            statement.setString(1, cnp);

            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la delete");
        }
        finally
        {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private List<T> createObjects(ResultSet resultSet)
    {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try
        {
            while (resultSet.next())
            {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la creare obiecte");
            return null;
        }
        return list;
    }

    public boolean find(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //select 1, s.*, p.* from persoane p join studenti s on p.cnp = s.cnp where p.email = 'student'
        String query = "Select 1 from persoane p join " + type.getSimpleName() + " s on p.cnp = s.cnp where p.email = ?";
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            GUI.showErrorMessage("Eroare la cautare");
            return false;
        }
        finally
        {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
