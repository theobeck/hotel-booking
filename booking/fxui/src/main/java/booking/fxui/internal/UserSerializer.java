package booking.fxui.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import booking.core.Booking;
import booking.core.User;

import java.io.IOException;

/**
 * A serializer for the {@link User} class.
 *
 * Uses the {@link BookingSerializer} to serialize the bookings.
 */
public final class UserSerializer extends JsonSerializer<User> {

    /**
     * Default constructor for UserSerializer.
     */
    public UserSerializer() {
    }

    @Override
    public void serialize(final User user, final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("username", user.getUsername());
        jsonGenerator.writeStringField("firstName", user.getFirstName());
        jsonGenerator.writeStringField("lastName", user.getLastName());
        jsonGenerator.writeStringField("password", user.getPassword());
        jsonGenerator.writeStringField("gender", user.getGender());
        jsonGenerator.writeArrayFieldStart("bookings");
        BookingSerializer bookingSerializer = new BookingSerializer();
        for (Booking booking : user.getBookings()) {
            bookingSerializer.serialize(booking, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
