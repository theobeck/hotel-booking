package booking.core;

import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

	String bookedBy;
	int roomNumber;
	LocalDate from;
	LocalDate to;
	int totalCostOfBooking;
	Booking b1;
	Booking b2;
	Booking b3;
	Booking b4;
	Booking b5;
	Booking b6;
	Booking b7;
	Booking b8;

	@BeforeEach
	public void setUp() {
		bookedBy = "abc";
		roomNumber = 123;
		from = LocalDate.of(2023, 1, 1);
		to = LocalDate.of(2023, 1, 3);
		totalCostOfBooking = 123;
		b1 = new Booking(bookedBy, roomNumber, from, to, totalCostOfBooking);
		b2 = new Booking(bookedBy, roomNumber, from, to, totalCostOfBooking);
		b3 = new Booking(bookedBy, roomNumber, from, LocalDate.of(2023, 1, 4), totalCostOfBooking);
		b4 = new Booking(bookedBy, roomNumber, from, LocalDate.of(2023, 1, 4), totalCostOfBooking);
		b5 = new Booking(bookedBy, roomNumber, from, LocalDate.of(2023, 1, 2), totalCostOfBooking);
		b6 = new Booking(bookedBy, roomNumber, from, LocalDate.of(2023, 1, 2), totalCostOfBooking);
		b7 = new Booking(bookedBy, roomNumber, null, LocalDate.of(2023, 1, 2), totalCostOfBooking);
		b8 = new Booking(bookedBy, roomNumber, from, null, totalCostOfBooking);

	}

	@Test
	public void testGettersSetters() {
		// test all getters and setters
		Booking b = new Booking();
		b.setBookedBy(bookedBy);
		assertEquals(b1.getBookedBy(), b.getBookedBy());
		b.setRoomNumber(roomNumber);
		assertEquals(b1.getRoomNumber(), b.getRoomNumber());
		b.setFrom(from);
		assertEquals(b1.getFrom(), b.getFrom());
		b.setTo(to);
		assertEquals(b1.getTo(), b.getTo());
		b.setTotalCostOfBooking(totalCostOfBooking);
		assertEquals(b1.getTotalCostOfBooking(), b.getTotalCostOfBooking());
	}

	@Test
	public void testIsEqualTo() {
		Booking booking2 = new Booking(bookedBy, roomNumber, from, to, totalCostOfBooking);

		assertFalse(b1.isEqualTo(null));
		assertTrue(b1.isEqualTo(booking2));

		Booking falseBookedBy = new Booking("false", roomNumber, from, to, totalCostOfBooking);
		assertFalse(b1.isEqualTo(falseBookedBy));

		Booking falseRoomNumber = new Booking(bookedBy, 0, from, to, totalCostOfBooking);
		assertFalse(b1.isEqualTo(falseRoomNumber));

		Booking falseFrom = new Booking(bookedBy, roomNumber, LocalDate.of(2023, 1, 2), to, totalCostOfBooking);
		assertFalse(b1.isEqualTo(falseFrom));

		Booking falseTo = new Booking(bookedBy, roomNumber, from, LocalDate.of(2023, 1, 2), totalCostOfBooking);
		assertFalse(b1.isEqualTo(falseTo));

		Booking falseTotalCostOfBooking = new Booking(bookedBy, roomNumber, from, to, 0);
		assertFalse(b1.isEqualTo(falseTotalCostOfBooking));
	}

	@Test
	public void testToString() {
		assertNotNull(b1.toString());
		assertEquals("Incomplete Booking Information", b7.toString());
		assertEquals("Incomplete Booking Information", b8.toString());
		assertTrue(b1.toString().equals(b1.toString()));
		assertTrue(b3.toString().equals(b4.toString()));
		assertTrue(b5.toString().equals(b6.toString()));
	}
}
