package booking.springboot.restserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@SpringBootTest(classes = RestApplication.class)
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        this.mockMvc.perform(post("/users/rest/Resty/Resterson/123/Non-binary"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        this.mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        this.mockMvc.perform(get("/users/rest")).andExpect(status().isOk());
    }

    @Test
    public void testGetBookingsByUsername() throws Exception {
        this.mockMvc.perform(get("/users/rest/bookings")).andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserByUsername() throws Exception {
        this.mockMvc.perform(put("/users/rest/Resty/Resterson/123/Non-binary"))
                .andExpect(status().isOk());
    }

    @Test
    public void testBookRoomByUsername() throws Exception {
        this.mockMvc.perform(put("/users/rest/book/1/2021-05-05/2021-05-06/600"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelBooking() throws Exception {
        this.mockMvc.perform(put("/users/rest/cancel/1/2021-05-05/2021-05-06/600"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserByUsername() throws Exception {
        this.mockMvc.perform(delete("/users/rest"))
                .andExpect(status().isOk());
    }
}
