package Service;

import Exceptions.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ilmaz on 11.10.16.
 */
public class SQLDatabase {
    //ToDo: replace information
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String CONNECTION_URI = "jdbc:mysql://127.0.0.1:3306/messanger";
    private final static String LOGIN = "messangeruser";
    private final static String PASSWORD = "messangerpassword";

    private static Connection conn;

    public static Connection getConnection() throws DBException {
        if(conn == null){
            try{
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(CONNECTION_URI, LOGIN, PASSWORD);
            }
            catch(ClassNotFoundException ex){
                throw new DBException("Can't find DB driver.");
            } catch (SQLException ex) {
                throw new DBException("Can't connect to DB (" + ex.getErrorCode() + ": " + ex.getMessage() + ").");
            }

        }
        return conn;
    }
}
