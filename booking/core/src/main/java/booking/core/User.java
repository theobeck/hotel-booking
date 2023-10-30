package booking.core;

public final class User {

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Create a new user object with the following variables defined.
     *
     * @param username The user's username.
     * @param password The user's password.
     */
    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Boolean equals(final User user) {
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}
