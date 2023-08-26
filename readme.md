# Spring Boot User Authentication and Authorization Project

This is a Spring Boot project that demonstrates user authentication and authorization features using Spring Security. The project includes user signup, signin, role-based authentication, and forgot password functionality.

## Features

- User Signup: New users can register by providing necessary details.
- User Signin: Registered users can signin using their credentials.
- Role-Based Authentication: Different user roles (e.g., user, admin) with varying levels of access.
- Forgot Password: Password reset functionality via email.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA (for database interaction)
- Spring Mail (for sending emails)

## Getting Started

1. Clone the repository:

   ```bash
   https://github.com/Madhurpatari/EzeuTest8

2. Configure Database:
- Update the application.properties file with your database configuration.

3. Configure Email:
- Update the application.properties file with your email server settings for the forgot password feature.

4. Access the Application:
- Open your web browser and visit: http://localhost:8080

## Usage
- Signup: Register a new user account.
- Signin: Log in using your registered credentials.
- Access Control: Different parts of the application are accessible based on user roles.
- Forgot Password: Reset your password via email.