package booking.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for the {@link Room} class.
 *
 */
public class RoomTest {

	Room r1;
	Room r2;
	LocalDate bookedFrom;
	LocalDate bookedTo;
	User user;
	Booking booking;

	@BeforeEach
	public void setUp() {
		r1 = new Room(1, 1, 100);
		bookedFrom = LocalDate.of(2023, 1, 2);
		bookedTo = LocalDate.of(2023, 1, 8);
		user = new User("test", "test", "test", "test", "Non-binary");
		booking = new Booking(user.getUsername(), r1.getRoomNumber(), bookedFrom,
				bookedTo, r1.totalCostOfBooking(bookedFrom, bookedTo));
	}

	@Test
	public void testConstructors() {
		// Test default constructor
		r1 = new Room();
		assertEquals(0, r1.getRoomNumber());
		assertEquals(0, r1.getRoomCapacity());
		assertEquals(0, r1.getPricePerNight());
		assertTrue(r1.getBookings().isEmpty());

		// Test constructor that books room
		r2 = new Room(2, 2, 200, bookedFrom, bookedTo, user);
		assertFalse(r2.getBookings().isEmpty());
		Booking b0 = r2.getBookingsByUsername(user.getUsername()).get(0);
		assertTrue(b0.isEqualTo(new Booking(user.getUsername(), r2.getRoomNumber(), bookedFrom, bookedTo,
				r2.totalCostOfBooking(bookedFrom, bookedTo))));
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
	public void testIsEqualTo() {
		int roomNumber = 1;
		int roomCapacity = 1;
		int pricePerNight = 100;
		Room actual = new Room(roomNumber, roomCapacity, pricePerNight);
		assertTrue(r1.isEqualTo(actual));

		Room rFalseNumber = new Room(roomNumber + 1, roomCapacity, pricePerNight);
		assertFalse(r1.isEqualTo(rFalseNumber));

		Room rFalseCap = new Room(roomNumber, roomCapacity + 1, pricePerNight);
		assertFalse(r1.isEqualTo(rFalseCap));

		Room rFalsePrice = new Room(roomNumber, roomCapacity, pricePerNight + 1);
		assertFalse(r1.isEqualTo(rFalsePrice));

		Room rFalseBookings = new Room(roomNumber, roomCapacity, pricePerNight, bookedFrom,
				bookedTo, user);
		assertFalse(r1.isEqualTo(rFalseBookings));
		assertFalse(r1.isEqualTo(null));
	}

	@Test
	public void testBooking() {
		assertTrue(r1.getBookings().isEmpty());
		assertTrue(r1.isAvailableBetween(bookedTo, bookedFrom));

		r1.bookRoom(bookedFrom, bookedTo, user);
		// Cannot be booked twice at same time
		assertThrows(IllegalStateException.class, () -> r1.bookRoom(bookedFrom,
				bookedTo, user));
		Booking b1 = r1.getBookingsByUsername(user.getUsername()).get(0);
		assertEquals(null, r1.getBookingsByUsername(null));
		assertTrue(b1.isEqualTo(booking));
		assertEquals(600, r1.totalCostOfBooking(bookedFrom, bookedTo));
		assertTrue(r1.getBookings().get(0).isEqualTo(booking));

		assertThrows(IllegalArgumentException.class,
				() -> r1.isAvailableBetween(LocalDate.of(2023, 11, 5), LocalDate.of(2023, 11, 1)));

		Booking b2 = new Booking("test2", r1.getRoomNumber(), bookedFrom,
				bookedTo, r1.totalCostOfBooking(bookedFrom, bookedTo));
		assertTrue(r1.getEqualBooking(b2) == null);
		r1.cancelBooking(booking);
		assertTrue(r1.getEqualBooking(booking) == null);
		assertThrows(IllegalStateException.class, () -> r1.cancelBooking(booking));

		r1.setBookings(null);
		assertTrue(r1.getBookings() == null);

		r1.setBookings(new ArrayList<>());
		assertTrue(r1.getBookings().isEmpty());

		List<Booking> bList = new ArrayList<>();
		bList.add(new Booking());
		r1.setBookings(bList);
		assertFalse(r1.getBookings().isEmpty());

	}

	@Test
	public void testIsAvailableBetween() {
		r1.bookRoom(LocalDate.of(2023, 11, 2), LocalDate.of(2023, 11, 5), user);

		assertTrue(r1.isAvailableBetween(LocalDate.of(2023, 11, 10), LocalDate.of(2023, 11, 15)));

		assertFalse(r1.isAvailableBetween(LocalDate.of(2023, 11, 3), LocalDate.of(2023, 11, 6)));

		assertFalse(r1.isAvailableBetween(LocalDate.of(2023, 11, 2), LocalDate.of(2023, 11, 5)));

		assertFalse(r1.isAvailableBetween(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 4)));

		assertTrue(r1.isAvailableBetween(LocalDate.of(2023, 10, 31), LocalDate.of(2023, 11, 1)));

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
