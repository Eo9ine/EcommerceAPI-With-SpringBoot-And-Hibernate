**PROJECT TITLE**
# Ecommerce API with Spring Boot and Hibernate

This project is an Ecommerce API built using Spring Boot and Hibernate. It provides RESTful endpoints for managing products, categories, and orders.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/your-repo.git

2.Navigate to the project directory:
 cd your-repo

3.Build the project using Maven:
mvn clean install

4.Run the application:
mvn spring-boot:run

5.Access the API at http://localhost:8080.

### API Endpoints

| Method | Endpoint                      | Description                     |
|--------|-------------------------------|---------------------------------|
| GET    | `/api/public/categories`      | Get all products                |
| POST   | `/api/public/categories`      | Create a new product            |
| PUT    | `/api/public/categories/{id}` | Update a product by ID          |
| DEL    | `/api/public/categories/{id}` | Delete a product by ID          |

### Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot) for the framework.
- [Hibernate](https://hibernate.org/) for ORM support.

