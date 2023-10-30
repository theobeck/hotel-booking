package booking.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import booking.core.Room;

import java.util.List;

/**
 * The controller for Room objects.
 */
@RestController
public final class RoomController {

    /**
     * Inject the service that manages Room objects.
     */
    @Autowired
    private final RoomService roomService;

    /**
     * Constructor for RoomController that injects the given RoomService.
     *
     * @param roomService the room service to use
     */
    public RoomController(final RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Get all rooms in the system.
     *
     * @return All rooms in the system
     */
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * Create a new room.
     *
     * @param room The room to create
     *
     * @return The created room
     */
    @PostMapping("/rooms/{roomNumber}")
    public Room createRoom(final @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    /**
     * Get a room by its room number.
     *
     * @param roomNumber The room Number of the room to get
     *
     * @return The room with the given room number
     */
    @GetMapping("/rooms/{roomNumber}")
    public Room getRoomByNumber(final @PathVariable int roomNumber) {
        return roomService.getRoomById(roomNumber);
    }

}
