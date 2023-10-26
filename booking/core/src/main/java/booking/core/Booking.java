package booking.core;

import java.time.LocalDate;
import java.util.Objects;

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
     * Who the booking is for.
     */
    private String bookedBy;

    /**
     * When the booking starts.
     */
    private LocalDate from;

    /**
     * When the booking ends.
     */
    private LocalDate to;

    /**
     * Default constructor.
     */
    public Booking() {
    }

    /**
     * Create a new booking object with the following variables defined.
     *
     * @param from
     * @param to
     * @param bookedBy
     */
    public Booking(final LocalDate from, final LocalDate to, final String bookedBy) {
        this.from = from;
        this.to = to;
        this.bookedBy = bookedBy;
    }

    /**
     * @return When the booking starts.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * @param bookedFrom When the booking will start.
     */
    public void setFrom(final LocalDate bookedFrom) {
        this.from = bookedFrom;
    }

    /**
     * @return When the booking ends.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * @param bookedTo When the booking will end.
     */
    public void setTo(final LocalDate bookedTo) {
        this.to = bookedTo;
    }

    /**
     * @return Who the booking is for.
     */
    public String getBookedBy() {
        return bookedBy;
    }

    /**
     * @param bookedBy Who the booking will be for.
     */
    public void setBookedBy(final String bookedBy) {
        this.bookedBy = bookedBy;
    }

    /**
     * @param obj The booking to compare to.
     * @return Whether the bookings are equal.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Booking booking = (Booking) obj;
        return this.bookedBy.equals(booking.getBookedBy())
                && this.from.equals(booking.getFrom())
                && this.to.equals(booking.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookedBy, from, to);
    }
}
