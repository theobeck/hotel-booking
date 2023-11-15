package booking.ui;

import booking.core.User;

/**
 * An abstract controller for the booking application.
 */
public abstract class AbstractBookingController {

    /**
     * The logged in user.
     */
    protected User user;

    /**
     * Takes in a user and sets it as the logged in user.
     *
     * @param user The logged in user.
     */
    final void setUser(final User user) {
        this.user = user;
    }

}
