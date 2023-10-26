package booking.json.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import booking.core.Booking;

import java.io.IOException;
import java.time.LocalDate;

public final class BookingDeserializer extends JsonDeserializer<Booking> {

    @Override
    public Booking deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        if (jsonNode instanceof ObjectNode objectNode) {
            Booking booking = new Booking();

            JsonNode bookedByNode = objectNode.get("bookedBy");
            JsonNode fromNode = objectNode.get("from");
            JsonNode toNode = objectNode.get("to");

            if (bookedByNode != null && bookedByNode.isTextual() && fromNode != null && fromNode.isTextual()
                    && toNode != null && toNode.isTextual()) {
                LocalDate from = LocalDate.parse(fromNode.asText());
                LocalDate to = LocalDate.parse(toNode.asText());
                booking.setBookedBy(bookedByNode.asText());
                booking.setFrom(from);
                booking.setTo(to);
            }

            return booking;
        }
        return null;
    }
}