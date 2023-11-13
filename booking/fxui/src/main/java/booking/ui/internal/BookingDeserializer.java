package booking.ui.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import booking.core.Booking;

import java.io.IOException;
import java.time.LocalDate;

/**
 * A deserializer for the {@link Booking} class.
 *
 * Used by the {@link RoomDeserializer} to deserialize its bookings.
 */
public final class BookingDeserializer extends JsonDeserializer<Booking> {

    @Override
    public Booking deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        if (jsonNode instanceof ObjectNode objectNode) {
            Booking booking = new Booking();

            JsonNode bookedByNode = objectNode.get("bookedBy");
            JsonNode roomNumberNode = objectNode.get("roomNumber");
            JsonNode fromNode = objectNode.get("from");
            JsonNode toNode = objectNode.get("to");

            if (bookedByNode != null && fromNode != null && toNode != null && roomNumberNode != null
                    && bookedByNode.isTextual() && fromNode.isTextual() && toNode.isTextual()
                    && roomNumberNode.isInt()) {
                String bookedBy = bookedByNode.asText();
                int roomNumber = roomNumberNode.asInt();
                LocalDate from = LocalDate.parse(fromNode.asText());
                LocalDate to = LocalDate.parse(toNode.asText());
                booking.setBookedBy(bookedBy);
                booking.setRoomNumber(roomNumber);
                booking.setFrom(from);
                booking.setTo(to);
            }

            return booking;
        }
        return null;
    }
}