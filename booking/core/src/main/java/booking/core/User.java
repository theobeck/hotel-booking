package booking.core;

public class User {

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
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
