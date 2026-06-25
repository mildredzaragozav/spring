# 🌱 Spring Framework Demos
 
A collection of hands-on demo projects built with the **Spring Framework**, covering a range of modules and real-world use cases. Each module is self-contained and can be run independently.

---
 
## 📦 Projects
 
### 🗄️ Spring Data JPA
Demonstrates database persistence using Spring Data JPA and Hibernate.
- Entity mapping and relationships (`@OneToMany`, `@ManyToMany`)
- Custom repository queries with `@Query` and JPQL
- Pagination and sorting
- Avoiding common pitfalls: N+1 problem, lazy loading, `@Transactional` proxy behavior
  
### 🔐 Spring Security
Explores authentication and authorization mechanisms.
- Form-based login and logout
- JWT authentication
- Role-based access control (`@PreAuthorize`, `@Secured`)
- OAuth2 / GitHub login integration
  
### 📡 Spring for GraphQL
Shows how to build GraphQL APIs with Spring Boot.
- Schema definition with `.graphqls` files
- Query and mutation resolvers
- Data fetching with `@QueryMapping` and `@MutationMapping`

## 🚀 Getting Started
 
### Prerequisites
- Java 17 or higher
- Maven 3.8+

- Each module has its own `application.properties` file with the required configuration.

## 📝 Notes
 
- These projects are intended for learning and experimentation, not production use.
- Some modules use an in-memory H2 database for simplicity. Configuration can be switched to PostgreSQL via application.properties.
