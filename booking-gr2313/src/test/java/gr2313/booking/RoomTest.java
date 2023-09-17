package gr2313.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.*;


public class RoomTest {

	Room r1;
	LocalDateTime bookedFrom;
	LocalDateTime bookedTo;

	@BeforeEach
    void setUp() {
		r1 = new Room(1, 1, 100);
		bookedFrom = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
		bookedTo = LocalDateTime.of(2023, 1, 7, 0, 0, 0);
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
		r1.bookRoom(bookedFrom, bookedTo);
		assertEquals(bookedFrom, r1.getBookedFrom());
		assertEquals(bookedTo, r1.getBookedTo());
		assertEquals(600, r1.totalCostOfBooking());
		LocalDateTime t1 = LocalDateTime.of(2023, 1, 3, 0, 0, 0);
		LocalDateTime t2 = LocalDateTime.of(2023, 1, 8, 0, 0, 0);
		assertFalse(r1.isAvailableOn(t1));
		assertTrue(r1.isAvailableOn(t2));
	}

	@Test
	public void testBookedFrom() {
		r1.setBookedFrom(bookedFrom);
		assertEquals(bookedFrom, r1.getBookedFrom());
	}

	@Test
	public void testBookedTo() {
		r1.setBookedTo(bookedTo);
		assertEquals(bookedTo, r1.getBookedTo());
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
