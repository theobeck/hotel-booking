package gr2313.booking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite {

    public void writeToFile(List<Room> rooms, String fileName) {
        try (FileOutputStream fileStream = new FileOutputStream(fileName);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {

            objectStream.writeObject(rooms);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Room> restoredListFromFile(String fileName) {
        List<Room> restoredList = new ArrayList<>();

        try (FileInputStream fileStream = new FileInputStream(fileName);
             ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

            restoredList = (List<Room>) objectStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return restoredList;
    }
}

