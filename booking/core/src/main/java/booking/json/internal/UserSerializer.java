package booking.json.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import booking.core.User;

import java.io.IOException;

/**
 * A serializer for the {@link User} class.
 *
 * Used by the {@link UserSerializer} to serialize its users.
 */
public final class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(final User user, final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("username", user.getUsername());
        jsonGenerator.writeStringField("password", user.getPassword());
        jsonGenerator.writeEndObject();
    }
}
