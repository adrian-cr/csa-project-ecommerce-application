# Project: Ecommerce Application
This project is a **microservice-based ecommerce application** built with Spring Boot. It demonstrates a modular architecture where each core business capability is encapsulated in its own service, enabling scalability, maintainability, and independent deployment.
## Table of Contents
* [Architecture Overview](#architecture-overview)
* [Procedure Overview](#procedure-overview)
* [Microservices](#microservices)
* [Setup Instructions](#setup-instructions)
* [Testing the Application](#testing-the-application)


## Project Overview
The application is composed of several independent microservices communicating via REST APIs:

* **User Service**: Manages user registration (`/users`) and authentication (`/auth`).
* **Product Service**: Handles product catalog (`/products`).
* **Order Service**: Manages order placement and details (`/orders`).
* **Payment Service**: Processes payments and manages transactions (`/payments`).

Each service has its own database and can be scaled independently.

Additionally, these microservices are all connected to two servers:
* **Eureka Server**: Acts as a service registry for all microservices, allowing them to discover each other.
* **Config Server**: Centralizes configuration management for all services, allowing dynamic configuration updates without redeployment.


## Procedure Overview
The application follows the standard flow required for payment processing in an ecommerce platform:
1. **User Registration**: A user registers via the `User` service and receives an authentication token.
2. **Payment Processing**: The `Payment` service processes the payment request, which includes validating the user's authentication token by sending an request to the `User` service.
3. **Order Placement**: Once the payment has been _approved_, the `Payment` service sends a request to the `Order` service, which interacts with the `Product` service to check product availability, and then sends a response back to the `Payment` service to finally _accept_ the payment if no issues are encountered.

The application is designed to handle errors gracefully in every step of this process, ensuring a smooth user experience.


## Microservices
| Service | Port | Description | Database Name |
|-------------------|------|---------------------------------|----|
| Product Service | 8081 | Product catalog | `ms_product_db` |
| Payment Service | 8082 | Payment processing, transaction history | `ms_payment_db`|
| Order Service | 8083 | Order placement | `ms_order_db`|
| User Service | 8084 | User registration, login, authentication | `ms_user_db`|


### Servers
| Server          | Port | Description |
|-----------------|------|---------------------------------|
| Eureka Server   | 8761 | Service registry for microservices |
| Config Server   | 8080 | Centralized configuration management for microservices |


### Database
Port: 3306


## Setup Instructions
### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL


### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/adrian-cr/csa-project-ecommerce-application
   cd csa-project-ecommerce-application
   ```
2. Build all servers/services.
3. Run all servers from their `Application` class; then do the same for each service.
4. Ensure the database server is running; create the proper schema for each service.


## Testing the Application
You can test the application using tools like Postman or cURL. Below are some sample requests to get you started:
**Create a new user**:

`POST http://localhost:8084/users`

```json
 //Body:
 {
   "username": "john_doe",
   "password": "securePass123",
   "role": "admin"
 }
```
This will generate a new authentication token to use for the payment transaction. You can skip this step by using the following token:

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZHJpYW4iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTc0ODMyODAxM30.kKB2k1D6YkTmsE9gEKnNUUdmxKJ03Qy4DZOMq3PoEc0
```

**Send a payment**:

`POST http://localhost:8082/payments`

```json
 //Body:
 {
   "items": { //"id":quantity
       "1": 1,
       "4": 2,
       "3": 5
   },
   "paymentDetails": {
       "userId": "1",
       "amount":"6.97",
       "currency": "USD",
       "paymentMethod": "debit",
       "provider": "visa"
   }
}
```
This will process the payment and create an order if successful. The response will include a message if an error occurs, or a success status (`200 - OK`) if the request is successful.

_Make sure to include the authentication token in the **Authorization** header of your request—otherwise, the payment will not go through._