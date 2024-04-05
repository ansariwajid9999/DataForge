# DataForge

DataForge is a comprehensive web application designed for managing various data records. It provides a robust set of features for user authentication, data management, and advanced search capabilities.

## Technology Stack

- **Backend:** Built with Java Spring Boot
- **Frontend:** Utilizes HTML, CSS, and JavaScript
- **Database:** Relies on MySQL for data storage
- **Security:** Implements Spring Security with JWT authentication

## Getting Started

To run the application locally, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the `frontend` directory and open the `index.html` file in your preferred web browser to access the login page.
3. Use the provided credentials to log in:
    - Username: user
    - Password: ****
4. Once logged in, explore the application and its functionalities.

## Key Features

- **User Authentication:** Provides secure login functionality for users.
- **Data Management:** Enables administrators to add, update, and delete data records.
- **Advanced Search:** Users can efficiently search for specific records based on various criteria such as city, phone number, first name, or email.

## Frontend 

-**resource --> templates**

## Setup

Ensure that MySQL is installed on your system. Update the `application.properties` file in the backend directory with your MySQL database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase?createTableIfNotExists=true
spring.datasource.username=myusername
spring.datasource.password=mypassword
spring.jpa.hibernate.ddl-auto=update
