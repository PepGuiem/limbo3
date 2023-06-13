package es.cc.esliceu.db.limbo.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import es.cc.esliceu.db.limbo.util.Color;

public class ConnectionToBBDD {
    private static String url = "jdbc:mysql://localhost:3306/limbo";
    private static String username = "root";
    private static String password = "pepguiemA1";

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        ConnectionToBBDD.url = url;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ConnectionToBBDD.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConnectionToBBDD.password = password;
    }
}
