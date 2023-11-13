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
 * The controller for User objects.
 */
@RestController
public final class UsersController {

    /**
     * Inject the service that manages User objects.
     */
    @Autowired
    private final UsersService usersService;

    /**
     * Constructor for UsersController that injects the given UsersService.
     *
     * @param usersService the users service to use
     */
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Create a new user.
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
     * Get all users in the system.
     *
     * @return All users in the system
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    /**
     * Get a user by its username.
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
     * Get all bookings for a user.
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
     * Update a user by its username.
     *
     * @param username  The username of the user to update
     * @param firstName The first name of the user to update
     * @param lastName  The last name of the user to update
     * @param password  The password of the user to update
     * @param gender    The gender of the user to update
     */
    @PutMapping("/users/{username}/{firstName}/{lastName}/{password}/{gender}")
    public void updateUserByUsername(final @PathVariable String username, final @PathVariable String firstName,
            final @PathVariable String lastName, final @PathVariable String password,
            final @PathVariable String gender) {
        usersService.updateUserByUsername(username, firstName, lastName, password, gender);
    }

    /**
     * Book a room by its room number.
     *
     * @param username   The username of the user booking the room
     * @param roomNumber The room number of the room to book
     * @param from       The start date of the booking
     * @param to         The end date of the booking
     */
    @PutMapping("/users/{username}/book/{roomNumber}/{from}/{to}")
    public void bookRoomByUsername(final @PathVariable String username, final @PathVariable int roomNumber,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            final @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        usersService.bookRoomByUsername(username, roomNumber, from, to);
    }

    /**
     * Unbook a room by the booking.
     *
     * @param username The username of the user unbooking the room
     * @param booking  The booking being cancelled
     */
    @PutMapping("/users/{username}/cancel/{booking}")
    public void cancelBooking(final @PathVariable String username, final @PathVariable Booking booking) {
        usersService.cancelBooking(username, booking);
    }

    /**
     * Delete a user by its username.
     *
     * @param username The username of the user to delete
     */
    @DeleteMapping("/users/{username}")
    public void deleteUserByUsername(final @PathVariable String username) {
        usersService.deleteUserByUsername(username);
    }

}
