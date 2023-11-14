package booking.ui;

import booking.core.User;

/**
 * An abstract controller for the booking application.
 */
public abstract class AbstractBookingController {

    /**
     * The user.
     */
    protected User user;

    final void setUser(final User user) {
        this.user = user;
    }

}