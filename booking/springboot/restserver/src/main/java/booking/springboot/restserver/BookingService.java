package booking.springboot.restserver;

import org.springframework.stereotype.Service;

import booking.core.Booking;

import java.util.List;

@Service
public final class BookingService {

    /**
     * Get all bookings.
     *
     * @return all bookings.
     */
    public List<Booking> getAllBookings() {
        // #TODO: Implement logic to get all bookings.
        return null;
    }

    /**
     * Create a booking.
     *
     * @param booking the booking to create.
     *
     * @return the created booking.
     */
    public Booking createBooking(final Booking booking) {
        // #TODO: Implement logic to create a booking.
        return null;
    }

    /**
     * Get a booking by ID.
     *
     * @param id the ID of the booking to get.
     *
     * @return the booking with the given ID, or null if no booking exists with that
     *         ID.
     */
    public Booking getBookingById(final Long id) {
        // #TODO: Implement logic to get a booking by ID.
        return null;
    }

    // Implement other CRUD operations for Booking here.
}
