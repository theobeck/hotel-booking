package booking.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            Collections.sort(rooms, Comparator.comparingInt(Room::getRoomNumber));
            objectWriter.writeValue(new File(fileName), rooms);
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
        List<Room> rooms = new ArrayList<>();
        
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            TypeReference<List<Room>> typeReference = new TypeReference<List<Room>>() {};
            rooms = objectMapper.readValue(fileInputStream, typeReference);
            fileInputStream.close();
            System.out.println("restoredList" + rooms);

        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }
    public static void main(String[] args) {
        ReadWrite r = new ReadWrite();
        r.restoredListFromFile("booking_gr2313/fxui/src/main/resources/booking/ui/bookings.json");
    }
}

