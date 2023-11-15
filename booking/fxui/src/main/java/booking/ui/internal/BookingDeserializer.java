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
 * Used by the {@link RoomDeserializer} and {@link UserDeserializer} classes
 * to deserialize their bookings.
 */
public final class BookingDeserializer extends JsonDeserializer<Booking> {

    /**
     * Default constructor for BookingDeserializer.
     */
    public BookingDeserializer() {
    }

    @Override
    public Booking deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        if (jsonNode instanceof ObjectNode objectNode) {

            JsonNode bookedByNode = objectNode.get("bookedBy");
            JsonNode roomNumberNode = objectNode.get("roomNumber");
            JsonNode fromNode = objectNode.get("from");
            JsonNode toNode = objectNode.get("to");
            JsonNode totalCostNode = objectNode.get("totalCostOfBooking");

            if (bookedByNode.isNull() || fromNode.isNull() || toNode.isNull() || roomNumberNode.isNull()
                    || totalCostNode.isNull()
                    || !bookedByNode.isTextual()
                    || !fromNode.isTextual()
                    || !toNode.isTextual()
                    || !roomNumberNode.isInt()
                    || !totalCostNode.isInt()) {
                return null;
            }

            Booking booking = new Booking();

            String bookedBy = bookedByNode.asText();
            int roomNumber = roomNumberNode.asInt();
            LocalDate from = LocalDate.parse(fromNode.asText());
            LocalDate to = LocalDate.parse(toNode.asText());
            int totalCost = totalCostNode.asInt();

            booking.setBookedBy(bookedBy);
            booking.setRoomNumber(roomNumber);
            booking.setFrom(from);
            booking.setTo(to);
            booking.setTotalCostOfBooking(totalCost);

            return booking;
        }
        return null;
    }
}
