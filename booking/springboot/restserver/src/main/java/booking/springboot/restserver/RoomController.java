package booking.springboot.restserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import booking.core.Room;

import java.util.List;

/**
 * The controller for Room objects.
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {

    /**
     * Inject the service that manages Room objects.
     */
    private final RoomService roomService;

    /**
     * Constructor for RoomController that injects the given RoomService.
     *
     * @param roomService the room service to use
     */
    public RoomController(final RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public Room createRoom(final @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/{id}")
    public Room getRoomById(final @PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // Implement other CRUD operations for Room here.
}
