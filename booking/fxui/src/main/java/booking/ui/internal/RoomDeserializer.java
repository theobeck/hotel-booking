package booking.ui.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import booking.core.Booking;
import booking.core.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A deserializer for the {@link Room} class.
 *
 * Uses the {@link BookingDeserializer} to deserialize the bookings.
 */
public final class RoomDeserializer extends JsonDeserializer<Room> {

    @Override
    public Room deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        if (jsonNode instanceof ObjectNode objectNode) {
            Room room = new Room();

            JsonNode roomNumberNode = objectNode.get("roomNumber");
            JsonNode roomCapacityNode = objectNode.get("roomCapacity");
            JsonNode pricePerNightNode = objectNode.get("pricePerNight");
            JsonNode bookingsNode = objectNode.get("bookings");

            if (roomNumberNode != null && roomCapacityNode != null && pricePerNightNode != null
                    && bookingsNode instanceof ArrayNode) {
                room.setRoomNumber(roomNumberNode.asInt());
                room.setRoomCapacity(roomCapacityNode.asInt());
                room.setPricePerNight(pricePerNightNode.asInt());

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

                room.setBookings(bookings);
                return room;
            }
        }

        return null;
    }
}
