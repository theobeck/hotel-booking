package booking.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import booking.core.Booking;
import booking.core.User;

import java.time.LocalDate;
import java.util.List;

/**
 * The controller for {@link User} objects.
 */
@RestController
public class UsersController {

    /**
     * Inject the service that manages User objects.
     */
    @Autowired
    private final UsersService usersService;

    /**
     * Constructor for UsersController that injects the given {@link UsersService}.
     *
     * @param usersService the users service to use
     */
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Creates a new user.
     *
     * @param username  The username of the user to create
     * @param firstName The first name of the user to create
     * @param lastName  The last name of the user to create
     * @param password  The password of the user to create
     * @param gender    The gender of the user to create
     */
    @PostMapping("/users/{username}/{firstName}/{lastName}/{password}/{gender}")
    public void createUser(final @PathVariable String username, final @PathVariable String firstName,
            final @PathVariable String lastName, final @PathVariable String password,
            final @PathVariable String gender) {
        usersService.createUser(username, firstName, lastName, password, gender);
    }

    /**
     * Gets all users in the system.
     *
     * @return All users in the system
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    /**
     * Gets a user by its username.
     *
     * @param username The username of the user to get
     *
     * @return The user with the given username
     */
    @GetMapping("/users/{username}")
    public User getUserByUsername(final @PathVariable String username) {
        return usersService.getUserByUsername(username);
    }

    /**
     * Gets all bookings for a user.
     *
     * @param username The username of the user to get bookings for
     *
     * @return All bookings for the given user
     */
    @GetMapping("/users/{username}/bookings")
    public List<Booking> getBookingsByUsername(final @PathVariable String username) {
        return usersService.getBookingsByUsername(username);
    }

    /**
     * Updates a user by its username.
     *
     * @param username  The username of the user to update
     * @param firstName The first name of the user to update
     * @param lastName  The last name of the user to update
     * @param password  The password of the user to update
     * @param gender    The gender of the user to update
     *
     * @return The user that was updated
     */
    @PutMapping("/users/{username}/{firstName}/{lastName}/{password}/{gender}")
    public User updateUserByUsername(final @PathVariable String username, final @PathVariable String firstName,
            final @PathVariable String lastName, final @PathVariable String password,
            final @PathVariable String gender) {
        return usersService.updateUserByUsername(username, firstName, lastName, password, gender);
    }

    /**
     * Books a room by its room number.
     *
     * @param username           The username of the user booking the room
     * @param roomNumber         The room number of the room to book
     * @param from               The start date of the booking
     * @param to                 The end date of the booking
     * @param totalCostOfBooking The total cost of the booking
     */
    @PutMapping("/users/{username}/book/{roomNumber}/{from}/{to}/{totalCostOfBooking}")
    public void bookRoomByUsername(final @PathVariable String username, final @PathVariable int roomNumber,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            final @PathVariable int totalCostOfBooking) {
        usersService.bookRoomByUsername(username, roomNumber, from, to, totalCostOfBooking);
    }

    /**
     * Unbooks a room by the booking.
     *
     * @param username           The username of the user unbooking the room
     * @param roomNumber         The room number of the room to unbook
     * @param from               The start date of the booking
     * @param to                 The end date of the booking
     * @param totalCostOfBooking The total cost of the booking
     */
    @PutMapping("/users/{username}/cancel/{roomNumber}/{from}/{to}/{totalCostOfBooking}")
    public void cancelBooking(final @PathVariable String username, final @PathVariable int roomNumber,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            final @PathVariable int totalCostOfBooking) {
        usersService.cancelBooking(new Booking(username, roomNumber, from, to, totalCostOfBooking));
    }

    /**
     * Delete sa user by its username.
     *
     * @param username The username of the user to delete
     *
     * @return The user that was deleted
     */
    @DeleteMapping("/users/{username}")
    public User deleteUserByUsername(final @PathVariable String username) {
        return usersService.deleteUserByUsername(username);
    }

}
