package booking.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import booking.json.ReadWrite;

public class Hotel {

    ReadWrite fileManager = new ReadWrite();

    List<Room> rooms = new ArrayList<>();
    int nmbRooms = 10;
    
    public Hotel(String filename){
        rooms = fileManager.restoredListFromFile(filename);
        System.out.println("hotel" + rooms);
        System.out.println(rooms.size());
        System.out.println(nmbRooms);
        List<Integer> occupiedRoomNumbers = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            occupiedRoomNumbers.add(rooms.get(i).getRoomNumber());
        }
        if(rooms.size() < nmbRooms){
            for(int i = 1; i<=nmbRooms; i++){
                if (occupiedRoomNumbers.contains(i)) {
                    continue;
                }
                Random random = new Random();
                int cap = random.nextInt(8)+1;
                Room room = new Room(i, cap, cap*750);
                rooms.add(room);
            }
            fileManager.writeToFile(rooms, filename);
        }
    }

    public List<Room> getRooms(){
        return rooms;
    }
}
