# Take Home Engineering Challenge

Backend to query San Francisco food truck data using Clojure and GraphQL

## Installation

Dpendency: [JDK 8+](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

Install: [Leiningen](https://leiningen.org/#install)

## Usage

    $ lein run

Default port set to 3000

Learn how to query: [GraphQL](https://graphql.org/learn/)

## Options

Options for interacting with this GraphQL schema can be found in: [schema.graphql](src/take_home_engineering_challenge/resources/schema.graphql)

The foodTrucks endpoint will calculate distance based on user coordinates (which are required) and then proceed to filter, sort, and paginate data

The default is the closest 5 food trucks to the user

## Examples

Sample POST request using Postman, CURL, or front end:

    POST /graphql HTTP/1.1
    Host: localhost:3000
    Content-Type: application/json

    {
    "query": "{ foodTrucks(userLatitude: 0, userLongitude: 0){id, name, longitude, distance}}"
    }

Response:

    {
        "data": {
            "foodTrucks": [
                {
                    "id": "1338489",
                    "name": "Wu Wei LLC dba MoBowl",
                    "longitude": -122.39167022705078,
                    "distance": 12796.08849611219
                },
                {
                    "id": "1377260",
                    "name": "Cochinita",
                    "longitude": -122.38906860351562,
                    "distance": 12796.089800790834
                },
                {
                    "id": "1181504",
                    "name": "John's Catering #5",
                    "longitude": -122.39047241210938,
                    "distance": 12796.150399027127
                },
                {
                    "id": "1337456",
                    "name": "Liang Bai Ping",
                    "longitude": -122.39118194580078,
                    "distance": 12796.185932293056
                },
                {
                    "id": "1336738",
                    "name": "Quan Catering",
                    "longitude": -122.39092254638672,
                    "distance": 12796.214794635802
                }
            ]
        }
    }

### Why This Approach

Used GraphQL because:

- I wanted to learn it
- Seemed like a good use case as the front end features aren't clearly defined
- Well known interface that could simplify querying

### Extracting, Transforming, Loading The Data

- Arbitrarily extracted only the fields I thought were useful
- Had to guess field metadata (nullability, type, etc.)
- Data is being cached on server ever hour to reduce latency to user

### What I Would Do With More Time

- Add field mapping to configs (such as mapping between fields names for graphql vs clojure keywords vs csv header name)
- Sets of configs depending on environment
- Injecting some configs through environment variables (such as port or url for data)
- Unit testing!
- CI/CD scripts in the code base
- Implement better filtering and sorting
- Features not related to graphql such as saving user information to keep track of their favorite food trucks
- A polling feature that would allow users to vote with their coworkers where they want to go for lunch

### Tradeoffs
- Caching data set for an hour to reduce latency hoping the data set doesn't change too often
- Using graphql is a little more complicated to use than vanilla REST for normal call but I'm hoping the complexity pays off as query complexity increases