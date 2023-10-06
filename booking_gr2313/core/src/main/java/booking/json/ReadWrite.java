package booking.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import booking.core.Room;

public class ReadWrite {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param rooms
     * @param fileName
     */
    public void writeToFile(List<Room> rooms, String fileName) {
        try {
            objectMapper.writeValue(new File(fileName), rooms);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName
     * @return
     */
    public List<Room> restoredListFromFile(String fileName) {
        List<Room> restoredList = new ArrayList<>();

        try {

            restoredList = objectMapper.readValue(new File(fileName), new TypeReference<List<Room>>(){});
            System.out.println("restoredList" + restoredList);

        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return restoredList;
    }
}

