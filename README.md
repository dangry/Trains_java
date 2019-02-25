# JAVA TRAINS

### This is a train information application developed for the Kiwis of Kiwiland.

#### This app has:
 - **Edges**: Represent the **railroads** of Kiwiland train system. They have a destination and a length.
 - **Nodes** Represent train **stations**, they have a name and a list of roads with their destination.
 - **Graphs**: Represent the **railroad system map** of Kiwiland, they consist of Nodes (stations) and Edges (roads).
 - **Trains**: They represent trains with a destination. They contain a reference of the current station they are in, a reference to the destination, a reference to the railroad system map, and a reference to the limitations on it's trip.
 
 
#### Actions supported:

###### By budget restrictions imposed by the country there are currently only **5** actions supported

 - **Route distance:** Returns the distance of a route. A route is specified by letters from A to E separated by a hyphen (-) (e.g., **A-B-C-D-E**). 
 - **Number of trips with MAX stops:** Returns the number of trips between cities with **maximum** stops.
 - **Number of trips with EXACT stops:** Returns the number of trips between cities with **exact** stops.
 - **Shortest route between 2 stations:** Returns the shortest route between 2 cities.
 - **Different routes with a LIMIT distance:** Returns the number of different routes between 2 cities with a distance limit.