package booking.core;

import java.util.ArrayList;
import java.util.List;

/**
 * A user of the hotel booking system that can
 * book a {@link Room} with a {@link Booking}.
 *
 * Has six defining characteristics:
 * <ul>
 * <li>Username</li>
 * <li>First name</li>
 * <li>Last name</li>
 * <li>Password</li>
 * <li>Gender</li>
 * <li>List of bookings</li>
 * </ul>
 */
public final class User {

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's first name.
     */
    private String firstName;

    /**
     * The user's last name.
     */
    private String lastName;

    /**
     * The user's password.
     */
    private String password;

    /**
     * The user's gender.
     */
    private String gender;

    /**
     * The list of bookings the user has made.
     */
    private List<Booking> bookings;

    /**
     * Default constructor for User.
     */
    public User() {
        bookings = new ArrayList<>();
    }

    /**
     * Creates a new user.
     *
     * @param username  The user's username.
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param password  The user's password.
     * @param gender    The user's gender
     */
    public User(final String username, final String firstName, final String lastName, final String password,
            final String gender) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        bookings = new ArrayList<>();
    }

    /**
     * Creates a new user with a list of bookings.
     *
     * @param username  The user's username.
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param password  The user's password.
     * @param gender    The user's gender
     * @param bookings  The user's bookings.
     */
    public User(final String username, final String firstName, final String lastName, final String password,
            final String gender, final List<Booking> bookings) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.bookings = new ArrayList<>(bookings);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(final List<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Check if given user is equal to this user.
     *
     * @param user The user to compare to.
     *
     * @return Whether the users are equal.
     */

    public boolean isEqualTo(final User user) {
        if (user == null) {
            return false;
        }

        return username.equals(user.getUsername())
                && password.equals(user.getPassword())
                && firstName.equals(user.getFirstName())
                && lastName.equals(user.getLastName())
                && gender.equals(user.getGender())
                && bookings.equals(user.getBookings());
    }

    /**
     * Takes in a booking to add to user's list of bookings.
     *
     * @param booking The booking to add to the user.
     */
    public void addBooking(final Booking booking) {
        bookings.add(booking);
    }

    /**
     * Takes in a booking to remove from user's list of bookings.
     *
     * @param booking The booking to remove from the user.
     */
    public void removeBooking(final Booking booking) {
        Booking bookingToCancel = getEqualBooking(booking);
        if (bookingToCancel == null) {
            throw new IllegalArgumentException("Cannot cancel booking that doesn't exist.");
        }
        bookings.remove(bookingToCancel);
    }

    /**
     * Takes in a booking and returns the booking equal to it.
     *
     * @param booking Booking to compare to
     *
     * @return The booking equal to the given booking
     */
    public Booking getEqualBooking(final Booking booking) {
        Booking tempBooking = null;
        for (Booking b : bookings) {
            if (b.isEqualTo(booking)) {
                tempBooking = b;
            }
        }
        return tempBooking;
    }
}
