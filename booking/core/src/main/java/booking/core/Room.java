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
     * The name of the person who booked the room.
     */
    @JsonProperty("bookedBy")
    private String bookedBy;

    /**
     * Create a new room object with no variables defined.
     */
    public Room() { }

    /**
     * Create a new room object with the following variables defined.
     * @param roomNumber
     * @param roomCapacity
     * @param pricePerNight
     */
    public Room(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        bookedFrom = null;
        bookedTo = null;
    }

    /**
     * Create a new room object with the following variables defined.
     * @param roomNumber
     * @param roomCapacity
     * @param pricePerNight
     * @param bookedFrom
     * @param bookedTo
     */
    public Room(final int roomNumber, final int roomCapacity, final int pricePerNight, final LocalDate bookedFrom, final LocalDate bookedTo) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
    }

    /**
     * @return Room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber Room number to change to
     */
    public void setRoomNumber(final int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return Room capacity
     */
    public int getRoomCapacity() {
        return roomCapacity;
    }

    /**
     * @param roomCapacity Room capacity to change to.
     */
    public void setRoomCapacity(final int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    /**
     * @return Time room is booked from
     */
    public LocalDate getBookedFrom() {
        return bookedFrom;
    }

    /**
     * @param bookedFrom Time booking starts
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedFrom(final LocalDate bookedFrom) {
        if (bookedTo != null && bookedTo.isBefore(bookedFrom)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        this.bookedFrom = bookedFrom;
    }

    /**
     * @return Time room is booked to
     */
    public LocalDate getBookedTo() {
        return bookedTo;
    }

    /**
     * @param bookedTo Time booking ends
     * @throws IllegalArgumentException when attempting to book in negative range
     */
    public void setBookedTo(final LocalDate bookedTo) {
        if (bookedFrom != null && bookedFrom.isAfter(bookedTo)) {
            throw new IllegalArgumentException("Cannot book in a negative range.");
        }
        this.bookedTo = bookedTo;
    }

    /**
     * @return Who the room is booked by
     */
    public String getBookedBy() {
        return bookedBy;
    }

    /**
     * @param bookedBy Who the room is booked by
     */
    public void setBookedBy(final String bookedBy) {
        this.bookedBy = bookedBy;
    }


    /**
     * @param bookedFrom Time to start booking
     * @param bookedTo TIme to end booking
     * @param bookedBy Who the room is booked by
     */
    public void bookRoom(final LocalDate bookedFrom, final LocalDate bookedTo, final String bookedBy) {
        if (isBooked()) {
            throw new IllegalStateException("Cannot book room when room is already booked.");
        }
        setBookedFrom(bookedFrom);
        setBookedTo(bookedTo);
        setBookedBy(bookedBy);
    }

    /**
     *  Remove booking from room.
     */
    public void cancelBooking() {
        if (!isBooked()) {
            throw new IllegalStateException("Cannot cancel booking when room isn't booked.");
        }
        bookedFrom = null;
        bookedTo = null;
        bookedBy = null;
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
    public void setPricePerNight(final int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    // /**
    //  * @param targetFrom Time to start booking
    //  * @param targetTo Time to end booking
    //  * @return Whether or not room is available between given times
    //  */
    // public boolean isAvailableBetween(final LocalDate targetFrom, final LocalDate targetTo) {
    //     return isBooked();

    //     !! Dette er kode for når vi skal implementere flere bookings for samme rom. !!
    //     if (!isBooked()) {
    //         return true;
    //     }
    //     return targetFrom.isBefore(bookedFrom) && targetTo.isBefore(bookedTo) || targetFrom.isAfter(bookedFrom) && targetTo.isAfter(bookedTo);
    // }

    /**
     * @return Whether or not room is booked
     */
    @JsonIgnore
    public boolean isBooked() {
        return isBookedFrom() && isBookedTo();
    }

    /**
     * @return Whether or not room is booked from
     */
    public boolean isBookedFrom() {
        return bookedFrom != null;
    }

    /**
     * @return Whether or not room is booked to
     */
    public boolean isBookedTo() {
        return bookedTo != null;
    }

    /**
     * @return Price of entire booking
     */
    public int totalCostOfBooking() {
        // FUCK DINNE FUNKSJONEN
        if (!isBooked()) {
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
