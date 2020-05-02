import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectToDb {
    private Connection connection;

    public ConnectToDb(String type, String host, String port,
                       String dbName, String user,
                       String password){
        connection = newConnection(type, host, port, dbName, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection newConnection(
            String type, String host, String port,
            String dbName, String user,
            String password) {

        String url = "jdbc:" + type + "://" + host + ":" + port + "/" + dbName +
                "?user=" + user + "&password=" + password;
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
