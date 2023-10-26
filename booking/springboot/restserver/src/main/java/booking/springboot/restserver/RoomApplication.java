package booking.springboot.restserver;

import com.fasterxml.jackson.databind.Module;
import java.util.EnumSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import booking.json.ReadWrite;
import booking.json.ReadWrite.RoomParts;

/**
 * The Spring application.
 */
@SpringBootApplication
public class RoomApplication {

  @Bean
  public Module objectMapperModule() {
    return ReadWrite.createJacksonModule(EnumSet.of(RoomParts.ROOMS));
  }

  public static void main(String[] args) {
    SpringApplication.run(RoomApplication.class, args);
  }
}
