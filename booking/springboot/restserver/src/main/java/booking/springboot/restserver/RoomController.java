package booking.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import booking.core.AbstractTodoList; må nok ha en abstract klasse
import booking.core.Room;

/**
 * The service implementation.
 */

@RestController
@RequestMapping(RoomController.ROOM_SERVICE_PATH)
public class RoomController {
    
}
