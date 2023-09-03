# Aviation Application Submission Project. 


This application provides endpoints for retrieving information related to airports and flights. 
Below are the available endpoints and their expected results:

## Endpoints

### Airport Information

### Endpoint: 

### `/api/v1/airport-info`

### Method: 

### `GET`

### Parameters:

### `airportCode (String, required): `

The IATA airport code.

### `date (String, required):`

The date and time in ISO 8601 format (e.g., "2023-08-28T14:30:00").


### Example Request:

### `/api/v1/airport-info?airportCode=JFK&date=2023-08-28T14:30:00`

### Example Response:

### `{"arrivingFlights": 1, "departingFlights": 1, "arrivingBaggagePieces": 0, "departingBaggagePieces": 0 }`

### Flight Information

### Endpoint: 

### `/api/v1/flight-info`

### Method: 

### `GET`

### Parameters:

### `flightNumber (Integer, required): `

The flight number.

### `date (String, required): `

The date and time in ISO 8601 format (e.g., "2023-08-28T14:30:00").

### Example Request:

### `GET /api/v1/flight-info?flightNumber=123&date=2023-08-28T14:30:00`

### Example Response:
### `{"baggageWeight":10,"cargoWeight":20,"totalWeight":30}`

## Takeaways

If you had more time for the project I would have added the following:

1. Improve query date parameter i.e. query a date and return flights on that date. 
2. Refactor the code base of the project as there is a lot of duplication throughout.
3. Complete test classes for Baggage and CargoItem model classes.
4. Conduct some manual testing with Postman
5. Add controller and service classes for the remaining classes 
6. Implement integration tests for the endpoints.
7. Add field validation for model instance variables (e.g., IATA code validation).
8. Implement JWT token authentication for secure APIs.
9. Deploy the application.
10. Develop a front-end interface (e.g., using React) to interact with the APIs.