package booking.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

	String username;
	String firstName;
	String lastName;
	String password;
	String gender;
	List<Booking> bookings;

	@BeforeEach
	public void setUp() {
		username = "test";
		firstName = "test";
		lastName = "test";
		password = "test";
		gender = "Non-binary";
		bookings = new ArrayList<>();
		bookings.add(new Booking("test", 1, null, null, 1));

	}

	@Test
	public void testGettersSetters() {
		User expected = new User();
		expected.setUsername(username);
		assertEquals(expected.getUsername(), username);
		expected.setFirstName(firstName);
		assertEquals(expected.getFirstName(), firstName);
		expected.setLastName(lastName);
		assertEquals(expected.getLastName(), lastName);
		expected.setPassword(password);
		assertEquals(expected.getPassword(), password);
		expected.setGender(gender);
		assertEquals(expected.getGender(), gender);
		expected.setBookings(bookings);
		assertEquals(expected.getBookings(), bookings);
		Booking testBooking = new Booking("username", 1, null, null, 1);
		expected.addBooking(testBooking);
		assertEquals(expected.getBookings().size(), 2);
		assertTrue(expected.getBookings().contains(testBooking));
		expected.removeBooking(testBooking);
		assertTrue(expected.getEqualBooking(testBooking) == null);
		assertThrows(IllegalArgumentException.class, () -> expected.removeBooking(testBooking));
	}

	@Test
	public void testIsEqualTo() {
		User expected = new User(username, firstName, lastName, password, gender, bookings);

		assertTrue(expected.isEqualTo(new User(username, firstName, lastName, password, gender, bookings)));

		String falseUsername = "falseUsername";
		assertFalse(expected.isEqualTo(new User(falseUsername, firstName, lastName, password, gender, bookings)));

		String falseFirstName = "falseFirstName";
		assertFalse(expected.isEqualTo(new User(username, falseFirstName, lastName, password, gender, bookings)));

		String falseLastName = "falseLastName";
		assertFalse(expected.isEqualTo(new User(username, firstName, falseLastName, password, gender, bookings)));

		String falsePassword = "falsePassword";
		assertFalse(expected.isEqualTo(new User(username, firstName, lastName, falsePassword, gender, bookings)));

		String falseGender = "falseGender";
		assertFalse(expected.isEqualTo(new User(username, firstName, lastName, password, falseGender, bookings)));

		List<Booking> falseBookings = new ArrayList<>();
		assertFalse(expected.isEqualTo(new User(username, firstName, lastName, password, gender, falseBookings)));

		assertFalse(expected.isEqualTo(null));
	}

}
