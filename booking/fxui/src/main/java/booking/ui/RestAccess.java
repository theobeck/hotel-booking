package booking.ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import booking.core.Room;
import booking.core.User;
import booking.ui.internal.RoomDeserializer;
import booking.ui.internal.UserDeserializer;

/**
 * A REST API access file.
 */
public final class RestAccess {

    /**
     * The HTTP status codes.
     */
    private enum HTTPCodes {
        /**
         * The OK, Bad Request, Not Found, and Internal Server Error codes.
         */
        OK(200), BAD_REQUEST(400), NOT_FOUND(404), INTERNAL_SERVER_ERROR(500);

        /**
         * The code of the response.
         */
        private final int code;

        /**
         * The constructor for the HTTP codes.
         *
         * @param code The code of the response.
         */
        HTTPCodes(final int code) {
            this.code = code;
        }

        /**
         * Get the code of the response.
         *
         * @return The code of the response.
         */
        public int getCode() {
            return code;
        }
    }

    /**
     * The base URL for the REST API.
     */
    private static final String BASE_URL = "http://localhost:8080/";

    /**
     * Create a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     */
    public void createRoom(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        final String url = BASE_URL + "rooms/" + roomNumber + "/" + roomCapacity + "/" + pricePerNight;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all rooms.
     *
     * @return A list of all rooms.
     */
    public List<Room> getAllRooms() {
        final String url = BASE_URL + "rooms/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTPCodes.OK.getCode()) {
                // Parse the JSON response and convert it to a List<Room>
                ObjectMapper objectMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(Room.class, new RoomDeserializer());
                objectMapper.registerModule(module);

                List<Room> rooms = objectMapper.readValue(response.body(), new TypeReference<List<Room>>() {
                });

                return rooms;
            } else {
                // Handle the response status code other than 200 (e.g., error handling)
                System.err.println("Error: HTTP " + response.statusCode());
                return Collections.emptyList();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Get a room by number.
     *
     * @param roomNumber The room number.
     *
     * @return The room.
     */
    public Room getRoomByNumber(final int roomNumber) {
        final String url = BASE_URL + "rooms/" + roomNumber;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTPCodes.OK.getCode()) {
                // Parse the JSON response and convert it to a Room
                ObjectMapper objectMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(Room.class, new RoomDeserializer());
                objectMapper.registerModule(module);

                Room room = objectMapper.readValue(response.body(), Room.class);

                return room;
            } else {
                // Handle the response status code other than 200 (e.g., error handling)
                System.err.println("Error: HTTP " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     */
    public void updateRoomByNumber(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        final String url = BASE_URL + "rooms/" + roomNumber + "/update/" + roomCapacity + "/" + pricePerNight;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Book a room.
     *
     * @param roomNumber The room number.
     * @param from       The start date of the booking.
     * @param to         The end date of the booking.
     * @param user       The username of the user booking the room.
     */
    public void bookRoomByNumber(final int roomNumber, final LocalDate from, final LocalDate to,
            final User user) {
        final String url = BASE_URL + "rooms/" + roomNumber + "/book/" + from + "/" + to + "/" + user;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancel a booking.
     *
     * @param roomNumber The room number.
     * @param username   The username.
     */
    public void cancelBooking(final int roomNumber, final String username) {
        final String url = BASE_URL + "rooms/" + roomNumber + "/cancel/" + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a room.
     *
     * @param roomNumber The room number.
     */
    public void deleteRoomByNumber(final int roomNumber) {
        final String url = BASE_URL + "rooms/" + roomNumber;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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
    public void createUser(final String username, final String firstName, final String lastName, final String password,
            final String gender) {
        final String url = BASE_URL + "users/" + username + "/" + firstName + "/" + lastName + "/" + password + "/"
                + gender;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all users.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        final String url = BASE_URL + "users/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTPCodes.OK.getCode()) {
                // Parse the JSON response and convert it to a List<User>
                ObjectMapper objectMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(User.class, new UserDeserializer());
                objectMapper.registerModule(module);

                List<User> users = objectMapper.readValue(response.body(), new TypeReference<List<User>>() {
                });

                return users;
            } else {
                // Handle the response status code other than 200 (e.g., error handling)
                System.err.println("Error: HTTP " + response.statusCode());
                return Collections.emptyList();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Get a user by username.
     *
     * @param username The username.
     * @return The user.
     */
    public User getUserByUsername(final String username) {
        final String url = BASE_URL + "users/" + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTPCodes.OK.getCode()) {
                // Parse the JSON response and convert it to a User
                ObjectMapper objectMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(User.class, new UserDeserializer());
                objectMapper.registerModule(module);

                User user = objectMapper.readValue(response.body(), User.class);

                return user;
            } else {
                // Handle the response status code other than 200 (e.g., error handling)
                System.err.println("Error: HTTP " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update a user by username.
     *
     * @param username  The username.
     * @param firstName The first name.
     * @param lastName  The last name.
     * @param password  The password.
     * @param gender    The gender.
     */
    public void updateUserByUsername(final String username, final String firstName, final String lastName,
            final String password, final String gender) {
        final String url = BASE_URL + "users/" + username + "/" + firstName + "/" + lastName + "/" + password + "/"
                + gender;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a user by username.
     *
     * @param username The username.
     */
    public void deleteUserByUsername(final String username) {
        final String url = BASE_URL + "users/" + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
