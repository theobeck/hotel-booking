package booking.fxui.internal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestAccessTest {
	HttpClient mockedHttpClient;
	private RestAccess restAccess;

	@BeforeEach
	public void setUp() {
		mockedHttpClient = mock(HttpClient.class);
		restAccess = new RestAccess(mockedHttpClient);
	}

	@Test
	public void testThrowsIOExceptions() throws Exception {
		when(mockedHttpClient.send(any(HttpRequest.class),
				eq(BodyHandlers.ofString())))
				.thenThrow(IOException.class);

		assertThrows(IOException.class, () -> restAccess.createRoom(123, 4, 100));
		assertThrows(IOException.class, () -> restAccess.getAllRooms());
		assertThrows(IOException.class, () -> restAccess.getRoomByNumber(0));
		assertThrows(IOException.class, () -> restAccess.updateRoomByNumber(0, 0, 0));
		assertThrows(IOException.class,
				() -> restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "room"));
		assertThrows(IOException.class,
				() -> restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "user"));
		assertThrows(IOException.class,
				() -> restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "both"));
		assertThrows(IOException.class,
				() -> restAccess.cancelBooking(0,"null", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), 0));
		assertThrows(IOException.class, () -> restAccess.deleteRoomByNumber(0));

		assertThrows(IOException.class, () -> restAccess.createUser("null", "null", "null", "null", "null"));
		assertThrows(IOException.class, () -> restAccess.getAllUsers());
		assertThrows(IOException.class, () -> restAccess.getBookingsByUsername("null"));
		assertThrows(IOException.class, () -> restAccess.updateUserByUsername("null", "null", "null", "null", "null"));
		assertThrows(IOException.class, () -> restAccess.deleteUserByUsername("null"));
	}

	@Test
	public void testThrowsInterruptedExceptions() throws Exception {
		when(mockedHttpClient.send(any(HttpRequest.class),
				eq(BodyHandlers.ofString())))
				.thenThrow(InterruptedException.class);
		assertThrows(InterruptedException.class, () -> restAccess.createRoom(123, 4, 100));
		assertThrows(InterruptedException.class, () -> restAccess.getAllRooms());
		assertThrows(InterruptedException.class, () -> restAccess.getRoomByNumber(0));
		assertThrows(InterruptedException.class, () -> restAccess.updateRoomByNumber(0, 0, 0));
		assertThrows(InterruptedException.class,
				() -> restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "room"));
		assertThrows(InterruptedException.class,
				() -> restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "user"));
		assertThrows(InterruptedException.class,
				() -> restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "both"));
		assertThrows(InterruptedException.class,
				() -> restAccess.cancelBooking(0,"null", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), 0));
		assertThrows(InterruptedException.class, () -> restAccess.deleteRoomByNumber(0));

		assertThrows(InterruptedException.class, () -> restAccess.createUser("null", "null", "null", "null", "null"));
		assertThrows(InterruptedException.class, () -> restAccess.getAllUsers());
		assertThrows(InterruptedException.class, () -> restAccess.getBookingsByUsername("null"));
		assertThrows(InterruptedException.class, () -> restAccess.updateUserByUsername("null", "null", "null", "null", "null"));
		assertThrows(InterruptedException.class, () -> restAccess.deleteUserByUsername("null"));
	}

	@Test
	public void testThrowsIllegalArgumentException() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1),
				LocalDate.of(2023, 11, 2), "null", 0, "none"));
	}

	@Test
	void testBookRoomByNumber() throws Exception {
		restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "both");
		restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "room");
		restAccess.bookRoomByNumber(0, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 2), "null", 0, "user");
	}

	@Test
	void testUpdateRoomByNumber() throws Exception {
		restAccess.updateRoomByNumber(0, 0, 0);
	}

	@Test
	void testUpdateUserByUsername() throws Exception {
		restAccess.updateUserByUsername("null", "null", "null", "null", "null");
	}
}
