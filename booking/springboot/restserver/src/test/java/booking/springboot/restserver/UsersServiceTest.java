package booking.springboot.restserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import booking.core.Booking;
import booking.core.User;
import booking.ui.internal.BookingDeserializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { RestApplication.class, UsersController.class, UsersService.class })
@WebMvcTest
public class UsersServiceTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        objectMapper = UsersService.createObjectMapper();
    }

    private String usersUrl(String... segments) {
        return "/users/" + String.join("/", segments);
    }

    @Test
    public User testGetTestUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(usersUrl("test"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        User user = objectMapper.readValue(content, User.class);

        assertNotNull(result.getResponse().getContentType());
        assertEquals("test", user.getUsername());
        return user;
    }

    @Test
    public void testGetNullUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(usersUrl("nonexistent"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertNull(result.getResponse().getContentType());
    }

    @Test
    public void testBookDeleteCreateUnbookUser() throws Exception {
        Booking booking = new Booking("test", 1, LocalDate.now(), LocalDate.now().plusDays(3), 1800);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(usersUrl(String.valueOf(booking.getBookedBy()), "book",
                                String.valueOf(booking.getRoomNumber()),
                                String.valueOf(booking.getFrom()),
                                String.valueOf(booking.getTo()),
                                String.valueOf(booking.getTotalCostOfBooking())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(usersUrl(String.valueOf(booking.getBookedBy()), "bookings"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        ObjectMapper bookingMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Booking.class, new BookingDeserializer());
        bookingMapper.registerModule(module);
        List<Booking> bookings = bookingMapper.readValue(content, new TypeReference<List<Booking>>() {
        });
        assertTrue(bookings.get(0).isEqualTo(booking));
        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(usersUrl(String.valueOf(booking.getBookedBy())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        User user = objectMapper.readValue(content, User.class);

        assertNotNull(result.getResponse().getContentType());
        assertEquals("test", user.getUsername());

        result = mockMvc.perform(MockMvcRequestBuilders
                .post(usersUrl(user.getUsername(), user.getFirstName(), user.getLastName(),
                        user.getPassword(), user.getGender()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        if (user.getBookings().isEmpty()) {
            return;
        }
        for (Booking b : user.getBookings()) {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put(usersUrl(String.valueOf(b.getBookedBy()), "book",
                                    String.valueOf(b.getRoomNumber()),
                                    String.valueOf(b.getFrom()),
                                    String.valueOf(b.getTo()),
                                    String.valueOf(b.getTotalCostOfBooking())))
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        }
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(usersUrl(String.valueOf(booking.getBookedBy()), "cancel",
                                String.valueOf(booking.getRoomNumber()),
                                String.valueOf(booking.getFrom()),
                                String.valueOf(booking.getTo()),
                                String.valueOf(booking.getTotalCostOfBooking())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void testUpdateTestUser() throws Exception {
        User oldUser = testGetTestUser();
        assertFalse(oldUser.getFirstName().equals("TestFirstName"));

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(usersUrl(oldUser.getUsername(), "UpdatedFirstName",
                                oldUser.getLastName(),
                                oldUser.getPassword(), oldUser.getGender()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        User newUser = objectMapper.readValue(content, User.class);

        assertEquals(oldUser.getUsername(), newUser.getUsername());
        assertEquals("UpdatedFirstName", newUser.getFirstName());
        assertEquals(oldUser.getLastName(), newUser.getLastName());
        assertEquals(oldUser.getPassword(), newUser.getPassword());
        assertEquals(oldUser.getGender(), newUser.getGender());

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(usersUrl(oldUser.getUsername(), oldUser.getFirstName(),
                                oldUser.getLastName(),
                                oldUser.getPassword(), oldUser.getGender()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        content = result.getResponse().getContentAsString();
        newUser = objectMapper.readValue(content, User.class);
        assertEquals(oldUser.getUsername(), newUser.getUsername());
        assertEquals(oldUser.getFirstName(), newUser.getFirstName());
        assertEquals(oldUser.getLastName(), newUser.getLastName());
        assertEquals(oldUser.getPassword(), newUser.getPassword());
        assertEquals(oldUser.getGender(), newUser.getGender());
    }

    @Test
    public void testDeleteUser() throws Exception {
        // MvcResult result =
        // mockMvc.perform(MockMvcRequestBuilders.delete(usersUrl("username"))
        // .accept(MediaType.APPLICATION_JSON))
        // .andExpect(MockMvcResultMatchers.status().isOk())
        // .andReturn();

        // String content = result.getResponse().getContentAsString();
        // User deletedUser = objectMapper.readValue(content, User.class);

        // assertNotNull(deletedUser);
        // assertEquals("username", deletedUser.getUsername());
    }
}
