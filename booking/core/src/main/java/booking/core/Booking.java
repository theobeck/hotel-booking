package booking.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A specific booking of a {@link Room} made by a {@link User}.
 *
 * Has five defining characteristics:
 * <ul>
 * <li>The username of the user the booking is for</li>
 * <li>The room number of the room that is booked</li>
 * <li>When the booking starts</li>
 * <li>When the booking ends</li>
 * <li>The total cost of the booking</li>
 * </ul>
 */
public final class Booking {

    /**
     * The username of the user the booking is for.
     */
    private String bookedBy;

    /**
     * The room number of the room that is booked.
     */
    private int roomNumber;
    /**
     * When the booking starts.
     */
    private LocalDate from;

    /**
     * When the booking ends.
     */
    private LocalDate to;

    /**
     * The total cost of the booking.
     */
    private int totalCostOfBooking;

    /**
     * Default constructor for Booking.
     */
    public Booking() {
    }

    /**
     * Create a new booking.
     *
     * @param bookedBy           The username of the user the booking is for.
     * @param roomNumber         The room number of the room that is booked.
     * @param from               When the booking starts.
     * @param to                 When the booking ends.
     * @param totalCostOfBooking The total cost of the booking.
     */
    public Booking(final String bookedBy, final int roomNumber, final LocalDate from, final LocalDate to,
            final int totalCostOfBooking) {
        this.bookedBy = bookedBy;
        this.roomNumber = roomNumber;
        this.from = from;
        this.to = to;
        this.totalCostOfBooking = totalCostOfBooking;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(final String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(final int room) {
        this.roomNumber = room;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(final LocalDate bookedFrom) {
        this.from = bookedFrom;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(final LocalDate bookedTo) {
        this.to = bookedTo;
    }

    public int getTotalCostOfBooking() {
        return totalCostOfBooking;
    }

    public void setTotalCostOfBooking(final int totalCostOfBooking) {
        this.totalCostOfBooking = totalCostOfBooking;
    }

    /**
     * Takes in a booking and compares it to the current booking.
     *
     * @param booking The booking to compare to.
     *
     * @return Whether the bookings are equal.
     */
    public boolean isEqualTo(final Booking booking) {
        if (booking == null) {
            return false;
        }

        if (booking == this) {
            return true;
        }

        return this.bookedBy.equals(booking.getBookedBy()) && this.roomNumber == booking.getRoomNumber()
                && this.from.equals(booking.getFrom()) && this.to.equals(booking.getTo())
                && this.totalCostOfBooking == booking.getTotalCostOfBooking();
    }

    /**
     * @return A string representation of the booking.
     */
    @Override
    public String toString() {
        if (from == null || to == null) {
            return "Incomplete Booking Information";
        }
        String fromMonth = from.getMonth().toString();
        fromMonth = fromMonth.substring(0, 1) + fromMonth.substring(1).toLowerCase();
        String toMonth = to.getMonth().toString();
        toMonth = toMonth.substring(0, 1) + toMonth.substring(1).toLowerCase();
        int amtNights = (int) (ChronoUnit.DAYS.between(from, to));
        if (amtNights > 1) {
            return "Room " + roomNumber + ":\n" + from.getDayOfMonth() + ". " + fromMonth + " "
                    + from.getYear() + " to " + to.getDayOfMonth() + ". " + toMonth + " " + to.getYear()
                    + "\n" + amtNights + " nights: $" + totalCostOfBooking;

        }
        return "Room " + roomNumber + ":\n" + from.getDayOfMonth() + ". " + fromMonth + " "
                + from.getYear() + " to " + to.getDayOfMonth() + ". " + toMonth + " " + to.getYear()
                + "\n" + amtNights + " night: $" + totalCostOfBooking;

    }
}
