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
 
#### How to use:

##### The first thing that we have to understand is how to structure the document that the application will read.

The first line must contain the map of the railroad system where the towns are named using the letters of the alphabet from A to E. A route between city A and city B with a distance of 3 will be represented as AB3.

Example map: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7

From the second line onwards we can specify the actions we want to be performed on that map.

##### Route distance has to be structured in the following way: 

 - The distance of the route {ROUTE}

Where {ROUTE} is specified by letters from A to E separated by a hyphen (-) (e.g., **A-B-C-D-E**).