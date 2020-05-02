import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test_models.AccommodationTest;
import test_models.AccommodationToRoomFairRelationTest;
import test_models.RoomFairTest;
import test_models.RoomIDAndPriceTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    All tests pass and can be run any number of times
 */
@Log4j2
class DbTest {
    private Connection connection;

    @BeforeEach
    void setup() {
        connection = connectToBookingDb();
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Since I have Lombok, might as well use SneakyThrows :) It's for closing the connection
    @SneakyThrows
    @Test
    void insert_into_booking_db_test_without_commit() {
        try {
            //Insert in accommodation table & test
            PreparedStatement insertAccommodationTable =
                    connection.prepareStatement("insert into accommodation" +
                            "(id, type, bed_type, max_guests, description) " +
                            "values (7837, 'quadRoom', 'single', 4, 'A room with 4 single beds') ");
            insertAccommodationTable.executeUpdate();
            PreparedStatement selectAccommodation = connection.prepareStatement("select * from accommodation where id = 7837");
            ResultSet accommodationResult = selectAccommodation.executeQuery();
            while (accommodationResult.next()) {
                assertEquals(7837, accommodationResult.getInt("id"));
                assertEquals("quadRoom", accommodationResult.getString("type"));
                assertEquals("single", accommodationResult.getString("bed_type"));
                assertEquals(4, accommodationResult.getInt("max_guests"));
                assertEquals("A room with 4 single beds", accommodationResult.getString("description"));
            }

            //Insert in room_fair table & test
            PreparedStatement insertRoomFairTable =
                    connection.prepareStatement("insert into room_fair" +
                            "(id, value, season) " +
                            "values (5, 130, 'spring') ");
            insertRoomFairTable.executeUpdate();
            PreparedStatement selectRoomFair = connection.prepareStatement("select * from room_fair where id = 5");
            ResultSet roomFairResult = selectRoomFair.executeQuery();
            while (roomFairResult.next()) {
                assertEquals(5, roomFairResult.getInt("id"));
                assertEquals(130, roomFairResult.getDouble("value"));
                assertEquals("spring", roomFairResult.getString("season"));
            }

            //Insert in accommodation_to_room_fair_relation table & test
            PreparedStatement insertAccommodationToRoomFairRelation =
                    connection.prepareStatement("insert into accommodation_to_room_fair_relation" +
                            "(id, id_accommodation, id_room_fair) " +
                            "values (5, 7837, 5) ");
            insertAccommodationToRoomFairRelation.executeUpdate();
            PreparedStatement selectAccommodationToRoomFairRelation =
                    connection.prepareStatement("select * from accommodation_to_room_fair_relation where id = 5");
            ResultSet accommodationToRoomFairRelation = selectAccommodationToRoomFairRelation.executeQuery();
            while (accommodationToRoomFairRelation.next()) {
                assertEquals(5, accommodationToRoomFairRelation.getInt("id"));
                assertEquals(7837, accommodationToRoomFairRelation.getInt("id_accommodation"));
                assertEquals(5, accommodationToRoomFairRelation.getInt("id_room_fair"));
            }

            /*
                Following code is a bonus to show the value can be updated and it will not persist in the database
                as long as I don't commit the statements. The bellow row exists in my db.
             */
            PreparedStatement updateQuery = connection.prepareStatement("update accommodation set type = 'testType2' where id = 6363");
            updateQuery.executeUpdate();
            PreparedStatement selectUpdate = connection.prepareStatement("select * from accommodation where id = 6363");
            ResultSet resultUpdate = selectUpdate.executeQuery();
            while (resultUpdate.next()) {
                assertEquals(6363, resultUpdate.getInt("id"));
                assertEquals("testType2", resultUpdate.getString("type"));
                assertEquals("single", resultUpdate.getString("bed_type"));
                assertEquals(1, resultUpdate.getInt("max_guests"));
                assertEquals("A nice cozy single room", resultUpdate.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @SneakyThrows
    @Test
    void insert_into_booking_db_test_with_commit() {
        try {
            //Insert in accommodation table & test
            PreparedStatement insertAccommodationTable =
                    connection.prepareStatement("insert into accommodation" +
                            "(id, type, bed_type, max_guests, description) " +
                            "values (5, 'quadRoom', 'single', 4, 'A room with 4 single beds') ");
            insertAccommodationTable.executeUpdate();
            PreparedStatement selectAccommodation = connection.prepareStatement("select * from accommodation where id = 5");
            ResultSet accommodationResult = selectAccommodation.executeQuery();
            while (accommodationResult.next()) {
                assertEquals(5, accommodationResult.getInt("id"));
                assertEquals("quadRoom", accommodationResult.getString("type"));
                assertEquals("single", accommodationResult.getString("bed_type"));
                assertEquals(4, accommodationResult.getInt("max_guests"));
                assertEquals("A room with 4 single beds", accommodationResult.getString("description"));
            }

            //Insert in room_fair table & test
            PreparedStatement insertRoomFairTable =
                    connection.prepareStatement("insert into room_fair" +
                            "(id, value, season) " +
                            "values (5, 130, 'spring') ");
            insertRoomFairTable.executeUpdate();
            PreparedStatement selectRoomFair = connection.prepareStatement("select * from room_fair where id = 5");
            ResultSet roomFairResult = selectRoomFair.executeQuery();
            while (roomFairResult.next()) {
                assertEquals(5, roomFairResult.getInt("id"));
                assertEquals(130, roomFairResult.getDouble("value"));
                assertEquals("spring", roomFairResult.getString("season"));
            }

            //Insert in accommodation_to_room_fair_relation table & test
            PreparedStatement insertAccommodationToRoomFairRelation =
                    connection.prepareStatement("insert into accommodation_to_room_fair_relation" +
                            "(id, id_accommodation, id_room_fair) " +
                            "values (5, 5, 5) ");
            insertAccommodationToRoomFairRelation.executeUpdate();
            PreparedStatement selectAccommodationToRoomFairRelation =
                    connection.prepareStatement("select * from accommodation_to_room_fair_relation where id = 5");
            ResultSet accommodationToRoomFairRelation = selectAccommodationToRoomFairRelation.executeQuery();
            while (accommodationToRoomFairRelation.next()) {
                assertEquals(5, accommodationToRoomFairRelation.getInt("id"));
                assertEquals(5, accommodationToRoomFairRelation.getInt("id_accommodation"));
                assertEquals(5, accommodationToRoomFairRelation.getInt("id_room_fair"));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Rollback delete all entries that were inserted above
            deleteFromTable(connection, 5);
            connection.commit();
            connection.close();
        }
    }

    @SneakyThrows
    @Test
    void interrogate_db_test_only_one_row() {
        try {
            //Inserting mock data in table
            PreparedStatement insertAccommodationTable =
                    connection.prepareStatement("insert into accommodation" +
                            "(id, type, bed_type, max_guests, description) " +
                            "values (5, 'quadRoom', 'single', 4, 'A room with 4 single beds') ");
            insertAccommodationTable.executeUpdate();
            PreparedStatement insertRoomFairTable =
                    connection.prepareStatement("insert into room_fair" +
                            "(id, value, season) " +
                            "values (5, 130, 'spring') ");
            insertRoomFairTable.executeUpdate();
            PreparedStatement insertAccommodationToRoomFairRelation =
                    connection.prepareStatement("insert into accommodation_to_room_fair_relation" +
                            "(id, id_accommodation, id_room_fair) " +
                            "values (5, 5, 5) ");
            insertAccommodationToRoomFairRelation.executeUpdate();

            /*
                Selecting only the room ID and its' corresponding value to be able to identify it
                It also only searches for the mock data (id = 7837) because the DB contains more data
             */
            PreparedStatement interrogateRoomPrices =
                    connection.prepareStatement("select accommodation.id,value from accommodation " +
                            "inner join accommodation_to_room_fair_relation on accommodation.id=id_accommodation " +
                            "inner join room_fair on room_fair.id=id_room_fair " +
                            "where accommodation.id = 5");

            //checks if the expected values are correct
            ResultSet resultSet = interrogateRoomPrices.executeQuery();
            RoomIDAndPriceTest roomIdAndPrice = new RoomIDAndPriceTest(0, 0);
            while (resultSet.next()) {
                assertEquals(5, resultSet.getInt("id"));
                assertEquals(130, resultSet.getDouble("value"));
                roomIdAndPrice.setId(resultSet.getInt("id"));
                roomIdAndPrice.setValue(resultSet.getDouble("value"));
            }
            log.info(roomIdAndPrice); //logs the result
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Rollback delete all entries that were inserted above
            deleteFromTable(connection, 5);
            connection.commit();
            connection.close();
        }
    }

    @SneakyThrows
    @Test
    void interrogate_db_test_multiple_rows() {
        try {
            //Inserting multiple mock data in table
            List<AccommodationTest> accommodationList = new LinkedList<>();
            accommodationList.add(new AccommodationTest(20, "singleRoom", "single",
                    1, "A simple room with one bed"));
            accommodationList.add(new AccommodationTest(21, "doubleRoom", "kingSize",
                    2, "A double room with a big bed"));
            accommodationList.add(new AccommodationTest(22, "familyRoom", "multiple",
                    5, "A family type room with multiple beds"));

            accommodationList.forEach(element -> {
                try {
                    PreparedStatement insertAccommodationTable =
                            connection.prepareStatement("insert into accommodation" +
                                    "(id, type, bed_type, max_guests, description) " +
                                    "values (?, ?, ?, ?, ?) ");
                    insertAccommodationTable.setInt(1, element.getId());
                    insertAccommodationTable.setString(2, element.getType());
                    insertAccommodationTable.setString(3, element.getBed_type());
                    insertAccommodationTable.setInt(4, element.getMax_guests());
                    insertAccommodationTable.setString(5, element.getDescription());
                    insertAccommodationTable.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            List<RoomFairTest> roomFairList = new LinkedList<>();
            roomFairList.add(new RoomFairTest(20, 130, "winter"));
            roomFairList.add(new RoomFairTest(21, 200, "winter"));
            roomFairList.add(new RoomFairTest(22, 250, "spring"));

            roomFairList.forEach(element -> {
                try {
                    PreparedStatement insertRoomFairTable =
                            connection.prepareStatement("insert into room_fair" +
                                    "(id, value, season) " +
                                    "values (?, ?, ?) ");
                    insertRoomFairTable.setInt(1, element.getId());
                    insertRoomFairTable.setDouble(2, element.getValue());
                    insertRoomFairTable.setString(3, element.getSeason());
                    insertRoomFairTable.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            List<AccommodationToRoomFairRelationTest> tableRelationList = new LinkedList<>();
            tableRelationList.add(new AccommodationToRoomFairRelationTest(20, 20, 20));
            tableRelationList.add(new AccommodationToRoomFairRelationTest(21, 21, 21));
            tableRelationList.add(new AccommodationToRoomFairRelationTest(22, 22, 22));

            tableRelationList.forEach(element -> {
                try {
                    PreparedStatement insertAccommodationToRoomFairRelation =
                            connection.prepareStatement("insert into accommodation_to_room_fair_relation" +
                                    "(id, id_accommodation, id_room_fair) " +
                                    "values (?, ?, ?) ");
                    insertAccommodationToRoomFairRelation.setInt(1, element.getId());
                    insertAccommodationToRoomFairRelation.setInt(2, element.getId_accommodation());
                    insertAccommodationToRoomFairRelation.setInt(3, element.getId_room_fair());
                    insertAccommodationToRoomFairRelation.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            /*
                Selecting only the room ID and its' corresponding value to be able to identify it
             */
            PreparedStatement interrogateRoomPrices =
                    connection.prepareStatement("select accommodation.id,value from accommodation " +
                            "inner join accommodation_to_room_fair_relation on accommodation.id=id_accommodation " +
                            "inner join room_fair on room_fair.id=id_room_fair");

            //checks if the expected values are correct
            ResultSet resultSet = interrogateRoomPrices.executeQuery();
            List<RoomIDAndPriceTest> roomIdAndPriceList = new ArrayList<>();
            while (resultSet.next()) {
                roomIdAndPriceList.add(new RoomIDAndPriceTest(resultSet.getInt("id"), resultSet.getDouble("value")));
            }
            //test if results are the expected ones
            assertEquals(20, roomIdAndPriceList.get(0).getId());
            assertEquals(21, roomIdAndPriceList.get(1).getId());
            assertEquals(22, roomIdAndPriceList.get(2).getId());
            assertEquals(130, roomIdAndPriceList.get(0).getValue());
            assertEquals(200, roomIdAndPriceList.get(1).getValue());
            assertEquals(250, roomIdAndPriceList.get(2).getValue());
            //logs the result, however if I use the method reference it will not correctly log the line number
            roomIdAndPriceList.forEach(element -> log.info(element));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Rollback delete all entries that were inserted above
            for(int i = 20; i <= 22 ; i++){
                deleteFromTable(connection, i);
                connection.commit();
            }
            connection.close();
        }
    }

    /*
        Helper method to delete from table
        For these tests it was easier to make all the tables have the same int for the id,
        Which could happen in a real world scenario, even though it is unlikely
     */
    static void deleteFromTable(Connection connection, int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("delete from accommodation_to_room_fair_relation where id = ?");
            delete.setInt(1, id);
            delete.executeUpdate();
            delete = connection.prepareStatement("delete from room_fair where id = ?");
            delete.setInt(1, id);
            delete.executeUpdate();
            delete = connection.prepareStatement("delete from accommodation where id = ?");
            delete.setInt(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Helper method to make the connection to the booking DB.
     */
    static Connection connectToBookingDb() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/booking?user=postgres&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}