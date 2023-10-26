package booking.springboot.restserver;

import java.util.List;

import org.springframework.stereotype.Service;

import booking.core.Room;

@Service
public final class RoomService {

    /**
     * Get all rooms.
     *
     * @return all rooms.
     */
    public List<Room> getAllRooms() {
        // #TODO: Implement logic to get all rooms.
        return null;
    }

    /**
     * Create a room.
     *
     * @param room the room to create.
     *
     * @return the created room.
     */
    public Room createRoom(final Room room) {
        // #TODO: Implement logic to create a room.
        return null;
    }

    /**
     * Get a room by ID.
     *
     * @param id the ID of the room to get.
     *
     * @return the room with the given ID, or null if no room exists with that ID.
     */
    public Room getRoomById(final Long id) {
        // #TODO: Implement logic to get a room by ID.
        return null;
    }

    // Implement other CRUD operations for Room here.
}
