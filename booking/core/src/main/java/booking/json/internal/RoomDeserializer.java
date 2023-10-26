package booking.json.internal;

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

public final class RoomDeserializer extends JsonDeserializer<Room> {

    @Override
    public Room deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode treeNode = parser.getCodec().readTree(parser);
        return deserialize(treeNode);
    }

    Room deserialize(final JsonNode treeNode) {
        if (treeNode instanceof ObjectNode objectNode) {
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
                    Booking booking = bookingDeserializer.deserialize(bookingNode);
                    if (booking != null) {
                        bookings.add(booking);
                    }
                }

                room.setBookings(bookings);
                return room;
            }
        }

        return null;

        // Room room = new Room();
        // List<Booking> bookings = new ArrayList<>();

        // while (jsonParser.nextToken() != null) {
        // if (jsonParser.getCurrentName() == null) {
        // continue;
        // }
        // switch (jsonParser.getCurrentName()) {
        // case "roomNumber":
        // room.setRoomNumber(jsonParser.getIntValue());
        // break;
        // case "roomCapacity":
        // room.setRoomCapacity(jsonParser.getIntValue());
        // break;
        // case "pricePerNight":
        // room.setPricePerNight(jsonParser.getIntValue());
        // break;
        // case "bookings":
        // jsonParser.nextToken(); // Move to the start of the array
        // while (jsonParser.nextToken() != null) {
        // if (jsonParser.getCurrentToken() == JsonToken.END_ARRAY) {
        // break;
        // }
        // Booking booking = jsonParser.readValueAs(Booking.class);
        // bookings.add(booking);
        // }
        // break;
        // }
        // }
        // room.setBookings(bookings);
        // return room;
    }
}
