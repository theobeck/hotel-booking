package booking.json.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import booking.core.Booking;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public final class BookingSerializer extends JsonSerializer<Booking> {

    @Override
    public void serialize(final Booking booking, final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("bookedBy", booking.getBookedBy());
        jsonGenerator.writeStringField("from", booking.getFrom().format(DateTimeFormatter.ISO_LOCAL_DATE));
        jsonGenerator.writeStringField("to", booking.getTo().format(DateTimeFormatter.ISO_LOCAL_DATE));
        jsonGenerator.writeEndObject();
    }
}
