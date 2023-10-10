package booking.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import booking.core.Room;

public class ReadWrite {

    /**
     * The object mapper object for the file manager object.
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Write the given rooms to the given file.
     * @param rooms
     * @param fileName
     */
    public void writeToFile(final List<Room> rooms, final String fileName) {
        try {
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            Collections.sort(rooms, Comparator.comparingInt(Room::getRoomNumber));
            objectWriter.writeValue(new File(fileName), rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName Takes in a filepath.
     * @return The list found at the end of the filepath.
     */
    public List<Room> restoredListFromFile(final String fileName) {
        List<Room> rooms = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            TypeReference<List<Room>> typeReference = new TypeReference<List<Room>>() { };
            rooms = objectMapper.readValue(fileInputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }
}

