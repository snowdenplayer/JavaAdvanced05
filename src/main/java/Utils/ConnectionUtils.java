package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    public static Connection connect() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String user = "root";
        String password = "drakoshenok2001";
        String url = "jdbc:mysql://localhost:3306/iszop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println("succes");

        return connection;
    }
}
