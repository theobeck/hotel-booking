package booking.fxui.internal;

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
 * A class that provides access to the hotel booking system's REST API.
 */
public final class RestAccess {

    /**
     * The base URL path for the REST Server.
     */
    public static final String BASE_URL = "http://localhost:8080/";

    /**
     * The base URL path for room requests.
     */
    public static final String ROOMS_PATH = "rooms/";

    /**
     * The base URL path for user requests.
     */
    public static final String USERS_PATH = "users/";

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
     * Creates a new RestAccess with a given HTTP client.
     *
     * @param httpClient The HTTP client.
     */
    public RestAccess(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Creates a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     *
     * @throws Exception If the room already exists.
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
     * Gets all rooms.
     *
     * @return A list of all rooms.
     *
     * @throws Exception If there are no rooms.
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
     * Gets a room by number.
     *
     * @param roomNumber The room number.
     *
     * @return The room.
     *
     * @throws Exception If the room does not exist.
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
     * Updates a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     *
     * @throws Exception If the room does not exist.
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
     * Books a room.
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
     * @throws Exception If the room is already booked.
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
     * Cancels a booking.
     *
     * @param roomNumber         The room number.
     * @param username           The username.
     * @param from               The start date of the booking.
     * @param to                 The end date of the booking.
     * @param totalCostOfBooking The total cost of the booking.
     *
     * @throws Exception If the booking does not exist.
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
     * Deletes a room.
     *
     * @param roomNumber The room number.
     *
     * @return The deleted room.
     *
     * @throws Exception If the room does not exist.
     */
    public Room deleteRoomByNumber(final int roomNumber) throws Exception {
        final String url = BASE_URL + ROOMS_PATH + roomNumber;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
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
     * Creates a user.
     *
     * @param username  The username.
     * @param firstName The first name.
     * @param lastName  The last name.
     * @param password  The password.
     * @param gender    The gender.
     *
     * @throws Exception If the user already exists.
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
     * Gets all users.
     *
     * @return A list of all users.
     *
     * @throws Exception If there are no users.
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
     * Gets a user by username.
     *
     * @param username The username.
     * @return The user.
     *
     * @throws Exception If the user does not exist.
     */
    public User getUserByUsername(final String username) throws Exception {
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
            if (response.body().equals("")) {
                return null;
            }
            return objectMapper.readValue(response.body(), User.class);

        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Gets all bookings by username.
     *
     * @param username The username.
     * @return A list of all bookings by username.
     *
     * @throws Exception If there are no bookings.
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
     * Updates a user by username.
     *
     * @param username  The username.
     * @param firstName The first name.
     * @param lastName  The last name.
     * @param password  The password.
     * @param gender    The gender.
     *
     * @throws Exception If the user does not exist.
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
     * Deletes a user by username.
     *
     * @param username The username.
     *
     * @throws Exception If the user does not exist.
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
