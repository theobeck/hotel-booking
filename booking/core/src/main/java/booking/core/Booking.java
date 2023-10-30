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
 * </ul>
 */
public final class Booking {

    /**
     * When the booking starts.
     */
    private LocalDate from;

    /**
     * When the booking ends.
     */
    private LocalDate to;

    /**
     * Who the booking is for.
     */
    private String bookedBy;

    /**
     * Default constructor for Booking.
     */
    public Booking() {
    }

    /**
     * Create a new booking object with the following variables defined.
     *
     * @param from     When the booking starts.
     * @param to       When the booking ends.
     * @param bookedBy Who the booking is for.
     */
    public Booking(final LocalDate from, final LocalDate to, final String bookedBy) {
        this.from = from;
        this.to = to;
        this.bookedBy = bookedBy;
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

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(final String bookedBy) {
        this.bookedBy = bookedBy;
    }

    /**
     * @param booking The booking to compare to.
     *
     * @return Whether the bookings are equal.
     */
    public Boolean equals(final Booking booking) {
        return from.equals(booking.getFrom())
                && to.equals(booking.getTo())
                && bookedBy.equals(booking.getBookedBy());
    }
}
