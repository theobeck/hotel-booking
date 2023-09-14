package gr2313.booking;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Room {
    private int roomNumber;
    // Kanskje endre roomCapacity til å være en samling av antall f.eks. singleBed, doubleBed, osv?
    private int roomCapacity;
    private GregorianCalendar bookedFrom;
    private GregorianCalendar bookedTo;
    private int pricePerNight;

    public Room (int roomNumber, int roomCapacity, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        bookedFrom = null;
        bookedTo = null;
    }

    public int getRoomNumber () {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
    
    public GregorianCalendar getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(GregorianCalendar bookedFrom) {
        // if (bookedTo != null) {
        //     if (bookedTo.before(bookedFrom)) {
        //         throw new IllegalArgumentException("Cannot book in a negative range.");
        //     }
        // }
        this.bookedFrom = bookedFrom;
    }

    public GregorianCalendar getBookedTo() {
        return bookedTo;
    }
    
    public void setBookedTo(GregorianCalendar bookedTo) {
        // if (bookedFrom != null && bookedFrom.after(bookedFrom)) {
        //     throw new IllegalArgumentException("Cannot book in a negative range.");
        // }
        this.bookedTo = bookedTo;
    }


    public void bookRoom (GregorianCalendar bookedFrom, GregorianCalendar bookedTo) {
        setBookedFrom(bookedFrom);
        setBookedTo(bookedTo);
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailableOn (GregorianCalendar targetDate) {
        return targetDate.before(bookedFrom) || targetDate.after(bookedTo);
    }

    private boolean isBooked () {
        return !(bookedTo == null && bookedFrom == null);
    }

    public int totalCostOfBooking () {
        if (!isBooked()) {
            throw new IllegalStateException("Cannot check booking cost when room isn't booked.");
        }
        
        Date date1Date = getBookedFrom().getTime();
        Date date2Date = getBookedTo().getTime();

        long timeDifferenceInMillis = date2Date.getTime() - date1Date.getTime();
        long daysBetween = TimeUnit.DAYS.convert(timeDifferenceInMillis, TimeUnit.MILLISECONDS);
        return pricePerNight * ((int)daysBetween);
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " has a capacity of " + roomCapacity + " and costs " + pricePerNight
                + " per night.";
    }

    public static void main(String[] args) {
        Room r1 = new Room(1, 1, 10);
        GregorianCalendar from = new GregorianCalendar(123, 8, 12);
        GregorianCalendar to = new GregorianCalendar(123, 8, 14);
        GregorianCalendar test = new GregorianCalendar(123, 8, 15);
        r1.bookRoom(from, to);
        System.out.println(r1.isAvailableOn(test));
    }
}
