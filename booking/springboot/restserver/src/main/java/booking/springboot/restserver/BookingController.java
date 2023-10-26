package booking.springboot.restserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import booking.core.Booking;

/**
 * The controller for Booking objects.
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    /**
     * Inject the service that manages Booking objects.
     */
    private final BookingService bookingService;

    /**
     * Constructor for BookingController that injects the given BookingService.
     *
     * @param bookingService The booking service to use
     */
    public BookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Get all bookings in the system.
     *
     * @return All bookings in the system
     */
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * Create a new booking.
     *
     * @param booking The booking to create
     *
     * @return The created booking
     */
    @PostMapping
    public Booking createBooking(final @RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    /**
     * Get a booking by its ID.
     *
     * @param id The ID of the booking to get
     *
     * @return The booking with the given ID
     */
    @GetMapping("/{id}")
    public Booking getBookingById(final @PathVariable Long id) {
        return bookingService.getBookingById(id);
    }
}
