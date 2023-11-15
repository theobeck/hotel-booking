package booking.ui.internal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import booking.core.Booking;
import booking.core.Room;
import booking.core.User;

/**
 * A REST API access file.
 */
public final class RestAccess {

    /**
     * The base URL for the REST API.
     */
    private static final String BASE_URL = "http://localhost:8080/";

    /**
     * The path for room requests.
     */
    private static final String ROOMS_PATH = "rooms/";

    /**
     * The path for user requests.
     */
    private static final String USERS_PATH = "users/";

    /**
     * The HTTP client.
     */
    private HttpClient httpClient;

    /**
     * Default constructor for RestAccess.
     */
    public RestAccess() {

    }

    /**
     * Constructor for RestAccess.
     *
     * @param httpClient The HTTP client.
     */
    public RestAccess(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Create a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     *
     * @throws Exception
     */
    public void createRoom(final int roomNumber, final int roomCapacity, final int pricePerNight) throws Exception {
        final String url = BASE_URL + ROOMS_PATH + roomNumber + "/" + roomCapacity + "/" + pricePerNight;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Get all rooms.
     *
     * @return A list of all rooms.
     *
     * @throws Exception
     */
    public List<Room> getAllRooms() throws Exception {
        final String url = BASE_URL + ROOMS_PATH;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = generateHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Room.class, new RoomDeserializer());
            objectMapper.registerModule(module);

            return objectMapper.readValue(response.body(), new TypeReference<List<Room>>() {
            });

        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Get a room by number.
     *
     * @param roomNumber The room number.
     *
     * @return The room.
     *
     * @throws Exception
     */
    public Room getRoomByNumber(final int roomNumber) throws Exception {
        final String url = BASE_URL + ROOMS_PATH + roomNumber;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = generateHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Room.class, new RoomDeserializer());
            objectMapper.registerModule(module);

            return objectMapper.readValue(response.body(), Room.class);

        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Update a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     *
     * @throws Exception
     */
    public void updateRoomByNumber(final int roomNumber, final int roomCapacity, final int pricePerNight)
            throws Exception {
        final String url = BASE_URL + ROOMS_PATH + roomNumber + "/update/" + roomCapacity + "/" + pricePerNight;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Book a room.
     *
     * @param roomNumber         The room number.
     * @param from               The start date of the booking.
     * @param to                 The end date of the booking.
     * @param username           The username of the user booking the room.
     * @param totalCostOfBooking The total cost of the booking.
     * @param which              If which = "room", only update room, if which =
     *                           "user", only update user, if which = "both", update
     *                           both.
     *
     * @throws Exception
     */
    public void bookRoomByNumber(final int roomNumber, final LocalDate from, final LocalDate to,
            final String username, final int totalCostOfBooking, final String which) throws Exception {
        if (!(which.equals("room") || which.equals("user") || which.equals("both"))) {
            throw new IllegalArgumentException("Invalid argument for which");
        }

        boolean room = false;
        boolean user = false;
        if (which.equals("both")) {
            room = true;
            user = true;
        }
        if (which.equals("room")) {
            room = true;
        }
        if (which.equals("user")) {
            user = true;
        }

        if (room) {
            final String url = BASE_URL + ROOMS_PATH + roomNumber + "/book/" + from + "/" + to + "/" + username;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            try {
                generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw e;
            }
        }
        if (user) {
            final String url2 = BASE_URL + USERS_PATH + username + "/book/" + roomNumber + "/" + from + "/" + to + "/"
                    + totalCostOfBooking;
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create(url2))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            try {
                generateHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw e;
            }
        }
    }

    /**
     * Cancel a booking.
     *
     * @param roomNumber         The room number.
     * @param username           The username.
     * @param from               The start date of the booking.
     * @param to                 The end date of the booking.
     * @param totalCostOfBooking The total cost of the booking.
     *
     * @throws Exception
     */
    public void cancelBooking(final int roomNumber, final String username, final LocalDate from, final LocalDate to,
            final int totalCostOfBooking) throws Exception {
        final String url = BASE_URL + ROOMS_PATH + roomNumber + "/cancel/" + username + "/" + from + "/" + to + "/"
                + totalCostOfBooking;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        final String url2 = BASE_URL + USERS_PATH + username + "/cancel/" + roomNumber + "/" + from + "/" + to + "/"
                + totalCostOfBooking;
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(url2))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            generateHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Delete a room.
     *
     * @param roomNumber The room number.
     *
     * @throws Exception
     */
    public void deleteRoomByNumber(final int roomNumber) throws Exception {
        final String url = BASE_URL + ROOMS_PATH + roomNumber;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
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
     *
     * @throws Exception
     */
    public void createUser(final String username, final String firstName, final String lastName, final String password,
            final String gender) throws Exception {
        final String url = BASE_URL + USERS_PATH + username + "/" + firstName + "/" + lastName + "/" + password + "/"
                + gender;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Get all users.
     *
     * @return A list of all users.
     *
     * @throws Exception
     */
    public List<User> getAllUsers() throws Exception {
        final String url = BASE_URL + USERS_PATH;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = generateHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(User.class, new UserDeserializer());
            objectMapper.registerModule(module);

            return objectMapper.readValue(response.body(), new TypeReference<List<User>>() {
            });

        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Get a user by username.
     *
     * @param username The username.
     * @return The user.
     *
     * @throws Exception
     */
    public User getUserByUsername(final String username) {
        final String url = BASE_URL + USERS_PATH + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = generateHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(User.class, new UserDeserializer());
            objectMapper.registerModule(module);

            return objectMapper.readValue(response.body(), User.class);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all bookings by username.
     *
     * @param username The username.
     * @return A list of all bookings by username.
     *
     * @throws Exception
     */
    public List<Booking> getBookingsByUsername(final String username) throws Exception {
        final String url = BASE_URL + USERS_PATH + username + "/bookings";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = generateHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Booking.class, new BookingDeserializer());
            objectMapper.registerModule(module);

            return objectMapper.readValue(response.body(), new TypeReference<List<Booking>>() {
            });
        } catch (IOException | InterruptedException e) {
            throw e;
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
     *
     * @throws Exception
     */
    public void updateUserByUsername(final String username, final String firstName, final String lastName,
            final String password, final String gender) throws Exception {
        final String url = BASE_URL + USERS_PATH + username + "/" + firstName + "/" + lastName + "/" + password + "/"
                + gender;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Delete a user by username.
     *
     * @param username The username.
     *
     * @throws Exception
     */
    public void deleteUserByUsername(final String username) throws Exception {
        final String url = BASE_URL + USERS_PATH + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            generateHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    private HttpClient generateHttpClient() {
        HttpClient httpClientToUse = httpClient;
        if (httpClientToUse == null) {
            httpClientToUse = HttpClient.newHttpClient();
        }
        return httpClientToUse;
    }
}
