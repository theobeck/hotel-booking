package gr2313.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.*;


public class RoomTest {

	Room r1;
	GregorianCalendar bookedFrom;
	GregorianCalendar bookedTo;

	@BeforeEach
    void setUp() {
		r1 = new Room(1, 1, 100);
		bookedFrom = new GregorianCalendar(123, 0, 1);
		bookedTo = new GregorianCalendar(123, 0, 7);
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
	public void testBookRoom() {
		r1.bookRoom(bookedFrom, bookedTo);
		assertEquals(bookedFrom, r1.getBookedFrom());
		assertEquals(bookedTo, r1.getBookedTo());
		assertEquals(600, r1.totalCostOfBooking());
		GregorianCalendar t1 = new GregorianCalendar(123, 0, 3);
		GregorianCalendar t2 = new GregorianCalendar(123, 0, 8);
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
