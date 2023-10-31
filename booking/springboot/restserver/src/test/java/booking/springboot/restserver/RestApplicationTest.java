package booking.springboot.restserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RestApplication.class)
@AutoConfigureMockMvc
public class RestApplicationTest {
    // @Autowired
    // private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        // mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
        // .andExpect(status().isOk())
        // .andExpect(content().string(equalTo("Greetings from Spring Boot!")));

        // package booking.json;

        // import static org.junit.jupiter.api.Assertions.assertEquals;
        // import static org.junit.jupiter.api.Assertions.assertTrue;

        // import java.io.File;
        // import java.io.IOException;
        // import java.time.LocalDate;
        // import java.util.ArrayList;
        // import java.util.List;

        // import org.junit.jupiter.api.BeforeEach;
        // import org.junit.jupiter.api.Test;

        // import booking.core.Room;
        // import booking.core.User;

        // /**
        // * A test class for the {@link ReadWrite} class.
        // */
        // public class ReadWriteTest {

        // private final String roomFilePath = "readWrite-" + System.currentTimeMillis()
        // + ".json";
        // private final String userFilePath = "users-" + System.currentTimeMillis() +
        // ".json";
        // private ReadWrite readWrite;
        // private List<Room> rooms;
        // private List<User> users;

        // @BeforeEach
        // public void setUp() {
        // readWrite = new ReadWrite();

        // rooms = new ArrayList<>();
        // rooms.add(new Room(1, 2, 200, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1,
        // 7), "test1"));
        // rooms.add(new Room(2, 4, 400, LocalDate.of(2023, 1, 10), LocalDate.of(2023,
        // 1, 20), "test2"));
        // rooms.add(new Room(3, 6, 600, LocalDate.of(2023, 2, 7), LocalDate.of(2023, 2,
        // 10), "test3"));

        // users = new ArrayList<>();
        // users.add(new User("test1", "test1", "test1", "test1", "male"));
        // users.add(new User("test2", "test2", "test2", "test2", "male"));
        // users.add(new User("test3", "test3", "test3", "test3", "male"));

        // }

        // /**
        // * @throws IOException
        // */
        // @Test
        // public void testWriteAndReadRoomFromFile() throws IOException {

        // // Write the rooms to a test file
        // readWrite.writeRoomsToFile(rooms, roomFilePath);

        // // Read the rooms back from the test file
        // List<Room> restoredRooms = readWrite.readRoomsFromFile(roomFilePath);

        // // Check if the rooms match what was written
        // assertEquals(rooms.size(), restoredRooms.size());
        // for (int i = 0; i < rooms.size(); i++) {
        // Room originalRoom = rooms.get(i);
        // Room restoredRoom = restoredRooms.get(i);
        // assertTrue(originalRoom.equals(restoredRoom));
        // for (int j = 0; j < originalRoom.getBookings().size(); j++) {
        // assertTrue(originalRoom.getBookings().get(j).equals(restoredRoom.getBookings().get(j)));

        // }
        // }

        // // Clean up the test file
        // File testFile = new File(roomFilePath);
        // assertTrue(testFile.delete());
        // }

        // /**
        // * @throws IOException
        // */
        // @Test
        // public void testWriteAndReadUserFromFile() throws IOException {

        // // Write the users to a test file
        // readWrite.writeUsersToFile(users, userFilePath);

        // // Read the users back from the test file
        // List<User> restoredUsers = readWrite.readUsersFromFile(userFilePath);

        // // Check if the users match what was written
        // assertEquals(users.size(), restoredUsers.size());
        // for (int i = 0; i < users.size(); i++) {
        // User originalUser = users.get(i);
        // User restoredUser = restoredUsers.get(i);
        // assertTrue(originalUser.equals(restoredUser));
        // }

        // // Clean up the test file
        // File testFile = new File(userFilePath);
        // assertTrue(testFile.delete());
        // }
        // }

    }
}
