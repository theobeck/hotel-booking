package gr2313.booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Room {
    private int roomNumber;
    // Kanskje endre roomCapacity til å være en samling av antall f.eks. singleBed, doubleBed, osv?
    private int roomCapacity;
    private LocalDateTime bookedFrom;
    private LocalDateTime bookedTo;
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
    
    public LocalDateTime getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(LocalDateTime bookedFrom) {
        // #TODO - fix proper edge case check
        // if (bookedTo != null) {
        //     if (bookedTo.before(bookedFrom)) {
        //         throw new IllegalArgumentException("Cannot book in a negative range.");
        //     }
        // }
        this.bookedFrom = bookedFrom;
    }

    public LocalDateTime getBookedTo() {
        return bookedTo;
    }
    
    public void setBookedTo(LocalDateTime bookedTo) {
        // #TODO - fix proper edge case check
        // if (bookedFrom != null && bookedFrom.after(bookedFrom)) {
        //     throw new IllegalArgumentException("Cannot book in a negative range.");
        // }
        this.bookedTo = bookedTo;
    }


    public void bookRoom (LocalDateTime bookedFrom, LocalDateTime bookedTo) {
        setBookedFrom(bookedFrom);
        setBookedTo(bookedTo);
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailableOn (LocalDateTime targetDate) {
        return targetDate.isBefore(bookedFrom) || targetDate.isAfter(bookedTo);
    }

    private boolean isBooked () {
        return !(bookedTo == null && bookedFrom == null);
    }

    public int totalCostOfBooking () {
        if (!isBooked()) {
            throw new IllegalStateException("Cannot check booking cost when room isn't booked.");
        }

        return (int) (pricePerNight * (ChronoUnit.DAYS.between(bookedFrom, bookedTo)));
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " has a capacity of " + roomCapacity + " and costs " + pricePerNight
                + " per night.";
    }

    public static void main(String[] args) {
        Room r1 = new Room(1, 1, 10);
        LocalDateTime from = LocalDateTime.of(2023, 9, 13, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 15, 0, 0, 0);
        LocalDateTime test = LocalDateTime.of(2023, 9, 14, 0, 0, 0);
        r1.bookRoom(from, to);
        System.out.println(r1.isAvailableOn(test));
        System.out.println(r1.totalCostOfBooking());
    }
}
