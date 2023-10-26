package booking.springboot.restserver;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * The Spring application.
 */
@SpringBootApplication
public class RoomApplication {

  // public RoomApplication() {
  // throw new IllegalStateException("This class should not be instantiated.");
  // }

  /**
   * The main method.
   *
   * @param args The command line arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(RoomApplication.class, args);
  }

  /**
   * A command line runner.
   *
   * @param ctx The application context
   *
   * @return A command line runner
   */
  @Bean
  public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
    return args -> {

      System.out.println("Let's inspect the beans provided by Spring Boot:");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        System.out.println(beanName);
      }

    };
  }

}
