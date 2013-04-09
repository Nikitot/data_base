package additionalFunc;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 09.04.13
 * Time: 9:27
 * To change this template use File | Settings | File Templates.
 */
public class DbTest {
    private final static String DB_URL = "jdbc:firebirdsql://localhost:3050/C:/Users/Vasya/Git/data_base/DB.FDB";
    private final static String DB_DEFAULT_USER = "SYSDBA";
    private final static String DB_DEFAULT_PASSWORD = "masterkey";

    static public void main(String[] args){
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_DEFAULT_USER, DB_DEFAULT_PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PLANT");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println("next");
                PlantRecord plantRecord = new PlantRecord();
                plantRecord.setFieldsFromResultSet(resultSet);
                System.out.println(plantRecord);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        /*
        FBWrappingDataSource dataSource = new FBWrappingDataSource();
        dataSource.setDatabase(DB_URL);
        dataSource.setType("TYPE4");
        dataSource.setEncoding("UTF8");
        //try {
        //    Connection dbh = DriverManager.getConnection("jdbc:firebirdsql:embedded://" + DB_URL, DB_DEFAULT_USER, DB_DEFAULT_PASSWORD);
        //} catch (SQLException e) {
        //    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        //}

        try {
            dataSource.setLoginTimeout(10);
            Connection connection = dataSource.getConnection(DB_DEFAULT_USER, DB_DEFAULT_PASSWORD);
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery()

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        */
    }
}
