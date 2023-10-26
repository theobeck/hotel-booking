package booking.json.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import booking.core.Booking;

import java.io.IOException;
import java.time.LocalDate;

public final class BookingDeserializer extends JsonDeserializer<Booking> {

    @Override
    public Booking deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode treeNode = parser.getCodec().readTree(parser);
        return deserialize(treeNode);
    }

    Booking deserialize(final JsonNode jsonNode) {
        Booking booking = new Booking();

        JsonNode bookedByNode = jsonNode.get("bookedBy");
        if (bookedByNode != null && bookedByNode.isTextual()) {
            booking.setBookedBy(bookedByNode.asText());
        }

        JsonNode fromNode = jsonNode.get("from");
        if (fromNode != null && fromNode.isTextual()) {
            LocalDate from = LocalDate.parse(fromNode.asText());
            booking.setFrom(from);
        }

        JsonNode toNode = jsonNode.get("to");
        if (toNode != null && toNode.isTextual()) {
            LocalDate to = LocalDate.parse(toNode.asText());
            booking.setTo(to);
        }

        return booking;
    }
}