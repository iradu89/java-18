import java.sql.Connection;
import java.sql.SQLException;

/**
 * 4) On your local system: define a database ("booking"), create the following tables:
 *
 * "accommodation" with the following columns:
 *
 *     -- id: int (primary key)
 *     -- type: varchar (32)
 *     -- bed_type: varchar (32)
 *     -- max_guests: int
 *     -- description: varchar (512)
 *
 * "room_fair" with the following columns:
 *     -- id: int (primary key)
 *     -- value: double
 *     -- season: varchar (32)
 *
 * "accommodation_to_room_fair_relation" with the following columns:
 *     -- id: int (primary key)
 *     -- id_accommodation: int (foreign key of accommodation)
 *     -- id_room_fair: int (foreign key of room fair)
 *
 * -> write a unit test which inserts data in the tables using the prepared statement, taking into account the accommodation_to_room_fair_relation data
 * -> write a unit test which interrogates the database and print using the LOGGER the price for each room in the database
 */
public class Main {
    public static void main(String[] args) {
        queryDb(newConnection());
    }

    private static Connection newConnection(){
        ConnectToDb connection = new ConnectToDb(
                "postgresql",
                "localhost",
                "5432",
                "booking",
                "postgres",
                "");
        return connection.getConnection();
    }

    private static void queryDb(Connection connection){
        try (connection) {
            assert connection != null;
            connection.setAutoCommit(false);

            //this was done first connecting just to the postgres db without specifying a dbName
            DatabaseAndTableCreation.createDatabase("booking", connection);

            //after that I specified the dbName in the connection url
            DatabaseAndTableCreation.createTableAccommodation(connection);
            DatabaseAndTableCreation.createTableRoomFair(connection);
            DatabaseAndTableCreation.createTableAccommodationToRoomFairRelation(connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
