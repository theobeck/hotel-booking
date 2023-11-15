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

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { RestApplication.class, RoomController.class, RoomService.class })
@WebMvcTest
public class RoomServiceTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        objectMapper = RoomService.createObjectMapper();
    }

    private String roomsUrl(String... segments) {
        return "/rooms/" + String.join("/", segments);
    }

    @Test
    public Room testGetRoomByNumberShouldReturnRoomOne() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(roomsUrl("1"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Room room = objectMapper.readValue(content, Room.class);

        assertNotNull(result.getResponse().getContentType());
        assertEquals(1, room.getRoomNumber());
        return room;
    }

    @Test
    public void testGetRoomByNumberShouldReturnNull() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(roomsUrl("0"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertNull(result.getResponse().getContentType());
    }

    @Test
    public void testBookDeleteCreateUnbookRoom() throws Exception {
        Booking booking = new Booking("test", 1, LocalDate.now(), LocalDate.now().plusDays(3), 1800);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(roomsUrl(String.valueOf(booking.getRoomNumber()), "book",
                                String.valueOf(booking.getFrom()),
                                String.valueOf(booking.getTo()),
                                booking.getBookedBy()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        result = mockMvc.perform(MockMvcRequestBuilders.delete(roomsUrl("1"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Room room = objectMapper.readValue(content, Room.class);

        assertNotNull(result.getResponse().getContentType());
        assertEquals(1, room.getRoomNumber());

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(roomsUrl(String.valueOf(room.getRoomNumber()),
                                String.valueOf(room.getRoomCapacity()),
                                String.valueOf(room.getPricePerNight())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        if (room.getBookings().isEmpty()) {
            return;
        }
        for (Booking b : room.getBookings()) {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put(roomsUrl(String.valueOf(b.getRoomNumber()), "book",
                                    String.valueOf(b.getFrom()),
                                    String.valueOf(b.getTo()),
                                    b.getBookedBy()))
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        }

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(roomsUrl(String.valueOf(booking.getRoomNumber()), "cancel",
                                booking.getBookedBy(),
                                String.valueOf(booking.getFrom()),
                                String.valueOf(booking.getTo()),
                                String.valueOf(booking.getTotalCostOfBooking())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateRoomOne() throws Exception {
        Room room = testGetRoomByNumberShouldReturnRoomOne();
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(roomsUrl(String.valueOf(room.getRoomNumber()), "update",
                                String.valueOf(room.getRoomCapacity() + 1),
                                String.valueOf(room.getPricePerNight())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Room updatedRoom = objectMapper.readValue(content, Room.class);
        assertEquals(room.getRoomNumber(), updatedRoom.getRoomNumber());
        assertEquals(room.getRoomCapacity() + 1, updatedRoom.getRoomCapacity());
        assertEquals(room.getPricePerNight(), updatedRoom.getPricePerNight());
        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(roomsUrl(String.valueOf(room.getRoomNumber()), "update",
                                String.valueOf(room.getRoomCapacity()),
                                String.valueOf(room.getPricePerNight())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        content = result.getResponse().getContentAsString();
        updatedRoom = objectMapper.readValue(content, Room.class);
        assertEquals(room.getRoomNumber(), updatedRoom.getRoomNumber());
        assertEquals(room.getRoomCapacity(), updatedRoom.getRoomCapacity());
        assertEquals(room.getPricePerNight(), updatedRoom.getPricePerNight());
    }

    @Test
    public void testUpdateRoomNull() throws Exception {
        Room room = testGetRoomByNumberShouldReturnRoomOne();
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(roomsUrl(String.valueOf(0), "update",
                                String.valueOf(room.getRoomCapacity() + 1),
                                String.valueOf(room.getPricePerNight())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertNull(result.getResponse().getContentType());
    }
}
