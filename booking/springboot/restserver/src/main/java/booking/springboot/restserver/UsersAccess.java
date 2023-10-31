package booking.springboot.restserver;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
     */
    public void createUser(final String username, final String firstName, final String lastName,
            final String password) {
        final String url = BASE_URL + username + "/" + firstName + "/" + lastName + "/" + password;
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

    public static void main(final String[] args) {
        UsersAccess usersAccess = new UsersAccess();
        List<User> users = usersAccess.getAllUsers();

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("First Name: " + user.getFirstName());
                System.out.println("Last Name: " + user.getLastName());
                System.out.println();
            }
        } else {
            System.out.println("No users found.");
        }
    }
}
