package booking.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public final class Room {
    /**
     * The room's number.
     */
    private int roomNumber;

    /**
     * The room's capacity.
     */
    private int roomCapacity;

    /**
     * How much it costs per night to book the room.
     */
    private int pricePerNight;

    /**
     * The list of bookings for the room.
     */
    private List<Booking> bookings;

    /**
     * Create a new room object with no variables defined.
     */
    public Room() {
    }

    /**
     * Create a new room object with the following variables defined.
     *
     * @param roomNumber
     * @param roomCapacity
     * @param pricePerNight
     */
    public Room(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        bookings = new ArrayList<>();
    }

    /**
     * Create a new room object with the following variables defined.
     *
     * @param roomNumber
     * @param roomCapacity
     * @param pricePerNight
     * @param bookedFrom
     * @param bookedTo
     * @param bookedBy
     */
    public Room(final int roomNumber, final int roomCapacity, final int pricePerNight, final LocalDate bookedFrom,
            final LocalDate bookedTo, final String bookedBy) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        bookings = new ArrayList<>();
        bookRoom(bookedFrom, bookedTo, bookedBy);
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
     * @param roomCapacity Room capacity to change to
     */
    public void setRoomCapacity(final int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    /**
     * @return List of bookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * @param bookings List of bookings to change to
     */
    public void setBookings(final List<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * @param bookedFrom Time to start booking
     * @param bookedTo   TIme to end booking
     * @param bookedBy   Who the room is booked by
     */
    public void bookRoom(final LocalDate bookedFrom, final LocalDate bookedTo, final String bookedBy) {
        if (!isAvailableBetween(bookedFrom, bookedTo)) {
            throw new IllegalStateException("Cannot book room in a time period where room is already booked.");
        }
        bookings.add(new Booking(bookedFrom, bookedTo, bookedBy));
    }

    /**
     * @param bookedBy Cancel booking by user.
     * @throws IllegalStateException If room isn't booked by user.
     */
    public void cancelBooking(final String bookedBy) {
        if (!isBookedBy(bookedBy)) {
            throw new IllegalStateException("Cannot cancel booking when room isn't booked by user.");
        }
        bookings.remove(getBookingByUser(bookedBy));
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

    /**
     * @param targetFrom Time to start booking
     * @param targetTo   Time to end booking
     * @return Whether or not room is available between given times
     */
    public boolean isAvailableBetween(final LocalDate targetFrom, final LocalDate targetTo) {
        for (Booking booking : bookings) {
            if (booking.getTo().isAfter(targetFrom) && booking.getFrom().isBefore(targetTo)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param bookedBy User to check booking of
     * @return Whether or not room is booked
     */
    public boolean isBookedBy(final String bookedBy) {
        return getBookingByUser(bookedBy) != null;
    }

    /**
     * @param bookedBy User to check booking of
     * @return Price of entire booking
     */
    public int totalCostOfUserBooking(final String bookedBy) {
        if (!isBookedBy(bookedBy)) {
            throw new IllegalStateException("Cannot check booking cost when room isn't booked.");
        }
        Booking userBooking = getBookingByUser(bookedBy);
        return (int) (pricePerNight
                * (ChronoUnit.DAYS.between(userBooking.getFrom(), userBooking.getTo())));
    }

    /**
     * @param bookedBy User to get booking of
     * @return Booking of user
     */
    public Booking getBookingByUser(final String bookedBy) {
        for (Booking booking : bookings) {
            if (booking.getBookedBy().equals(bookedBy)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * @param room Room to compare to
     * @return Whether or not rooms are equal
     */
    public Boolean equals(final Room room) {
        return roomNumber == room.getRoomNumber() && roomCapacity == room.getRoomCapacity()
                && pricePerNight == room.getPricePerNight();
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Room number: " + roomNumber + ", capacity: " + roomCapacity + ", price: " + pricePerNight
                + " per night.";
    }
}
