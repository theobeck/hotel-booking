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

    /**
     * @return Room number
     */
    public int getRoomNumber () {
        return roomNumber;
    }

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
     */
    public void setBookedFrom(LocalDateTime bookedFrom) {
        // #TODO - fix proper edge case check
        // if (bookedTo != null) {
        //     if (bookedTo.before(bookedFrom)) {
        //         throw new IllegalArgumentException("Cannot book in a negative range.");
        //     }
        // }
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
     */
    public void setBookedTo(LocalDateTime bookedTo) {
        // #TODO - fix proper edge case check
        // if (bookedFrom != null && bookedFrom.after(bookedFrom)) {
        //     throw new IllegalArgumentException("Cannot book in a negative range.");
        // }
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
    public boolean isAvailableOn (LocalDateTime targetDate) {
        return targetDate.isBefore(bookedFrom) || targetDate.isAfter(bookedTo);
    }

    /**
     * @return Whether or not room is booked
     */
    private boolean isBooked () {
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
