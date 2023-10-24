package booking.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
     * Create a new file manager object.
     */
    public ReadWrite() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Write the given rooms to the given file.
     *
     * @param rooms
     * @param filePath
     */
    public void writeToFile(final List<Room> rooms, final String filePath) {
        try {
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            Collections.sort(rooms, Comparator.comparingInt(Room::getRoomNumber));
            objectWriter.writeValue(new File(filePath), rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filePath Takes in a filepath.
     * @return The list found at the end of the filepath.
     */
    public List<Room> restoredListFromFile(final String filePath) {
        List<Room> rooms = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            TypeReference<List<Room>> typeReference = new TypeReference<List<Room>>() {
            };
            rooms = objectMapper.readValue(fileInputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }
}
