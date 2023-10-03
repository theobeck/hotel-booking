package gr2313.booking.core;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Room implements Serializable{
    private int roomNumber;
    // Kanskje endre roomCapacity til å være en samling av antall f.eks. singleBed, doubleBed, osv?
    private int roomCapacity;
    private int pricePerNight;

    private LocalDateTime bookedFrom;
    private LocalDateTime bookedTo;
    private String bookedBy;
    private boolean isBooked;

    public void setBooked(boolean isBooked){
        this.isBooked = isBooked;
    }

    public boolean getIsBooked(){
        return isBooked;
    }

    public Room (int roomNumber, int roomCapacity, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        bookedFrom = null;
        bookedTo = null;
    }

    /**
     * @return Room number
     */
    public int getRoomNumber () {
        return roomNumber;
    }

    //heisann

    /**
     * @param roomNumber Room number to change to
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return Room capacity
     */
    public int getRoomCapacity() {
        return roomCapacity;
    }

    /**
     * @param roomNumber Room capacity to change to
     */
    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
    
    /**
     * @return Time room is booked from
     */
    public LocalDateTime getBookedFrom() {
        return bookedFrom;
    }

    /**
     * @param bookedFrom Time booking starts
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedFrom(LocalDateTime bookedFrom) {
        if (bookedTo != null && bookedTo.isAfter(bookedFrom)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        this.bookedFrom = bookedFrom;
    }

    /**
     * @return Time room is booked to
     */
    public LocalDateTime getBookedTo() {
        return bookedTo;
    }
    
    /**
     * @param bookedTo Time booking ends
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedTo(LocalDateTime bookedTo) {
        if (bookedFrom != null && bookedFrom.isAfter(bookedTo)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        this.bookedTo = bookedTo;
    }


    /**
     * @param bookedFrom Time to start booking
     * @param bookedTo TIme to end booking
     */
    public void bookRoom (LocalDateTime bookedFrom, LocalDateTime bookedTo) {
        setBookedFrom(bookedFrom);
        setBookedTo(bookedTo);
    }

    /**
     * @return Price per night
     */
    public int getPricePerNight() {
        return pricePerNight;
    }

    /**
     * @param pricePerNight How much room will cost per night
     */
    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    /**
     * @param targetDate Date to check if available on
     * @return Whether or not it is available on the date given
     */
    // Dette er en funksjon for i fremtiden når vi evt kan la ett rom bli booket flere ganger, så lenge de forskjellige bookingene ikke skjer samtidig.
    public boolean isAvailableOn (LocalDateTime targetDate) {
        return targetDate.isBefore(bookedFrom) || targetDate.isAfter(bookedTo);
    }

    /**
     * @return Whether or not room is booked
     */
    public boolean isBooked () {
        return !(bookedTo == null && bookedFrom == null);
    }

    /**
     * @return Price of entire booking
     */
    public int totalCostOfBooking () {
        // FUCK DINNE FUNKSJONEN
        if (!isBooked()) {
            throw new IllegalStateException("Cannot check booking cost when room isn't booked.");
        }
        return (int) (pricePerNight * (ChronoUnit.DAYS.between(bookedFrom, bookedTo)));
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber + ", capacity: " + roomCapacity + ", price: " + pricePerNight
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
