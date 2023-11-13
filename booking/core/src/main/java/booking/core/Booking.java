package booking.core;

import java.time.LocalDate;

/**
 * A specific booking of a {@link Room}.
 *
 * Has three defining characteristics:
 * <ul>
 * <li>Who the booking is for</li>
 * <li>When the booking starts</li>
 * <li>When the booking ends</li>
 * <li>The room that is booked</li>
 * <li>The room that is booked</li>
 * </ul>
 */
public final class Booking {

    /**
     * Who the booking is for.
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
     * Default constructor for Booking.
     */
    public Booking() {
    }

    /**
     * Create a new booking object with the following variables defined.
     *
     * @param bookedBy   Who the booking is for.
     * @param roomNumber The room number of the room that is booked.
     * @param from       When the booking starts.
     * @param to         When the booking ends.
     */
    public Booking(final String bookedBy, final int roomNumber, final LocalDate from, final LocalDate to) {
        this.bookedBy = bookedBy;
        this.roomNumber = roomNumber;
        this.from = from;
        this.to = to;
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

    /**
     * @param booking The booking to compare to.
     *
     * @return Whether the bookings are equal.
     */
    public boolean isEqualTo(final Booking booking) {
        return this.bookedBy.equals(booking.getBookedBy()) && this.roomNumber == booking.getRoomNumber()
                && this.from.equals(booking.getFrom()) && this.to.equals(booking.getTo());
    }
}
