package booking.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import booking.core.Room;

import java.time.LocalDate;
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
     * Create a new room.
     *
     * @param roomNumber    The room number of the room to create
     * @param roomCapacity  The room capacity of the room to create
     * @param pricePerNight The price per night of the room to create
     */
    @PostMapping("/rooms/{roomNumber}/{roomCapacity}/{pricePerNight}")
    public void createRoom(final @PathVariable int roomNumber,
            final @PathVariable int roomCapacity, final @PathVariable int pricePerNight) {
        roomService.createRoom(roomNumber, roomCapacity, pricePerNight);
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
     * Get a room by its room number.
     *
     * @param roomNumber The room Number of the room to get
     *
     * @return The room with the given room number
     */
    @GetMapping("/rooms/{roomNumber}")
    public Room getRoomByNumber(final @PathVariable int roomNumber) {
        return roomService.getRoomByNumber(roomNumber);
    }

    /**
     * Update the given rooms.
     *
     * @param rooms The rooms to update
     */
    public void updateRooms(final List<Room> rooms) {
        roomService.updateRooms(rooms);
    }

    /**
     * Book a room by its room number.
     *
     * @param roomNumber The room number of the room to update
     * @param from       The start date of the booking
     * @param to         The end date of the booking
     * @param username   The username of the user booking the room
     */
    @PutMapping("/rooms/{roomNumber}/{from}/{to}/{username}")
    public void bookRoomByNumber(final @PathVariable int roomNumber,
            final @PathVariable LocalDate from, final @PathVariable LocalDate to, final @PathVariable String username) {
        roomService.bookRoomByNumber(roomNumber, from, to, username);
    }

    // create a method that unbooks room by number.
    /**
     * Unbook a room by its room number.
     *
     * @param roomNumber The room number of the room to unbook
     * @param username   The username of the user unbooking the room
     */
    @PutMapping("/rooms/{roomNumber}/cancel/{username}")
    public void cancelBooking(final @PathVariable int roomNumber, final @PathVariable String username) {
        roomService.cancelBooking(roomNumber, username);
    }

    /**
     * Delete a room by its room number.
     *
     * @param roomNumber The room number of the room to delete
     */
    @DeleteMapping("/rooms/{roomNumber}")
    public void deleteRoomByNumber(final @PathVariable int roomNumber) {
        roomService.deleteRoomByNumber(roomNumber);
    }
}
