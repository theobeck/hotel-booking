# Release 3.0.0

## Changes and improvements in This Release

- Transitioned to a date-oriented booking process and removed redundant Hotel.java.
- Rooms can now be booked multiple times, enhancing booking flexibility.
- Created User.java and Booking.java to better handle user data and booking data.
- Implemented a new login page and a new register page along with a LogInController and RegisterController. The booking process is now tied to a specific user.
- Created REST APIs for both users and rooms, allowing for more streamlined data handling.
- Made significant changes to the UI on all pages, including new layouts, font changes, and added images.
- Created custom serializer/deserializer classes for better JSON handling for Booking, Room, and User objects.
- Updated and clarified file and controller names for better readability.

## Testing

- Updated tests to reflect the change in the booking process.
- Added new tests for the new controllers and REST APIs.
- Improved test coverage in correspondence with JaCoCo report. The modules `core` and `fxui` now have 100% test coverage, and `springboot.restserver` has near 100% test coverage.

## Bug Fixes

- Fixed bugs in the che devfile which made it impossible to run the project itself and its GUI in che.
- Addressed issues with the pipeline and Eclipse Che, ensuring smoother operations.
- Addressed various minor issues, including UI tweaks, checkstyle satisfaction, and small bug fixes.

## Documentation

- Updated Javadocs across the project for better documentation.
- Updated README.md to reflect the changes in the project.
- Added Javadocs plugin so we can generate Javadocs sites.

## Known Issues

- No known issues at this time.

For more details and specific implementation changes, please refer to the commit history and release documentation.
