package booking.springboot.restserver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import booking.core.Room;
import booking.core.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoom = roomRepository.findById(id).orElse(null);
        if (existingRoom != null) {
            existingRoom.setName(room.getName());
            existingRoom.setDescription(room.getDescription());
            existingRoom.setCapacity(room.getCapacity());
            existingRoom.setPrice(room.getPrice());
            return roomRepository.save(existingRoom);
        }
        return null;
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
    
    
}
