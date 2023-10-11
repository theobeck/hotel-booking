package booking.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Room {
    /**
     * The room's number.
     */
    @JsonProperty("roomNumber")
    private int roomNumber;

    // Kanskje endre roomCapacity til å være en samling av antall f.eks. singleBed, doubleBed, osv?
    /**
     * The room's capacity.
     */
    @JsonProperty("roomCapacity")
    private int roomCapacity;

    /**
     * How much it costs per night to book the room.
     */
    @JsonProperty("pricePerNight")
    private int pricePerNight;


    /**
     * The time the room's current booking starts.
     */
    @JsonProperty("bookedFrom")
    private LocalDateTime bookedFrom;

    /**
     * The time the room's current booking ends.
     */
    @JsonProperty("bookedTo")
    private LocalDateTime bookedTo;

    /**
     * Whether the room is booked or not.
     */
    @JsonProperty("isBooked")
    private boolean isBooked;

    /**
     * Empty constructor for creating a Room object.
     */
    public Room() {
    }

    /**
     * Create a new room object with the following variables defined.
     * @param newRoomNumber
     * @param newRoomCapacity
     * @param newPricePerNight
     */
    public Room(final int newRoomNumber, final int newRoomCapacity, final int newPricePerNight) {
        roomNumber = newRoomNumber;
        roomCapacity = newRoomCapacity;
        pricePerNight = newPricePerNight;
        bookedFrom = null;
        bookedTo = null;
    }

    /**
     * @return Room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param newRoomNumber Room number to change to
     */
    public void setRoomNumber(final int newRoomNumber) {
        roomNumber = newRoomNumber;
    }

    /**
     * @return Room capacity
     */
    public int getRoomCapacity() {
        return roomCapacity;
    }

    /**
     * @param newRoomCapacity Room capacity to change to.
     */
    public void setRoomCapacity(final int newRoomCapacity) {
        roomCapacity = newRoomCapacity;
    }

    /**
     * @return Time room is booked from
     */
    public LocalDateTime getBookedFrom() {
        return bookedFrom;
    }

    /**
     * @param newBookedFrom Time booking starts
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedFrom(final LocalDateTime newBookedFrom) {
        if (bookedTo != null && bookedTo.isAfter(newBookedFrom)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        bookedFrom = newBookedFrom;
    }

    /**
     * @return Time room is booked to
     */
    public LocalDateTime getBookedTo() {
        return bookedTo;
    }

    /**
     * @param newBookedTo Time booking ends
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedTo(final LocalDateTime newBookedTo) {
        if (bookedFrom != null && bookedFrom.isAfter(newBookedTo)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        bookedTo = newBookedTo;
    }


    /**
     * @param newBookedFrom Time to start booking
     * @param newBookedTo TIme to end booking
     */
    public void bookRoom(final LocalDateTime newBookedFrom, final LocalDateTime newBookedTo) {
        setBookedFrom(newBookedFrom);
        setBookedTo(newBookedTo);
    }

    /**
     * @return Price per night
     */
    public int getPricePerNight() {
        return pricePerNight;
    }

    /**
     * @param newPricePerNight How much room will cost per night
     */
    public void setPricePerNight(final int newPricePerNight) {
        pricePerNight = newPricePerNight;
    }

    /**
     * @param newIsBooked Update isBooked state
     */
    public void setBooked(final boolean newIsBooked) {
        isBooked = newIsBooked;
    }

    /**
     * @return isBooked state
     */
    public boolean getIsBooked() {
        return isBooked;
    }

    /**
     * @param targetDate Date to check if available on
     * @return Whether or not it is available on the date given
     */
    // Dette er en funksjon for i fremtiden når vi evt kan la ett rom bli booket flere ganger, så lenge de forskjellige bookingene ikke skjer samtidig.
    public boolean isAvailableOn(final LocalDateTime targetDate) {
        return targetDate.isBefore(bookedFrom) || targetDate.isAfter(bookedTo);
    }

    // /**
    //  * @return Whether or not room is booked
    //  */
    // public boolean isBooked () {
    //     return !(bookedTo == null && bookedFrom == null);
    // }

    /**
     * @return Price of entire booking
     */
    public int totalCostOfBooking() {
        // FUCK DINNE FUNKSJONEN
        if ((bookedTo == null && bookedFrom == null)) {
            throw new IllegalStateException("Cannot check booking cost when room isn't booked.");
        }
        return (int) (pricePerNight * (ChronoUnit.DAYS.between(bookedFrom, bookedTo)));
    }


    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Room number: " + roomNumber + ", capacity: " + roomCapacity + ", price: " + pricePerNight + " per night. isBooked: " + isBooked;
    }
}
