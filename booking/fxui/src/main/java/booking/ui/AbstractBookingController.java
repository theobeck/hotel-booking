package booking.ui;

public abstract class AbstractBookingController {
    /**
     * The username of the user.
     */
    protected String username;

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

}