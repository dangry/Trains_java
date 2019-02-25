# JAVA TRAINS

### This is a train information application developed for the Kiwis of Kiwiland.

#### This app has:
 - **Edges**: Represent the **railroads** of Kiwiland train system. They have a destination and a length.
 - **Nodes** Represent train **stations**, they have a name and a list of roads with their destination.
 - **Graphs**: Represent the **railroad system map** of Kiwiland, they consist of Nodes (stations) and Edges (roads).
 - **Trains**: They represent trains with a destination. They contain a reference of the current station they are in, a reference to the destination, a reference to the railroad system map, and a reference to the limitations on it's trip.
 
 
#### Actions supported:

###### By budget restrictions imposed by the country there are currently only **5** actions supported.

 - **Route distance:** Returns the distance of a route. 
 - **Number of trips with MAX stops:** Returns the number of trips between cities with **maximum** stops.
 - **Number of trips with EXACT stops:** Returns the number of trips between cities with **exact** stops.
 - **Shortest route between 2 stations:** Returns the shortest route between 2 cities.
 - **Different routes with a LIMIT distance:** Returns the number of different routes between 2 cities with a distance limit.
 
## How to use

#### Build APP:

~~Gradle wrapper can be used to build the app `./gradlew build`~~

Gradle can be used to build the app `gradle build`

#### Run APP:

The resulting JAR file will be in `build/libs/` with the name `Trains_java-1.0-SNAPSHOT.jar`.

To run this JAR file you have to specify the directory of the test file (One is included in the project `src/test/resources/testFile.txt`):

 - Example run (From the root folder): `java -jar build/libs/Trains_java-1.0-SNAPSHOT.jar src/test/resources/testFile.txt`

##### The structure the document that the application will read has to follow these templates.

The first line must contain the map of the railroad system where the towns are named using the letters of the alphabet from A to E. A route between city A and city B with a distance of 3 will be represented as AB3.

Example map: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7

From the second line onwards we can specify the actions we want to be performed on that map.

##### Route distance has to be structured in the following way: 

 - The distance of the route {ROUTE}

Where {ROUTE} is specified by letters from A to E separated by a hyphen (-) (e.g., **A-B-C-D-E**).

##### Number of trips with MAX stops has to be structured in the following way: 

 - The number of trips starting at {START_CITY} and ending at {END_CITY} with a maximum of {MAX_STOPS} stops.
 
Where {START_CITY} and {END_CITY} are letters from A to E and {MAX_STOPS} a number representing the maximum amount of stops.

##### Number of trips with EXACT stops has to be structured in the following way: 

 - The number of trips starting at {START_CITY} and ending at {END_CITY} with {EXACT_STOPS} 4 stops.
 
Where {START_CITY} and {END_CITY} are letters from A to E and {EXACT_STOPS} a number representing the exact amount of stops.

##### Shortest route between 2 stations has to be structured in the following way: 

 - The length of the shortest route (in terms of distance to travel) from {START_CITY} to {END_CITY}.

Where {START_CITY} and {END_CITY} are letters from A to E.

##### Different routes with a LIMIT distance has to be structured in the following way:

 - The number of different routes from {START_CITY} to {END_CITY} with a distance of less than {MAX_DISTANCE}.
 
Where {START_CITY} and {END_CITY} are letters from A to E and {MAX_DISTANCE} a number representing the maximum distance traveled.
