package booking.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import booking.core.Booking;
import booking.core.Room;

import java.time.LocalDate;
import java.util.List;

/**
 * The controller for {@link Room} objects.
 */
@RestController
public class RoomController {

    /**
     * Inject the service that manages Room objects.
     */
    @Autowired
    private final RoomService roomService;

    /**
     * Constructor for RoomController that injects the given {@link RoomService}.
     *
     * @param roomService the room service to use
     */
    public RoomController(final RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Creates a new room.
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
     * Gets all rooms in the system.
     *
     * @return All rooms in the system
     */
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * Gets a room by its room number.
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
     * Updates the room by given number.
     *
     * @param roomNumber    The room number of the room to update
     * @param roomCapacity  The new room capacity
     * @param pricePerNight The new price per night
     *
     * @return The room that was updated
     */
    @PutMapping("/rooms/{roomNumber}/update/{roomCapacity}/{pricePerNight}")
    public Room updateRoomByNumber(final @PathVariable int roomNumber, final @PathVariable int roomCapacity,
            final @PathVariable int pricePerNight) {
        return roomService.updateRoomByNumber(roomNumber, roomCapacity, pricePerNight);
    }

    /**
     * Books a room by its room number.
     *
     * @param roomNumber The room number of the room to update
     * @param from       The start date of the booking
     * @param to         The end date of the booking
     * @param username   The username of the user booking the room
     *
     * @return A response entity with a message and status code
     */
    @PutMapping("/rooms/{roomNumber}/book/{from}/{to}/{username}")
    public ResponseEntity<String> bookRoomByNumber(final @PathVariable int roomNumber,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            final @PathVariable String username) {

        roomService.bookRoomByNumber(roomNumber, from, to, username);
        return new ResponseEntity<>("Room booked successfully", HttpStatus.OK);
    }

    /**
     * Unbooks a room by the booking.
     *
     * @param roomNumber         The room number of the room to unbook
     * @param username           The username of the user cancelling the booking
     * @param from               The start date of the booking
     * @param to                 The end date of the booking
     * @param totalCostOfBooking The total cost of the booking
     */
    @PutMapping("/rooms/{roomNumber}/cancel/{username}/{from}/{to}/{totalCostOfBooking}")
    public void cancelBooking(final @PathVariable int roomNumber, final @PathVariable String username,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            final @PathVariable int totalCostOfBooking) {
        roomService.cancelBooking(new Booking(username, roomNumber, from, to, totalCostOfBooking));
    }

    /**
     * Deletes a room by its room number.
     *
     * @param roomNumber The room number of the room to delete
     *
     * @return The room that was deleted
     */
    @DeleteMapping("/rooms/{roomNumber}")
    public Room deleteRoomByNumber(final @PathVariable int roomNumber) {
        return roomService.deleteRoomByNumber(roomNumber);
    }
}
