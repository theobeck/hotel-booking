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

import booking.core.Room;
import booking.core.User;
import booking.springboot.restserver.internal.RoomDeserializer;
import booking.springboot.restserver.internal.RoomSerializer;
import booking.springboot.restserver.internal.UserDeserializer;
import booking.springboot.restserver.internal.UserSerializer;

@Service
public final class RoomService {

    /**
     * The object mapper object for the file manager object.
     */
    private ObjectMapper objectMapper;

    /**
     * The filepath bookings are saved to.
     */
    private String bookingPath;

    /**
     * Random object used to generate random numbers.
     */
    public RoomService() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Room.class, new RoomDeserializer());
        module.addSerializer(Room.class, new RoomSerializer());
        module.addDeserializer(User.class, new UserDeserializer());
        module.addSerializer(User.class, new UserSerializer());
        objectMapper.registerModule(module);
        bookingPath = "src/main/resources/booking/springboot/restserver/bookings.json";
    }

    /**
     * Create a room.
     *
     * @param roomNumber    the room number of the room to create.
     * @param roomCapacity  the room capacity of the room to create.
     * @param pricePerNight the price per night of the room to create.
     */
    public void createRoom(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        List<Room> rooms = getAllRooms();
        Room room = new Room(roomNumber, roomCapacity, pricePerNight);
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

        try (FileInputStream fileInputStream = new FileInputStream(bookingPath)) {
            TypeReference<List<Room>> typeReference = new TypeReference<List<Room>>() {
            };
            rooms = objectMapper.readValue(fileInputStream, typeReference);
        } catch (IOException e) {
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
        List<Room> rooms = getAllRooms();
        for (Room room : rooms) {
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
    public void updateRooms(final List<Room> rooms) {
        try {
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            Collections.sort(rooms, Comparator.comparingInt(Room::getRoomNumber));
            objectWriter.writeValue(new File(bookingPath), rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        List<Room> rooms = getAllRooms();

        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.bookRoom(from, to, username);
                updateRooms(rooms);
            }
        }
    }

    /**
     * Unbook a room by room number.
     *
     * @param roomNumber the room number of the room to unbook.
     * @param username   the username of the user unbooking the room.
     */
    public void cancelBooking(final int roomNumber, final String username) {
        List<Room> rooms = getAllRooms();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.cancelBooking(username);
                updateRooms(rooms);
            }
        }
    }

    /**
     * Delete a room by room number.
     *
     * @param roomNumber the room number of the room to delete.
     */
    public void deleteRoomByNumber(final int roomNumber) {
        List<Room> rooms = getAllRooms();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                rooms.remove(room);
                updateRooms(rooms);
            }
        }
    }
}
