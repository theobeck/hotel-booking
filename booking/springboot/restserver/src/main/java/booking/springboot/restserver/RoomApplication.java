package booking.springboot.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Spring application.
 */
@SpringBootApplication
public final class RoomApplication {

  private RoomApplication() {
    throw new IllegalStateException("This class should not be instantiated.");
  }

  /**
   * The main method.
   *
   * @param args the command line arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(RoomApplication.class, args);
  }
}
