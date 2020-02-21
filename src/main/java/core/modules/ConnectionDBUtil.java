package core.modules;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDBUtil {

    private DataSource dataSource;

    private static ConnectionDBUtil instance = new ConnectionDBUtil();

    private ConnectionDBUtil() {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("JNDI_LOOKUP_NAME");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionDBUtil getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    public void close(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = null;
    }

}