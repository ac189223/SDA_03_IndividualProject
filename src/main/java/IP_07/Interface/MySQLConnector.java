package IP_07.Interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents a connector to MySQL database
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class MySQLConnector {

    /* =================    =================    Methods    =================   ================= */

    /**
     * Establishing connection with MySQL database
     *
     * @return              working connection to the database or throwing SQLException if unable to connect
     */
    public Connection startConnection()
    {
        Connection conn;                                         // Setup connection properties
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost:3306/";
        final String DB_NAME = "ip";
        final String USER_NAME = "root";
        final String PASSWORD = "ac01AC=!";

        try {
        //    Class.forName(DRIVER).newInstance();                // Dina says it is not working anyway
        //    conn = DriverManager.getConnection(URL + DB_NAME + "?user=" + USER_NAME + "&password=" + PASSWORD);
            conn = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
            return conn;                                        // Return connection to work with
        } catch (Exception e) {
            System.out.println("NO CONNECTION =(");
        }
        return null;                                            // Return null if unable to connect
    }

    /**
     * Closing connection with database or throwing SQLException if unable to do it
     *
     * @param conn          connection, that will be closed
     */
    public void closeConnection(Connection conn) {
        try
        {
            conn.close();
        } catch (SQLException e) {
            System.out.println("CANNOT DISCONNECT =(");         // Inform if unable to disconnect
        }
    }
}