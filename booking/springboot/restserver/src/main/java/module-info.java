module booking.springboot.restserver {
    exports booking.springboot.restserver;

    requires transitive com.fasterxml.jackson.databind;
    requires transitive booking.core;
    requires booking.ui;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.core;

    opens booking.springboot.restserver
            to spring.beans, spring.context, spring.web, spring.core, com.fasterxml.jackson.databind;
}
