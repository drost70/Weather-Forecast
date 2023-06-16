# Weather-Forecast

This WeatherAPI project is a Java application that provides information about cities, countries, weather forecasts by location, and weather warnings.

# Technologies Used
Java
Spring Framework
Maven

## Installation and Usage

To run this project locally, please follow these steps:

1.https://github.com/drost70/Weather-Forecast
   
2. Open the project in your preferred IDE.
3. Build the project using the command:
mvn clean install
4. Run the application with the command:
mvn spring-boot:run
5. The application will start running on `http://localhost:8080`.

6. Use an API testing tool like Postman or cURL to interact with the API endpoints. Here are some examples:

- Retrieve a list of all cities: `GET http://localhost:8080/cities`
- Retrieve a city by ID: `GET http://localhost:8080/cities/{id}`
- Create a new city: `POST http://localhost:8080/cities`
- Update an existing city: `PUT http://localhost:8080/cities/{id}`
- Delete a city: `DELETE http://localhost:8080/cities/{id}`

# DateStorage
The application stores data in CSV files located in the src/main/resources/cities2023.6






