package booking.springboot.restserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BookingApplication.class)
@AutoConfigureMockMvc
public class BookingApplicationTest {
    // @Autowired
    // private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        // mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
        // .andExpect(status().isOk())
        // .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }
}
