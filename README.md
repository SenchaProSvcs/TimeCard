TimeCard
========

TimeCard SBMA demo

Client development environment

    Sencha Architect
    Sencha Touch

Server development environment

    Eclipse Juno
    Maven
    Apache Tomcat web server
    JAX-RS for REST web services
    Apache Wink JAX-RS service provider
    Jackson for JSON / POJO serialization / deserialization
    Java Persistence API (JPA)
    Hibernate JPA service provider
    Apache Derby (Java DB) relational database

Client functions

    Login Panel
        Displays user name / password fields and login button
        Invokes REST web service with user name and password
        Receives authentication token for subsequent calls
        Displays error message if invalid user name / password
    Punch Clock panel
        Retrieves from the server and displays a list of recent clock in / clock out events, a title / tool bar and a logout button
        Scrolls the list to the most recent / current clock in / out entry.
        Each event includes the date, including day of the week, month and day of month, the clock in time and the clock out time (if any)
        Provides a title bar with clock in and clock out buttons and a running timeclock
        The clock in button is only enabled if the user is clocked out
        The clock out button is only enabled if the user is clocked in
        On clock in / out, the application gets the location and denies the operation if the location is not available
        On clock out, if the clock out location is more than a configurable value from clock in, requests an explanation
        Invokes a REST web service to clock in / clock out

Server functions

    REST web services
        Credential resource (supports login / logout)
        Time card item resource (supports retrieving / updating clock in / out details)
    Persistent entities
        Credential (for authentication tokens)
        Employee
        Time card item
    Database servlet to start / stop / restart DB with server
