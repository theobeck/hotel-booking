package booking.core;

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

    // TODO: Add a list of bookings to the user.

    // TODO: CREATE REST API FOR USER

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Create a new user object with the following variables defined.
     *
     * @param username  The user's username.
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param password  The user's password.
     */
    public User(final String username, final String firstName, final String lastName, final String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public Boolean equals(final User user) {
        return username.equals(user.getUsername())
                && password.equals(user.getPassword())
                && firstName.equals(user.getFirstName())
                && lastName.equals(user.getLastName());
    }
}
