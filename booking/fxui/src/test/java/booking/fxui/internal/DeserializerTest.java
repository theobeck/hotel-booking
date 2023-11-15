package booking.fxui.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import booking.core.Booking;
import booking.core.Room;
import booking.core.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DeserializerTest {
    JsonParser parser;
    DeserializationContext ctxt;
    ObjectMapper mapper;
    JsonNode rootNode;

    @BeforeEach
    public void setUp() {
        parser = Mockito.mock(JsonParser.class);
        ctxt = Mockito.mock(DeserializationContext.class);
        mapper = new ObjectMapper();
        rootNode = mapper.createObjectNode();
    }

    @Test
    public void testUserDeserialization() throws IOException {
        String username = "john_doe";
        String firstName = "John";
        String lastName = "Doe";
        String password = "password123";
        String gender = "Male";

        ((ObjectNode) rootNode).put("username", username);
        ((ObjectNode) rootNode).put("firstName", firstName);
        ((ObjectNode) rootNode).put("lastName", lastName);
        ((ObjectNode) rootNode).put("password", password);
        ((ObjectNode) rootNode).put("gender", gender);

        ArrayNode bookingsNode = mapper.createArrayNode();
        ((ObjectNode) rootNode).set("bookings", bookingsNode);

        ObjectCodec codec = Mockito.mock(ObjectCodec.class);
        when(parser.getCodec()).thenReturn(codec);

        when(parser.getCodec().readTree(parser)).thenReturn(rootNode);

        UserDeserializer userDeserializer = new UserDeserializer();
        User user = userDeserializer.deserialize(parser, ctxt);

        assertEquals(username, user.getUsername());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(password, user.getPassword());
        assertEquals(gender, user.getGender());
        assertEquals(List.of(), user.getBookings());
        assertTrue(user instanceof User);

        ((ObjectNode) rootNode).putNull("username");
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("username", 'a');
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("username", username);

        ((ObjectNode) rootNode).putNull("firstName");
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("firstName", 'a');
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("firstName", firstName);

        ((ObjectNode) rootNode).putNull("lastName");
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("lastName", 'a');
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("lastName", lastName);

        ((ObjectNode) rootNode).putNull("password");
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("password", 'a');
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("password", password);

        ((ObjectNode) rootNode).putNull("gender");
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("gender", 'a');
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("gender", gender);

        ((ObjectNode) rootNode).putNull("bookings");
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        ((ObjectNode) rootNode).put("bookings", 'a');
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
        bookingsNode.add(mapper.createObjectNode().put("bookedBy", "John Doe").put("roomNumber", 101)
                .put("from", "2021-01-01").put("to", "2021-01-03").put("totalCostOfBooking", 300));
        ((ObjectNode) rootNode).set("bookings", bookingsNode);

        user = userDeserializer.deserialize(parser, ctxt);

        assertEquals(username, user.getUsername());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(password, user.getPassword());
        assertEquals(gender, user.getGender());
        assertEquals(List.of(), user.getBookings());
        assertTrue(user instanceof User);

        when(parser.getCodec().readTree(parser)).thenReturn(null);
        user = userDeserializer.deserialize(parser, ctxt);
        assertNull(user);
    }

    @Test
    public void testRoomDeserialization() throws IOException {
        int roomNumber = 101;
        int roomCapacity = 4;
        int pricePerNight = 100;

        ((ObjectNode) rootNode).put("roomNumber", roomNumber);
        ((ObjectNode) rootNode).put("roomCapacity", roomCapacity);
        ((ObjectNode) rootNode).put("pricePerNight", pricePerNight);

        ArrayNode bookingsNode = mapper.createArrayNode();
        ((ObjectNode) rootNode).set("bookings", bookingsNode);

        ObjectCodec codec = Mockito.mock(ObjectCodec.class);
        when(parser.getCodec()).thenReturn(codec);

        when(parser.getCodec().readTree(parser)).thenReturn(rootNode);

        RoomDeserializer roomDeserializer = new RoomDeserializer();
        Room room = roomDeserializer.deserialize(parser, ctxt);

        assertEquals(roomNumber, room.getRoomNumber());
        assertEquals(roomCapacity, room.getRoomCapacity());
        assertEquals(pricePerNight, room.getPricePerNight());
        assertEquals(List.of(), room.getBookings());
        assertTrue(room instanceof Room);

        ((ObjectNode) rootNode).putNull("roomNumber");
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        ((ObjectNode) rootNode).put("roomNumber", "1");
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        ((ObjectNode) rootNode).put("roomNumber", roomNumber);

        ((ObjectNode) rootNode).putNull("roomCapacity");
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        ((ObjectNode) rootNode).put("roomCapacity", "1");
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        ((ObjectNode) rootNode).put("roomCapacity", roomCapacity);

        ((ObjectNode) rootNode).putNull("pricePerNight");
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        ((ObjectNode) rootNode).put("pricePerNight", "1");
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        ((ObjectNode) rootNode).put("pricePerNight", pricePerNight);

        ((ObjectNode) rootNode).putNull("bookings");
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        ((ObjectNode) rootNode).put("bookings", 1);
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
        bookingsNode.add(mapper.createObjectNode().put("bookedBy", "John Doe").put("roomNumber", 101)
                .put("from", "2021-01-01").put("to", "2021-01-03").put("totalCostOfBooking", 300));
        ((ObjectNode) rootNode).set("bookings", bookingsNode);

        room = roomDeserializer.deserialize(parser, ctxt);

        assertEquals(roomNumber, room.getRoomNumber());
        assertEquals(roomCapacity, room.getRoomCapacity());
        assertEquals(pricePerNight, room.getPricePerNight());
        assertEquals(List.of(), room.getBookings());
        assertTrue(room instanceof Room);

        when(parser.getCodec().readTree(parser)).thenReturn(null);
        room = roomDeserializer.deserialize(parser, ctxt);
        assertNull(room);
    }

    @Test
    public void testBookingDeserialization() throws IOException {
        String bookedBy = "John Doe";
        int roomNumber = 101;
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(3);
        int totalCost = 300;

        ((ObjectNode) rootNode).put("bookedBy", bookedBy);
        ((ObjectNode) rootNode).put("roomNumber", roomNumber);
        ((ObjectNode) rootNode).put("from", from.toString());
        ((ObjectNode) rootNode).put("to", to.toString());
        ((ObjectNode) rootNode).put("totalCostOfBooking", totalCost);

        ObjectCodec codec = Mockito.mock(ObjectCodec.class);
        when(parser.getCodec()).thenReturn(codec); // Mocking ObjectCodec

        when(parser.getCodec().readTree(parser)).thenReturn(rootNode);

        BookingDeserializer bookingDeserializer = new BookingDeserializer();
        Booking booking = bookingDeserializer.deserialize(parser, ctxt);

        assertNotNull(booking);
        assertEquals(bookedBy, booking.getBookedBy());
        assertEquals(roomNumber, booking.getRoomNumber());
        assertEquals(from, booking.getFrom());
        assertEquals(to, booking.getTo());
        assertEquals(totalCost, booking.getTotalCostOfBooking());
        assertTrue(booking instanceof Booking);

        ((ObjectNode) rootNode).putNull("bookedBy");
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("bookedBy", 123);
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("bookedBy", bookedBy);

        ((ObjectNode) rootNode).putNull("roomNumber");
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("roomNumber", "1");
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("roomNumber", roomNumber);

        ((ObjectNode) rootNode).putNull("from");
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("from", 1);
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("from", from.toString());

        ((ObjectNode) rootNode).putNull("to");
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("to", 1);
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("to", to.toString());

        ((ObjectNode) rootNode).putNull("totalCostOfBooking");
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("totalCostOfBooking", "1");
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
        ((ObjectNode) rootNode).put("totalCostOfBooking", totalCost);

        when(codec.readTree(parser)).thenReturn(null);
        booking = bookingDeserializer.deserialize(parser, ctxt);
        assertNull(booking);
    }
}