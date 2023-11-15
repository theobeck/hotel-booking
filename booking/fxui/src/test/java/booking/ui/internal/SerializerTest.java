package booking.fxui.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import booking.core.Booking;
import booking.core.Room;
import booking.core.User;
import booking.fxui.internal.RoomSerializer;
import booking.fxui.internal.UserSerializer;

public class SerializerTest {
    List<Booking> bookings;

    @BeforeEach
    public void setUp() {
        bookings = new ArrayList<>();
        Booking booking1 = new Booking("test", 1, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5), 50);
        Booking booking2 = new Booking("test", 1, LocalDate.of(2023, 11, 6), LocalDate.of(2023, 11, 10), 50);
        bookings.add(booking1);
        bookings.add(booking2);
    }

    @Test
    public void testUserSerialization() throws IOException {
        User user = new User("testUser", "John", "Doe", "password", "male", bookings);

        UserSerializer userSerializer = new UserSerializer();
        SerializerProvider serializerProvider = mock(SerializerProvider.class);

        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(stringWriter);

        userSerializer.serialize(user, jsonGenerator, serializerProvider);
        jsonGenerator.flush();

        String jsonString = stringWriter.toString();

        assertEquals(
                "{\"username\":\"testUser\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"password\":\"password\",\"gender\":\"male\",\"bookings\":[{\"bookedBy\":\"test\",\"roomNumber\":1,\"from\":\"2023-11-01\",\"to\":\"2023-11-05\",\"totalCostOfBooking\":50},{\"bookedBy\":\"test\",\"roomNumber\":1,\"from\":\"2023-11-06\",\"to\":\"2023-11-10\",\"totalCostOfBooking\":50}]}",
                jsonString);
    }

    @Test
    public void testRoomSerialization() throws IOException {
        RoomSerializer roomSerializer = new RoomSerializer();
        SerializerProvider serializerProvider = mock(SerializerProvider.class);

        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(stringWriter);

        Room room = new Room(1, 4, 100);
        room.setBookings(bookings);

        roomSerializer.serialize(room, jsonGenerator, serializerProvider);
        jsonGenerator.flush();

        String jsonString = stringWriter.toString();

        assertEquals(
                "{\"roomNumber\":1,\"roomCapacity\":4,\"pricePerNight\":100,\"bookings\":[{\"bookedBy\":\"test\",\"roomNumber\":1,\"from\":\"2023-11-01\",\"to\":\"2023-11-05\",\"totalCostOfBooking\":50},{\"bookedBy\":\"test\",\"roomNumber\":1,\"from\":\"2023-11-06\",\"to\":\"2023-11-10\",\"totalCostOfBooking\":50}]}",
                jsonString);
    }
}
