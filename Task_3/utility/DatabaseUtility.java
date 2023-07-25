package Task_3.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {

    private static final String databaseUrl = "jdbc:mysql://localhost:3306/codsoft";
    private static final String userName = "modderfox";
    private static final String password = "modderfox";
    private static final String driverPath = "com.mysql.cj.jdbc.Driver";

    public DatabaseUtility() {
        try {
            Class.forName(driverPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, userName, password);
    }

}
