package booking.core;

import java.util.ArrayList;
import java.util.List;

/**
 * A user of the system.
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
     * The list of bookings for the user.
     */
    private List<Booking> bookings;

    /**
     * Default constructor for User.
     */
    public User() {
        bookings = new ArrayList<>();
    }

    /**
     * Create a new user object with the following variables defined.
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
     * @param user The user to compare to.
     *
     * @return Whether the users are equal.
     */

    public boolean isEqualTo(final User user) {
        return username.equals(user.getUsername())
                && password.equals(user.getPassword())
                && firstName.equals(user.getFirstName())
                && lastName.equals(user.getLastName())
                && gender.equals(user.getGender());
    }

    /**
     * @param booking The booking to add to the user.
     */
    public void addBooking(final Booking booking) {
        bookings.add(booking);
    }

    /**
     * @param booking The booking to remove from the user.
     */
    public void removeBooking(final Booking booking) {
        bookings.remove(booking);
    }
}
