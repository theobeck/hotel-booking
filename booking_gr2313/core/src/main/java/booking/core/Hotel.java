package booking.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import booking.json.ReadWrite;

public class Hotel {

    /**
     * The file manager object.
     */
    private ReadWrite fileManager = new ReadWrite();

    /**
     * The list of rooms in the Hotel.
     */
    private List<Room> rooms = new ArrayList<>();

    /**
     * The upper bound for capacity of a newly generated room.
     */
    private final int UPPER_BOUND_CAPACITY = 8;

    /**
     * How much the room's cost increases with the capacity.
     */
    private final int COST_PER_PERSON = 750;

    /**
     * Constructs a new Hotel object with the following variables defined.
     * @param filename
     * @param nmbRooms
     */
    public Hotel(final String filename, final int nmbRooms) {
        rooms = fileManager.restoredListFromFile(filename);
        List<Integer> occupiedRoomNumbers = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            occupiedRoomNumbers.add(rooms.get(i).getRoomNumber());
        }
        if (rooms.size() < nmbRooms) {
            for (int i = 1; i <= nmbRooms; i++) {
                if (occupiedRoomNumbers.contains(i)) {
                    continue;
                }
                Random random = new Random();
                int cap = random.nextInt(UPPER_BOUND_CAPACITY) + 1;
                Room room = new Room(i, cap, cap * COST_PER_PERSON);
                rooms.add(room);
            }
            fileManager.writeToFile(rooms, filename);
        }
    }

    /**
     * @return The current file manager object.
     */
    public ReadWrite getFileManager() {
        return fileManager;
    }

    /**
     * @param newFileManager Change the current file manager object.
     */
    public void setFileManager(final ReadWrite newFileManager) {
        fileManager = newFileManager;
    }

    /**
     * @return The list of rooms.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * @param newRooms Change the hotel's list of rooms.
     */
    public void setRooms(final List<Room> newRooms) {
        rooms = newRooms;
    }
}
