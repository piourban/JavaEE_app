package DbUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Piotr Urban on 27.01.2017.
 */
public class ConnectionProvider {

    private static DataSource dataSource;

    public static Connection geConnection() throws SQLException {

        return getDataSource().getConnection();
    }

    public static DataSource getDataSource() {

        if (dataSource == null) {

            try {
                Context initialContext = new InitialContext();
                Context envContext = (Context) initialContext.lookup("java:comp/env");

                DataSource ds = (DataSource) envContext.lookup("jdbc/java_ee");
                dataSource = ds;
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
}
