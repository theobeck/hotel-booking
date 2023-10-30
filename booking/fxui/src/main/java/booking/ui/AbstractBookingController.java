package booking.ui;

/**
 * An abstract controller for the booking application.
 */
public abstract class AbstractBookingController {
    /**
     * The username of the user.
     */
    protected String username;

    final String getUsername() {
        return username;
    }

    final void setUsername(final String username) {
        this.username = username;
    }

}