package gr2313.booking;

import java.io.*;
import java.util.ArrayList;

public class gjenoppretteObjekt {
    public static void main(String[] args) {
        ArrayList<Room> objektListe = new ArrayList<>();

        try (FileInputStream filStrøm = new FileInputStream("objekt_fil.ser");
             ObjectInputStream objektStrøm = new ObjectInputStream(filStrøm)) {

            while (true) {
                try {
                    Room objekt = (Room) objektStrøm.readObject();
                    objektListe.add(objekt);
                } catch (EOFException e) {
                    break; // Slutt på filen
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

