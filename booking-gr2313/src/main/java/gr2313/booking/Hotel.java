package gr2313.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hotel {

    ReadWrite fileManager = new ReadWrite();

    List<Room> rooms = new ArrayList<>();
    int nmbRooms = 10;
    
    public Hotel(String filename){
        rooms = fileManager.restoredListFromFile(filename);
        if(rooms.isEmpty()){
            for(int i = 0; i<nmbRooms; i++){
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
