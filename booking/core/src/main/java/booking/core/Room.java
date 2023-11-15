package booking.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * A room that can be booked by a {@link User} with a {@link Booking}.
 *
 * Has four defining characteristics:
 * <ul>
 * <li>Room number</li>
 * <li>Room capacity</li>
 * <li>Price per night</li>
 * <li>List of bookings</li>
 * </ul>
 */
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
     * Default constructor for Room.
     */
    public Room() {
        bookings = new ArrayList<>();
    }

    /**
     * Create a new room.
     *
     * @param roomNumber    The room's number.
     * @param roomCapacity  The room's capacity.
     * @param pricePerNight How much it costs per night to book the room.
     */
    public Room(final int roomNumber, final int roomCapacity, final int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        bookings = new ArrayList<>();
    }

    /**
     * Create a new room with an initial booking.
     *
     * @param roomNumber    The room's number.
     * @param roomCapacity  The room's capacity.
     * @param pricePerNight How much it costs per night to book the room.
     * @param bookedFrom    When the booking starts.
     * @param bookedTo      When the booking ends.
     * @param bookedBy      Who the booking is for.
     */
    public Room(final int roomNumber, final int roomCapacity, final int pricePerNight, final LocalDate bookedFrom,
            final LocalDate bookedTo, final User bookedBy) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.pricePerNight = pricePerNight;
        bookings = new ArrayList<>();
        bookRoom(bookedFrom, bookedTo, bookedBy);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(final int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(final int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(final List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(final int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    /**
     * Create a new booking for the room.
     *
     * @param bookedFrom Time to start booking
     * @param bookedTo   Time to end booking
     * @param bookedBy   Who the room is booked by
     */
    public void bookRoom(final LocalDate bookedFrom, final LocalDate bookedTo, final User bookedBy) {
        if (!isAvailableBetween(bookedFrom, bookedTo)) {
            throw new IllegalStateException("Cannot book room in a time period where room is already booked.");
        }
        Booking newBooking = new Booking(bookedBy.getUsername(), roomNumber, bookedFrom, bookedTo,
                totalCostOfBooking(bookedFrom, bookedTo));
        bookings.add(newBooking);
        bookedBy.addBooking(newBooking);
    }

    /**
     * Takes in a booking and cancels it.
     *
     * @param booking Booking to cancel
     *
     * @throws IllegalStateException If the booking doesn't exist
     */
    public void cancelBooking(final Booking booking) {
        Booking bookingToCancel = getEqualBooking(booking);
        if (bookingToCancel == null) {
            throw new IllegalStateException("Cannot cancel booking that doesn't exist.");
        }
        bookings.remove(bookingToCancel);
    }

    /**
     * Check if a room is available in a date range.
     *
     * @param targetFrom Wanted start of booking
     * @param targetTo   Wanted end of booking
     *
     * @return Whether or not room is available in the date range
     */
    public boolean isAvailableBetween(final LocalDate targetFrom, final LocalDate targetTo) {
        if (bookings.isEmpty()) {
            return true;
        }
        if (targetFrom.isAfter(targetTo)) {
            throw new IllegalArgumentException("Cannot check availability in negative range.");
        }

        for (Booking booking : bookings) {
            if (booking.getTo().isAfter(targetFrom) && booking.getFrom().isBefore(targetTo)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get total cost of a booking.
     *
     * @param from Start of booking
     * @param to   End of booking
     *
     * @return Price of entire booking
     */
    public int totalCostOfBooking(final LocalDate from, final LocalDate to) {
        return (int) (pricePerNight
                * (ChronoUnit.DAYS.between(from, to)));
    }

    /**
     * Get all bookings made by a user.
     *
     * @param username Username of user to get bookings of
     *
     * @return List of bookings made by user
     */
    public List<Booking> getBookingsByUsername(final String username) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getBookedBy().equals(username)) {
                userBookings.add(booking);
            }
        }
        if (userBookings.isEmpty()) {
            userBookings = null;
        }
        return userBookings;
    }

    /**
     * Takes in a booking and returns the booking equal to it.
     *
     * @param booking Booking to compare to
     *
     * @return The booking equal to the given booking
     */
    public Booking getEqualBooking(final Booking booking) {
        Booking tempBooking = null;
        for (Booking b : bookings) {
            if (b.isEqualTo(booking)) {
                tempBooking = b;
            }
        }
        return tempBooking;
    }

    /**
     * Check if given room is equal to this room.
     *
     * @param room Room to compare to
     *
     * @return Whether or not rooms are equal
     */
    public boolean isEqualTo(final Room room) {
        if (room == null) {
            return false;
        }

        return roomNumber == room.getRoomNumber()
                && roomCapacity == room.getRoomCapacity()
                && pricePerNight == room.getPricePerNight()
                && bookings.equals(room.getBookings());
    }

    /**
     * @return String representation of room
     */
    @Override
    public String toString() {
        return "Room " + roomNumber + "\nHas bedding for " + roomCapacity + "\nCosts " + pricePerNight
                + " per night.";
    }
}
