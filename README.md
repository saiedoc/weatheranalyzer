# Weather Analyzer

## Overview

Weather Analyzer is a Java application that analyzes daily weather data to help users decide whether they need to take an umbrella before going out. The application uses the [Gson](https://github.com/google/gson) library for JSON parsing and Apache Commons Lang for additional utilities.

## Features

- Fetches and analyzes average weather data for the day.
- Provides a simple recommendation on whether to take an umbrella based on the weather conditions.

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/weatheranalyzer.git
cd weatheranalyzer
```

### Build the Project

To build the project and create an executable JAR file, run:

```bash
mvn clean package
```

### Run the Application

After building the project, you can run the application with:

```bash
java -jar target/weatheranalyzer-1.0-SNAPSHOT.jar
```

## Dependencies

The project uses the following libraries:

- **Gson**: For JSON parsing.
- **Apache Commons Lang**: For additional utility methods.
- **JUnit Jupiter**: For unit testing (included as a test dependency).
- **Mockito**: For mocking in unit tests.

## Testing

To run the tests, use:

```bash
mvn test
```

## Docker 

A Docker image for the project can be found on DockerHub under the url: https://hub.docker.com/repository/docker/saiedaussi/weatheranalyzer

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
