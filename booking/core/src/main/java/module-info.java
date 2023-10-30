module booking.core {
    exports booking.core;

    requires transitive com.fasterxml.jackson.databind;

    opens booking.core to com.fasterxml.jackson.databind;
}
