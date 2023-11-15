package booking.fxui.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import booking.core.Booking;
import booking.core.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A deserializer for the {@link User} class.
 *
 * Uses the {@link BookingDeserializer} to deserialize the bookings.
 */
public final class UserDeserializer extends JsonDeserializer<User> {

    /**
     * Default constructor for UserDeserializer.
     */
    public UserDeserializer() {
    }

    @Override
    public User deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        if (jsonNode instanceof ObjectNode objectNode) {

            JsonNode usernameNode = objectNode.get("username");
            JsonNode firstNameNode = objectNode.get("firstName");
            JsonNode lastNameNode = objectNode.get("lastName");
            JsonNode passwordNode = objectNode.get("password");
            JsonNode genderNode = objectNode.get("gender");
            JsonNode bookingsNode = objectNode.get("bookings");

            if (usernameNode.isNull() || !usernameNode.isTextual()
                    || firstNameNode.isNull() || !firstNameNode.isTextual() || lastNameNode.isNull()
                    || !lastNameNode.isTextual() || passwordNode.isNull() || !passwordNode.isTextual()
                    || genderNode.isNull() || !genderNode.isTextual() || !(bookingsNode instanceof ArrayNode)) {
                return null;
            }

            User user = new User();

            user.setUsername(usernameNode.asText());
            user.setFirstName(firstNameNode.asText());
            user.setLastName(lastNameNode.asText());
            user.setPassword(passwordNode.asText());
            user.setGender(genderNode.asText());
            ArrayNode bookingsArrayNode = (ArrayNode) bookingsNode;
            List<Booking> bookings = new ArrayList<>();
            BookingDeserializer bookingDeserializer = new BookingDeserializer();

            for (JsonNode bookingNode : bookingsArrayNode) {
                Booking booking = bookingDeserializer.deserialize(bookingNode.traverse(parser.getCodec()),
                        ctxt);
                if (booking != null) {
                    bookings.add(booking);
                }
            }

            user.setBookings(bookings);
            return user;
        }

        return null;
    }
}
