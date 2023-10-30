module booking.core {
    exports booking.core;
    exports booking.json;

    requires transitive com.fasterxml.jackson.databind;

    opens booking.core to com.fasterxml.jackson.databind;
    opens booking.json to com.fasterxml.jackson.databind;
}
