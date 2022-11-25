TASK:  
Create a Spring-based module, which handles event ticket booking.

Based on the attached source code model:

1. Implement three service classes:

- UserService

- EventService

- ticket service

which should contain user, event, and booking-related functionality accordingly. Create an implementation of the BookingFacade interface which should delegate method calls to services mentioned above.

(0.5 point)

2. Configure spring application context based on the XML config file. (0.5 point)

3. Implement DAO objects for each of the domain model entities (User, Event, Ticket). They should store in and retrieve data from a common in-memory storage - java map. Each entity should be stored under a separate namespace, so you could list particular entity types. (0.5 point)

Example for ticket - map entry {"ticket:" à {}}.

E.g. {"ticket:12345" à {"id" : 12345, "place" : 23, ......}}

4. Storage should be implemented as a separate spring bean. Implement the ability to initialize storage with some prepared data from the file during the application start (use spring bean post-processing features). Path to the concrete file should be set using property placeholder and external property file. (1 point)

5. DAO with storage bean should be inserted into services beans using auto wiring. Services beans should be injected into the facade using constructor-based injections. The rest of the injections should be done in a setter-based way. (1 point)

6. Cover code with unit tests. (0.5 point)

7. Code should contain proper logging. (0.5 point)

8. Create several integration tests that instantiate the Application Context directly, lookup facade bean and perform some real-life scenario (e.g. create user, then create event, then book ticket for this event for the user, then cancel it). (0.5 point)

ad. 1  
We introduced 3 interfaces with methods corresponding to BookingFacade interface, separated by functionality of Ticket, User and Event.
Implementation of BookingFacade doesn't contain any business logic, implementations of services has all logic.

ad. 2  
properties manage usage of in memory storage.  
simply "memoryStorage=true".

ad. 3  
Plan is to implement namespaces and JSON inserting in mapper above facade implementation

ad. 4  
storage initialization will be implemented in Deployer, files for deployer will be implemented along with json mapper

ad. 5  
Done in Configuration.class

ad. 6  
Services are covered with unit tests

ad. 7
We will implement logging with Lombok

ad. 8  

