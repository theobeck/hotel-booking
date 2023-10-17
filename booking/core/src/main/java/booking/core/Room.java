package booking.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private LocalDate bookedFrom;

    /**
     * The time the room's current booking ends.
     */
    @JsonProperty("bookedTo")
    private LocalDate bookedTo;

    /**
     * Create a new room object with no variables defined.
     */
    public Room() { }

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
     * Create a new room object with the following variables defined.
     * @param newRoomNumber
     * @param newRoomCapacity
     * @param newPricePerNight
     * @param newBookedFrom
     * @param newBookedTo
     */
    public Room(final int newRoomNumber, final int newRoomCapacity, final int newPricePerNight, final LocalDate newBookedFrom, final LocalDate newBookedTo) {
        roomNumber = newRoomNumber;
        roomCapacity = newRoomCapacity;
        pricePerNight = newPricePerNight;
        bookedFrom = newBookedFrom;
        bookedTo = newBookedTo;
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
    public LocalDate getBookedFrom() {
        return bookedFrom;
    }

    /**
     * @param newBookedFrom Time booking starts
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedFrom(final LocalDate newBookedFrom) {
        if (bookedTo != null && bookedTo.isAfter(newBookedFrom)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        bookedFrom = newBookedFrom;
    }

    /**
     * @return Time room is booked to
     */
    public LocalDate getBookedTo() {
        return bookedTo;
    }

    /**
     * @param newBookedTo Time booking ends
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedTo(final LocalDate newBookedTo) {
        if (bookedFrom != null && bookedFrom.isAfter(newBookedTo)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        bookedTo = newBookedTo;
    }


    /**
     * @param newBookedFrom Time to start booking
     * @param newBookedTo TIme to end booking
     */
    public void bookRoom(final LocalDate newBookedFrom, final LocalDate newBookedTo) {
        setBookedFrom(newBookedFrom);
        setBookedTo(newBookedTo);
    }

    /**
     *  Remove booking from room.
     */
    public void removeBooking() {
        bookedFrom = null;
        bookedTo = null;
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
     * @param targetFrom Time to start booking
     * @param targetTo Time to end booking
     * @return Whether or not room is available between given times
     */
    public boolean isAvailableBetween(final LocalDate targetFrom, final LocalDate targetTo) {
        return isBooked();

        // !! Dette er kode for når vi skal implementere flere bookings for samme rom. !!
        // if (!isBooked()) {
        //     return true;
        // }
        // return targetFrom.isBefore(bookedFrom) && targetTo.isBefore(bookedTo) || targetFrom.isAfter(bookedFrom) && targetTo.isAfter(bookedTo);
    }

    /**
     * @return Whether or not room is booked
     */
    @JsonIgnore
    public boolean isBooked() {
        return bookedTo != null || bookedFrom != null;
    }

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
        return "Room number: " + roomNumber + ", capacity: " + roomCapacity + ", price: " + pricePerNight + " per night.";
    }
}
