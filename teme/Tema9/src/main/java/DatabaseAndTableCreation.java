import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DatabaseAndTableCreation {
    public static void createTableAccommodation(Connection connection) throws SQLException
    {
        PreparedStatement createTable =
                connection.prepareStatement(
                        "create table accommodation " +
                                "(" +
                                "id int," +
                                "type varchar(32), " +
                                "bed_type varchar(32), " +
                                "max_guests int, " +
                                "description varchar(512), " +
                                "primary key(id))");

        createTable.execute();
        connection.commit();
    }

    public static void createTableRoomFair(Connection connection) throws SQLException
    {
        PreparedStatement createTable =
                connection.prepareStatement(
                        "create table room_fair " +
                                "(" +
                                "id int," +
                                "value double precision, " +
                                "season varchar(32), " +
                                "primary key(id))");

        createTable.execute();
        connection.commit();
    }

    public static void createTableAccommodationToRoomFairRelation(Connection connection) throws SQLException
    {
        PreparedStatement createTable =
                connection.prepareStatement(
                        "create table accommodation_to_room_fair_relation " +
                                "(" +
                                "id int," +
                                "id_accommodation int, " +
                                "id_room_fair int, " +
                                "foreign key(id_accommodation) references accommodation(id), " +
                                "foreign key(id_room_fair) references room_fair(id), " +
                                "primary key(id))");

        createTable.execute();
        connection.commit();
    }

    /*
        To be able to create this database from within java I had to turn auto-commit to true and then it worked
        I also didn't connect to a specific db, I just connected to postgres without specifying a db
        So no dbName in the query
     */
    public static void createDatabase(String dbName, Connection connection) throws SQLException {
        PreparedStatement createDatabase =
                connection.prepareStatement("create database " + dbName);
        createDatabase.execute();
    }
}
