package gr2313.booking;

import java.util.Date;

public class Room {
    private int roomNumber;
    // Kanskje endre roomCapacity til å være en samling av antall f.eks. singleBed, doubleBed, osv?
    private int roomCapacity;
    private Date bookedFrom;
    private Date bookedTo;
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

    public Date getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(Date bookedFrom) {
        if (bookedTo == null || bookedTo.before(bookedFrom)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        this.bookedFrom = bookedFrom;
    }

    public Date getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(Date bookedTo) {
        if (bookedFrom == null || bookedFrom.after(bookedFrom)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        this.bookedTo = bookedTo;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailableOn (Date targetDate) {
        return !(targetDate.after(bookedFrom) && targetDate.before(bookedTo));
    }

    private boolean isBooked () {
        return !(bookedTo == null && bookedFrom == null);
    }

    public int totalBookingCost () {
        if (!isBooked()) {
            throw new IllegalStateException("Cannot check booking price when room isn't booked.");
        }
        return pricePerNight * bookedFrom.compareTo(bookedTo);
    }

}
