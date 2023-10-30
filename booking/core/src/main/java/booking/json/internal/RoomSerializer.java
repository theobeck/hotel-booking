package booking.json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import booking.core.Booking;
import booking.core.Room;

/**
 * A serializer for the {@link Room} class.
 *
 * Uses the {@link BookingSerializer} to serialize the bookings.
 */
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
        BookingSerializer bookingSerializer = new BookingSerializer();
        for (Booking booking : room.getBookings()) {
            bookingSerializer.serialize(booking, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
