package booking.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import booking.core.Booking;
import booking.core.Room;

public final class RoomSerializer extends JsonSerializer<Room> {

    @Override
    public void serialize(final Room room, final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("roomNumber", room.getRoomNumber());
        jsonGenerator.writeNumberField("roomCapacity", room.getRoomCapacity());
        jsonGenerator.writeNumberField("pricePerNight", room.getPricePerNight());
        jsonGenerator.writeArrayFieldStart("bookings");
        for (Booking booking : room.getBookings()) {
            jsonGenerator.writeObject(booking);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
