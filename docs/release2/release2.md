# Release 2.0.0

## Changes in This Release

### Code Modularization

- Split up the code base into two distinct modules to improve code organization and maintainability.
- One of the modules has been further divided into two sub-modules to enhance code isolation and reusability.

### Code Quality Assurance

- Introduced CheckStyle for code quality assurance, ensuring adherence to coding standards and best practices.
- Added Jacoco coverage to monitor and improve test coverage, enhancing code reliability.
- Integrated SpotBugs for static code analysis, identifying and addressing potential bugs and code quality issues.

### Git Branch Structure

- Revised the git branch structure to follow an issues-oriented approach, enhancing collaboration and issue tracking.

### Documentation Enhancement

- Added illustrations to the README.md using PlantUML to improve documentation and make it more visually appealing.
- Expanded README.md files to contain necessary information about the current release.

### Data Persistence

- Transformed the data persistence mechanism from being object-saving oriented to JSON-oriented, providing more flexibility in data storage and retrieval along with more human readability.

### Testing

- Added TestFX files to comprehensively test all FXML files, ensuring a robust and reliable user interface.
- Added a test file for ReadWrite.java to ensure robust functionality and maintain code integrity.

## Bug Fixes

- Addressed several minor bugs and issues to improve the overall stability and performance of the application.
- Booked rooms no longer appear in available rooms page.

## Known Issues

- Eclipse Che won't load for us, so we couldn't test for it.

For more details and specific implementation changes, please refer to the commit history and release documentation.
