import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by alaincruzcasanova on 7/14/17.
 */

public class DataAccessObject {

    //Connection constant that will be use as the connection string.
    private final String URL = "jdbc:mysql://localhost/wiki";

    /*
    * IMPORTANT: Change this information, otherwise it won't work (or might won't).
    * The username and password must be set for the respective database.
    */
    //The username that we will use to connect to the MySQL database
    private final String USER = "root";
    //Password for the username that we will be using to establish the connection
    private final String PASSWORD = "oper";

    //Our connector to our MySql database
    private Connection connector;

    /*
    * Default constructor
    * Here we will initialize our connection to the MySQL database
    * We will be using the information that we set before
    */
    public DataAccessObject() {
        try {
            connector = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /*
    * Returns the information regarding the page which's id was passed down as parameter
    * We will returning this information as a String
    */
    public String getPage(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        String result = null;
        try {
            statement = connector.createStatement();
            if (statement.execute("SELECT * FROM page WHERE page_id=" + (id))) {
                resultSet = statement.getResultSet();
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            /*
             * it is a good idea to release
             * resources in a finally{} block
             * in reverse-order of their creation
             * if they are no-longer needed
             * This practice was taken from:
             * https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-statements.html
             */
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        result = "Id: "+id+" | ";
                        result += "Title: " + resultSet.getString("page_title");
                    }
                    resultSet.close();
                } catch (SQLException sqlEx) {} //ignore
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) {} // ignore
            }
        }
        return result;
    }

    /*
    * Returns the information regarding the page which's title was passed down as parameter
    * We will returning this information as a String
    */
    public String getPage(String title) {
        Statement statement = null;
        ResultSet resultSet = null;
        String result = null;
        try {
            statement = connector.createStatement();
            if (statement.execute("SELECT * FROM page WHERE page_title='"+title+"'")) {
                resultSet = statement.getResultSet();
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            /*
             * it is a good idea to release
             * resources in a finally{} block
             * in reverse-order of their creation
             * if they are no-longer needed
             * This practice was taken from:
             * https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-statements.html
             */
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        result = "Id: "+resultSet.getString("page_id")+" | ";
                        result += "Title: " + title;
                    }
                    //System.out.println(result);
                    resultSet.close();
                } catch (SQLException sqlEx) {} //ignore
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) {} // ignore
            }
        }
        return result;
    }
}
