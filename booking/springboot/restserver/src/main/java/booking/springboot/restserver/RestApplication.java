package booking.springboot.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Spring application.
 */
@SpringBootApplication
public class RestApplication {

    /**
     * The main method.
     *
     * @param args The command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
