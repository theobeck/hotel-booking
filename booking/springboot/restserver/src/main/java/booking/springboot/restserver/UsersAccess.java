package booking.springboot.restserver;

import java.time.LocalDate;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import booking.core.Booking;
import booking.core.User;

/**
 * A class for accessing the users REST API.
 */
public class UsersAccess {

    /**
     * The base URL for the REST API.
     */
    private static final String BASE_URL = "http://localhost:8080/users/";

    /**
     * The REST template.
     */
    private final RestTemplate restTemplate;

    /**
     * Default constructor for Users Access.
     */
    public UsersAccess() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Create a user.
     *
     * @param username  The username.
     * @param firstName The first name.
     * @param lastName  The last name.
     * @param password  The password.
     * @param gender    The gender.
     */
    public void createUser(final String username, final String firstName, final String lastName,
            final String password, final String gender) {
        final String url = BASE_URL + username + "/" + firstName + "/" + lastName + "/" + password + "/" + gender;
        restTemplate.postForLocation(url, null);
    }

    /**
     * Get all users.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(BASE_URL,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        return response.getBody();
    }

    /**
     * Get a user by username.
     *
     * @param username The username.
     * @return The user.
     */
    public User getUserByUsername(final String username) {
        final String url = BASE_URL + username;
        return restTemplate.getForObject(url, User.class);
    }

    /**
     * Get all bookings for a user.
     *
     * @param username The username.
     * @return A list of all bookings for the user.
     */
    public List<Booking> getBookingsByUsername(final String username) {
        final String url = BASE_URL + username + "/bookings";
        ResponseEntity<List<Booking>> response = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
                });
        return response.getBody();
    }

    /**
     * Book a room.
     *
     * @param username   The username.
     * @param roomNumber The room number.
     * @param bookedFrom When the booking starts.
     * @param bookedTo   When the booking ends.
     */
    public void bookRoom(final String username, final int roomNumber, final LocalDate bookedFrom,
            final LocalDate bookedTo) {
        final String url = BASE_URL + username + "/book/" + roomNumber + "/" + bookedFrom + "/" + bookedTo;
        restTemplate.put(url, null);
    }

    /**
     * Cancel a booking.
     *
     * @param username   The username.
     * @param roomNumber The room number.
     * @param bookedFrom When the booking starts.
     * @param bookedTo   When the booking ends.
     */
    public void cancelBooking(final String username, final int roomNumber, final LocalDate bookedFrom,
            final LocalDate bookedTo) {
        final String url = BASE_URL + username + "/cancel/" + roomNumber + "/" + bookedFrom + "/" + bookedTo;
        restTemplate.put(url, null);
    }

    /**
     * Update a user by username.
     *
     * @param username  The username.
     * @param firstName The first name.
     * @param lastName  The last name.
     * @param password  The password.
     */
    public void updateUserByUsername(final String username, final String firstName, final String lastName,
            final String password) {
        final String url = BASE_URL + username + "/" + firstName + "/" + lastName + "/" + password;
        restTemplate.put(url, null);
    }

    /**
     * Delete a user by username.
     *
     * @param username The username.
     */
    public void deleteUserByUsername(final String username) {
        final String url = BASE_URL + username;
        restTemplate.delete(url);
    }
}
