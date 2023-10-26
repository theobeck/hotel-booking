package booking.springboot.restserver;

import org.springframework.web.bind.annotation.*;

import booking.core.Booking;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    // Inject the service that manages Booking objects (you'll create this service
    // later).
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // Implement other CRUD operations for Booking here.
}
