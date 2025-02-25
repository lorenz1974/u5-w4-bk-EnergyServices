# u5-w4-bk-EnergyServices README.md

# Energy Services Application

This project is a Spring Boot application designed to manage clients and invoices for an energy services company. It provides RESTful APIs for creating, updating, deleting, and retrieving client and invoice information.

## Project Structure

The project is structured as follows:

```
src
├── main
│   ├── java
│   │   └── bw5
│   │       └── energyservices
│   │           ├── model
│   │           │   └── Invoice.java
│   │           ├── repository
│   │           │   └── InvoiceRepository.java
│   │           ├── service
│   │           │   ├── ClientService.java
│   │           │   └── InvoiceService.java
│   │           ├── dto
│   │           │   └── InvoiceRequestDTO.java
│   │           └── response
│   │               └── InvoiceResponse.java
│   └── resources
│       └── application.properties
└── pom.xml
```

## Features

- **Client Management**: Create, update, delete, and retrieve client information.
- **Invoice Management**: Create, update, delete, and retrieve invoice information.
- **Validation**: Ensures data integrity through validation checks on client and invoice data.

## Setup Instructions

1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd u5-w4-bk-EnergyServices
   ```

2. **Build the project**:
   ```
   mvn clean install
   ```

3. **Run the application**:
   ```
   mvn spring-boot:run
   ```

4. **Access the API**: The application will be available at `http://localhost:8080`.

## Usage Guidelines

- Use the provided RESTful endpoints to interact with the client and invoice data.
- Ensure that all required fields are provided when creating or updating clients and invoices.

## Dependencies

This project uses the following dependencies:
- Spring Boot
- Spring Data JPA
- Hibernate
- Validation API

## License

This project is licensed under the MIT License. See the LICENSE file for more details.