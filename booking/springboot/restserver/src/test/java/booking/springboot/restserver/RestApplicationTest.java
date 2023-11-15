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

import com.fasterxml.jackson.databind.ObjectMapper;

import booking.core.Booking;
import booking.core.Room;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { RestApplication.class, RoomController.class, RoomService.class })
@WebMvcTest
public class RestApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        objectMapper = RoomService.createObjectMapper();
    }

    @Test
    public void testGetRoomByNumberShouldReturnRoomOne() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/rooms/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            String content = result.getResponse().getContentAsString();
            Room room = objectMapper.readValue(content, Room.class);

            assertNotNull(result.getResponse().getContentType());
            assertEquals(1, room.getRoomNumber());
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testGetRoomByNumberShouldReturnNull() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/rooms/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            assertNull(result.getResponse().getContentType());
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testDeleteAndCreateRoomByNumber() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/rooms/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            String content = result.getResponse().getContentAsString();
            Room room = objectMapper.readValue(content, Room.class);

            assertNotNull(result.getResponse().getContentType());
            assertEquals(1, room.getRoomNumber());

            result = mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/rooms/" + room.getRoomNumber() + "/" + room.getRoomCapacity() + "/"
                                    + room.getPricePerNight())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            if (room.getBookings().isEmpty()) {
                return;
            }
            for (Booking booking : room.getBookings()) {
                mockMvc
                        .perform(MockMvcRequestBuilders
                                .put("/rooms/" + room.getRoomNumber() + "/book/" + booking.getFrom() + "/"
                                        + booking.getTo() + "/" + booking.getBookedBy() + "")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
