package gr2313.booking;

import java.io.*;

public class skriveObjekt {

    static BookingController bookControl = new BookingController();

    public static void main(String[] args) {
        try (FileOutputStream filstrom = new FileOutputStream("rom.ser"); ObjectOutputStream objektStrom = new ObjectOutputStream(filstrom)){
            objektStrom.writeObject(bookControl.getRooms());
            System.out.println("Objektet har blitt skrevet til fil");
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
