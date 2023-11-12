package booking.core;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * A test class for the {@link Room} class.
 *
 */
public class RoomTest {

	Room r1;
	LocalDate bookedFrom;
	LocalDate bookedTo;
	String bookedBy;
	Booking booking;

	@BeforeEach
	public void setUp() {
		r1 = new Room(1, 1, 100);
		bookedFrom = LocalDate.of(2023, 1, 2);
		bookedTo = LocalDate.of(2023, 1, 8);
		bookedBy = "test";
		booking = new Booking(bookedFrom, bookedTo, bookedBy);
	}

	@Test
	public void testToString() {
		int roomNumber = 1;
		int roomCapacity = 1;
		int pricePerNight = 100;
		Room actual = new Room(roomNumber, roomCapacity, pricePerNight);

		assertTrue(r1.toString().equals(actual.toString()));
	}

	@Test
	public void testBooking() {
		assertFalse(r1.isBookedBy(bookedBy));
		assertThrows(IllegalStateException.class, () -> r1.totalCostOfUserBooking(bookedBy));
		r1.bookRoom(bookedFrom, bookedTo, "test");
		assertThrows(IllegalStateException.class, () -> r1.bookRoom(bookedFrom, bookedTo, "test"));
		assertTrue(booking.equals(r1.getBookingsByUser(bookedBy)));
		assertEquals(600, r1.totalCostOfUserBooking(bookedBy));
		assertTrue(r1.isBookedBy(bookedBy));

		LocalDate t1 = LocalDate.of(2023, 1, 3);
		LocalDate t2 = LocalDate.of(2023, 1, 6);
		LocalDate t3 = LocalDate.of(2023, 1, 9);
		LocalDate t4 = LocalDate.of(2023, 1, 10);
		assertFalse(r1.isAvailableBetween(t1, t2));
		assertTrue(r1.isAvailableBetween(t3, t4));

		r1.cancelBooking("test");
		assertFalse(r1.isBookedBy(bookedBy));
		assertThrows(IllegalStateException.class, () -> r1.cancelBooking("test"));

	}

	@Test
	public void testPricePerNight() {
		assertEquals(100, r1.getPricePerNight());
		r1.setPricePerNight(101);
		assertEquals(101, r1.getPricePerNight());
	}

	@Test
	public void testRoomCapacity() {
		assertEquals(1, r1.getRoomCapacity());
		r1.setRoomCapacity(2);
		assertEquals(2, r1.getRoomCapacity());
	}

	@Test
	public void testRoomNumber() {
		assertEquals(1, r1.getRoomNumber());
		r1.setRoomNumber(2);
		assertEquals(2, r1.getRoomNumber());
	}
}
