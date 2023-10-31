package booking.springboot.restserver;

import java.time.LocalDate;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import booking.core.Room;

/**
 * A class for accessing the room REST API.
 */
public class RoomAccess {

    /**
     * The base URL for the REST API.
     */
    private static final String BASE_URL = "http://localhost:8080/rooms/";

    /**
     * The REST template.
     */
    private final RestTemplate restTemplate;

    /**
     * Default constructor for Room Access.
     */
    public RoomAccess() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Create a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     */
    public void createRoom(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        final String url = BASE_URL + roomNumber + "/" + roomCapacity + "/" + pricePerNight;
        restTemplate.postForLocation(url, null);
    }

    /**
     * Get all rooms.
     *
     * @return A list of all rooms.
     */
    public List<Room> getAllRooms() {
        ResponseEntity<List<Room>> response = restTemplate.exchange(BASE_URL,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Room>>() {
                });
        return response.getBody();
    }

    /**
     * Get a room by number.
     *
     * @param roomNumber The room number.
     *
     * @return The room.
     */
    public Room getRoomByNumber(final int roomNumber) {
        final String url = BASE_URL + roomNumber;
        return restTemplate.getForObject(url, Room.class);
    }

    /**
     * Update a room.
     *
     * @param roomNumber    The room number.
     * @param roomCapacity  The room capacity.
     * @param pricePerNight The price per night.
     */
    public void updateRoomByNumber(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        final String url = BASE_URL + roomNumber + "/update/" + roomCapacity + "/" + pricePerNight;
        restTemplate.put(url, null);
    }

    /**
     * Book a room.
     *
     * @param roomNumber The room number.
     * @param from       The start date.
     * @param to         The end date.
     * @param username   The username.
     */
    public void bookRoomByNumber(final int roomNumber, final LocalDate from, final LocalDate to,
            final String username) {
        final String url = BASE_URL + roomNumber + "/book/" + from + "/" + to + "/" + username;
        restTemplate.put(url, null);
    }

    /**
     * Cancel a booking.
     *
     * @param roomNumber The room number.
     * @param username   The username.
     */
    public void cancelBooking(final int roomNumber, final String username) {
        final String url = BASE_URL + roomNumber + "/cancel/" + username;
        restTemplate.put(url, null);
    }

    /**
     * Delete a room.
     *
     * @param roomNumber The room number.
     */
    public void deleteRoomByNumber(final int roomNumber) {
        final String url = BASE_URL + roomNumber;
        restTemplate.delete(url);
    }

    public static void main(final String[] args) {
        RoomAccess roomAccess = new RoomAccess();
        List<Room> rooms = roomAccess.getAllRooms();

        if (rooms != null && !rooms.isEmpty()) {
            for (Room room : rooms) {
                System.out.println("Room Number: " + room.getRoomNumber());
                System.out.println("Room Capacity: " + room.getRoomCapacity());
                System.out.println("Price Per Night: " + room.getPricePerNight());
                System.out.println();
            }
        } else {
            System.out.println("No rooms found.");
        }
    }
}
