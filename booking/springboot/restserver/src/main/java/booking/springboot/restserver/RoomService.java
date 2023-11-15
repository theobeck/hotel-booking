package booking.springboot.restserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;

import booking.core.Booking;
import booking.core.Room;
import booking.core.User;
import booking.ui.internal.RoomDeserializer;
import booking.ui.internal.RoomSerializer;

@Service
public class RoomService {

    /**
     * The object mapper object for the file manager object.
     */
    private final ObjectMapper objectMapper;

    /**
     * The filepath bookings are saved to.
     */
    private static final String ROOMS_PATH = "src/main/resources/booking/springboot/restserver/rooms.json";

    /**
     * Random object used to generate random numbers.
     */
    public RoomService() {
        objectMapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Room.class, new RoomDeserializer());
        module.addSerializer(Room.class, new RoomSerializer());
        objectMapper.registerModule(module);
    }

    /**
     * Create a room.
     *
     * @param roomNumber    the room number of the room to create.
     * @param roomCapacity  the room capacity of the room to create.
     * @param pricePerNight the price per night of the room to create.
     */
    public void createRoom(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        final List<Room> rooms = getAllRooms();
        final Room room = new Room(roomNumber, roomCapacity, pricePerNight);
        rooms.add(room);
        updateRooms(rooms);
    }

    /**
     * Get all rooms.
     *
     * @return all rooms.
     */
    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(ROOMS_PATH)) {
            final TypeReference<List<Room>> typeReference = new TypeReference<List<Room>>() {
            };
            if (fileInputStream.available() == 0) {
                return rooms;
            }
            rooms = objectMapper.readValue(fileInputStream, typeReference);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    /**
     * Get a room by room number.
     *
     * @param roomNumber the room number of the room to get.
     *
     * @return the room with the given room number, or null if no room exists with
     *         that room number.
     */
    public Room getRoomByNumber(final int roomNumber) {
        final List<Room> rooms = getAllRooms();
        for (final Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    /**
     * Write the given rooms to the given file.
     *
     * @param rooms The rooms to store
     */
    private void updateRooms(final List<Room> rooms) {
        try {
            final ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            Collections.sort(rooms, Comparator.comparingInt(Room::getRoomNumber));
            objectWriter.writeValue(new File(ROOMS_PATH), rooms);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update a room.
     *
     * @param room the room to update.
     */
    private void updateOneRoom(final Room room) {
        final List<Room> rooms = getAllRooms();
        Room roomToRemove = new Room();
        for (final Room r : rooms) {
            if (r.getRoomNumber() == room.getRoomNumber()) {
                roomToRemove = r;
            }
        }
        rooms.remove(roomToRemove);
        rooms.add(room);
        updateRooms(rooms);
    }

    /**
     * Update a room by room number.
     *
     * @param roomNumber the room number of the room to update.
     * @param from       the start date of the booking.
     * @param to         the end date of the booking.
     * @param username   the username of the user booking the room.
     */
    public void bookRoomByNumber(final int roomNumber, final LocalDate from, final LocalDate to,
            final String username) {
        final Room room = getRoomByNumber(roomNumber);
        final UsersService usersService = new UsersService();
        final User user = usersService.getUserByUsername(username);
        room.bookRoom(from, to, user);
        updateOneRoom(room);
    }

    /**
     * Unbook a room by room number.
     *
     * @param booking the booking to cancel.
     */
    public void cancelBooking(final Booking booking) {
        final Room room = getRoomByNumber(booking.getRoomNumber());
        Booking bookingToCancel = new Booking();
        for (final Booking b : room.getBookings()) {
            if (b.isEqualTo(booking)) {
                bookingToCancel = b;
            }
        }
        room.cancelBooking(bookingToCancel);
        updateOneRoom(room);
    }

    /**
     * Delete a room by room number.
     *
     * @param roomNumber the room number of the room to delete.
     */
    public void deleteRoomByNumber(final int roomNumber) {
        final List<Room> rooms = getAllRooms();
        Room roomToUpdate = new Room();
        for (final Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                roomToUpdate = room;
            }
        }
        rooms.remove(roomToUpdate);
        updateRooms(rooms);
    }

    public void updateRoomByNumber(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        final List<Room> rooms = getAllRooms();
        for (final Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setRoomCapacity(roomCapacity);
                room.setPricePerNight(pricePerNight);
                updateRooms(rooms);
            }
        }
    }
}
