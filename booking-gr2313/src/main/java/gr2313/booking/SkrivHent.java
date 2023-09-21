package gr2313.booking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SkrivHent {

    public void skrivTilFil(List<Room> rooms, String filnavn) {
        try (FileOutputStream filStrom = new FileOutputStream(filnavn);
             ObjectOutputStream objektStrom = new ObjectOutputStream(filStrom)) {

            objektStrom.writeObject(rooms);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Room> gjenopprettListeFraFil(String filnavn) {
        List<Room> gjenopprettetListe = new ArrayList<>();

        try (FileInputStream filStrøm = new FileInputStream(filnavn);
             ObjectInputStream objektStrøm = new ObjectInputStream(filStrøm)) {

            gjenopprettetListe = (List<Room>) objektStrøm.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return gjenopprettetListe;
    }
}

