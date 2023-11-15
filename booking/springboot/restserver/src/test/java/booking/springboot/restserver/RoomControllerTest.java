package booking.springboot.restserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import booking.core.Room;

@SpringBootTest(classes = RestApplication.class)
@AutoConfigureMockMvc
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    @Test
    public void testCreateRoom() throws Exception {
        this.mockMvc.perform(post("/rooms/1/8/600")).andExpect(status().isOk());
    }

    @Test
    public void testGetAllRooms() throws Exception {
        Room room = new Room(1, 8, 600);
        when(roomService.getAllRooms()).thenReturn(List.of(room));
        this.mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRoomByNumber() throws Exception {
        this.mockMvc.perform(get("/rooms/1")).andExpect(status().isOk());
    }

    @Test
    public void testUpdateRoomByNumber() throws Exception {
        this.mockMvc.perform(put("/rooms/1/update/8/600")).andExpect(status().isOk());
    }

    @Test
    public void testBookRoomByNumber() throws Exception {
        this.mockMvc.perform(put("/rooms/1/book/2021-05-01/2021-05-02/rest")).andExpect(status().isOk());
    }

    @Test
    public void testCancelBooking() throws Exception {
        this.mockMvc.perform(put("/rooms/1/cancel/rest/2021-05-01/2021-05-02/600")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteRoomByNumber() throws Exception {
        this.mockMvc.perform(delete("/rooms/1")).andExpect(status().isOk());
    }
}