package booking.core;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class RoomTest {

	Room r1;
	LocalDate bookedFrom;
	LocalDate bookedTo;

	@BeforeEach
    public void setUp() {
		r1 = new Room(1, 1, 100);
		bookedFrom = LocalDate.of(2023, 1, 2);
		bookedTo = LocalDate.of(2023, 1, 8);
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
		assertFalse(r1.isBooked());
		assertThrows(IllegalStateException.class,() -> r1.totalCostOfBooking());
		r1.bookRoom(bookedFrom, bookedTo);
		assertThrows(IllegalStateException.class,() -> r1.bookRoom(bookedFrom, bookedTo));
		assertEquals(bookedFrom, r1.getBookedFrom());
		assertEquals(bookedTo, r1.getBookedTo());
		assertThrows(IllegalArgumentException.class,() -> r1.setBookedTo(LocalDate.of(2023, 1, 1)));
		assertThrows(IllegalArgumentException.class,() -> r1.setBookedFrom(LocalDate.of(2023, 1, 9)));
		assertEquals(600, r1.totalCostOfBooking());
		assertTrue(r1.isBooked());
		r1.cancelBooking();
		assertFalse(r1.isBooked());
		assertThrows(IllegalStateException.class,() -> r1.cancelBooking());

		// !! Dette er kode for når vi skal implementere flere bookings for samme rom. !!
		// LocalDate t1 = LocalDate.of(2023, 1, 3);
		// LocalDate t2 = LocalDate.of(2023, 1, 6);
		// LocalDate t3 = LocalDate.of(2023, 1, 9);
		// LocalDate t4 = LocalDate.of(2023, 1, 10);
		// assertFalse(r1.isAvailableBetween(t1, t2));
		// assertTrue(r1.isAvailableBetween(t3, t4));
	}

	@Test
	public void testBookedFrom() {
		r1.setBookedFrom(bookedFrom);
		assertEquals(bookedFrom, r1.getBookedFrom());
		assertTrue(r1.isBookedFrom());
	}

	@Test
	public void testBookedTo() {
		r1.setBookedTo(bookedTo);
		assertEquals(bookedTo, r1.getBookedTo());
		assertTrue(r1.isBookedTo());
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
