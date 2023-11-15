package booking.fxui.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import booking.core.Booking;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * A serializer for the {@link Booking} class.
 *
 * Used by the {@link RoomSerializer} and {@link UserSerializer} classes
 * to serialize their bookings.
 */
public final class BookingSerializer extends JsonSerializer<Booking> {

    /**
     * Default constructor for BookingSerializer.
     */
    public BookingSerializer() {
    }

    @Override
    public void serialize(final Booking booking, final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("bookedBy", booking.getBookedBy());
        jsonGenerator.writeNumberField("roomNumber", booking.getRoomNumber());
        jsonGenerator.writeStringField("from", booking.getFrom().format(DateTimeFormatter.ISO_LOCAL_DATE));
        jsonGenerator.writeStringField("to", booking.getTo().format(DateTimeFormatter.ISO_LOCAL_DATE));
        jsonGenerator.writeNumberField("totalCostOfBooking", booking.getTotalCostOfBooking());
        jsonGenerator.writeEndObject();
    }
}
