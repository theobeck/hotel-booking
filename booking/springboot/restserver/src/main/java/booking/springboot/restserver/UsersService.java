package booking.springboot.restserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;

import booking.core.User;
import booking.springboot.restserver.internal.UserDeserializer;
import booking.springboot.restserver.internal.UserSerializer;

@Service
public final class UsersService {

    /**
     * The object mapper object for the file manager object.
     */
    private final ObjectMapper objectMapper;

    /**
     * The filepath bookings are saved to.
     */
    private static final String USERS_PATH = "src/main/resources/booking/springboot/restserver/users.json";

    /**
     * Random object used to generate random numbers.
     */
    public UsersService() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());
        module.addSerializer(User.class, new UserSerializer());
        objectMapper.registerModule(module);
    }

    /**
     * Create a user.
     *
     * @param username  the username of the user to create.
     * @param firstName the first name of the user to create.
     * @param lastName  the last name of the user to create.
     * @param password  the password of the user to create.
     * @param gender    the gender of the user to create.
     */
    public void createUser(final String username, final String firstName, final String lastName,
            final String password, final String gender) {
        final User user = new User(username, firstName, lastName, password, gender);
        final List<User> users = getAllUsers();
        users.add(user);
        updateUsers(users);
    }

    /**
     * Get all users.
     *
     * @return all users.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(USERS_PATH)) {
            TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
            };
            users = objectMapper.readValue(fileInputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Get a user by its username.
     *
     * @param username the username of the user to get.
     * @return the user with the given username.
     */
    public User getUserByUsername(final String username) {
        final List<User> users = getAllUsers();
        for (final User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Update a user by username.
     *
     * @param username  the username of the user to update.
     * @param firstName the first name of the user to update.
     * @param lastName  the last name of the user to update.
     * @param password  the password of the user to update.
     * @param gender    the gender of the user to update.
     */
    public void updateUserByUsername(final String username, final String firstName, final String lastName,
            final String password, final String gender) {
        final List<User> users = getAllUsers();
        for (final User user : users) {
            if (user.getUsername().equals(username)) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPassword(password);
                user.setGender(gender);
                updateUsers(users);
                return;
            }
        }
    }

    /**
     * Delete a user by username.
     *
     * @param username the username of the user to delete.
     */
    public void deleteUserByUsername(final String username) {
        final List<User> users = getAllUsers();
        for (final User user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);
                updateUsers(users);
                return;
            }
        }
    }

    private void updateUsers(final List<User> users) {
        try {
            final ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            objectWriter.writeValue(new File(USERS_PATH), users);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
