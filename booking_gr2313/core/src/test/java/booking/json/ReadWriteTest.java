package booking.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import booking.core.Room;

public class ReadWriteTest {

    private final String filePath = "readWrite-" + System.currentTimeMillis() + ".json";
    private ReadWrite readWrite;
    private List<Room> rooms;

    @BeforeEach
    public void setUp() {
        readWrite = new ReadWrite();
        rooms = new ArrayList<>();
        rooms.add(new Room(1, 2, 200));
        rooms.add(new Room(2, 3, 400));
        rooms.add(new Room(3, 1, 100));
    }

    @Test
    public void testWriteAndReadFromFile() throws IOException {

        // Write the rooms to a test file
        readWrite.writeToFile(rooms, filePath);

        // Read the rooms back from the test file
        List<Room> restoredRooms = readWrite.restoredListFromFile(filePath);

        // Check if the rooms match what was written
        assertEquals(rooms.size(), restoredRooms.size());
        for (int i = 0; i < rooms.size(); i++) {
            Room originalRoom = rooms.get(i);
            Room restoredRoom = restoredRooms.get(i);
            assertEquals(originalRoom.getRoomNumber(), restoredRoom.getRoomNumber());
            assertEquals(originalRoom.getRoomCapacity(), restoredRoom.getRoomCapacity());
            assertEquals(originalRoom.getPricePerNight(), restoredRoom.getPricePerNight());
        }

        // Clean up the test file
        File testFile = new File(filePath);
        assertTrue(testFile.delete());
    }
}
