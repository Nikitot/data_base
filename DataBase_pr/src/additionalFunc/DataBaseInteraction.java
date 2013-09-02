package additionalFunc;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 09.04.13
 * Time: 9:27
 * To change this template use File | Settings | File Templates.
 */
public class DataBaseInteraction {
    private final static String DB_URL = "jdbc:firebirdsql://localhost:3050/" + new File("").getAbsolutePath() + "/DataBase_pr/DB.FDB";

    private final static String DB_DEFAULT_USER = "SYSDBA";
    private final static String DB_DEFAULT_PASSWORD = "masterkey";

    public static ResultSet getAllTable(String table) {
        try {
            System.out.println(DB_URL);
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_DEFAULT_USER, DB_DEFAULT_PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table);
            ResultSet resultSet = statement.executeQuery();
            //statement.close();
            //connection.close();
            return resultSet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    public static ResultSet getFromDb (String feilds, String from, String where) {
        if (feilds == null) {
            feilds = "*";
        }
        try {
            System.out.println(DB_URL);
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_DEFAULT_USER, DB_DEFAULT_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(String.format(
                                                "SELECT %s FROM %s WHERE %s", feilds, from, where));
            ResultSet resultSet = statement.executeQuery();
            //statement.close();
            //connection.close();
            return resultSet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    public static int loadTableRow(String table, ArrayList<String> column, ArrayList<String> values) {
        if (column.size() != values.size()){
            return -1;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(table);
        stringBuilder.append(" (");
        stringBuilder.append(column.get(0));
        for (int i=1; i<column.size(); i++){
            stringBuilder.append(", ");
            stringBuilder.append(column.get(i));
        }
        stringBuilder.append(") VALUES ('");
        stringBuilder.append(values.get(0));
        for (int i=1; i<column.size(); i++){
            stringBuilder.append("', '");
            stringBuilder.append(values.get(i));
        }
        stringBuilder.append("')");
        System.out.println(stringBuilder);

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_DEFAULT_USER, DB_DEFAULT_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
            statement.executeUpdate();
            //connection.commit();   //not allowed in auto-commit mode
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 1;
    }

    public static void updateTableRow(String table, ArrayList<String> columns, ArrayList<String> values, String conditions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(table);
        stringBuilder.append(" SET ");
        stringBuilder.append(columns.get(0));
        stringBuilder.append(" = '");
        stringBuilder.append(values.get(0));
        for (int i=1; i<columns.size(); i++){
            stringBuilder.append("', ");
            stringBuilder.append(columns.get(i));
            stringBuilder.append(" = '");
            stringBuilder.append(values.get(i));
        }
        stringBuilder.append("' WHERE ");
        stringBuilder.append(conditions);
        System.out.println(stringBuilder);

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_DEFAULT_USER, DB_DEFAULT_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
            statement.executeUpdate();
            //connection.commit();   //not allowed in auto-commit mode
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
