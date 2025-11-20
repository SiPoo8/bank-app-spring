# Bank App Spring Boot Backend

A simple banking backend application built with **Spring Boot**, **JPA/Hibernate**, and **MySQL**.  
This project allows basic banking operations such as creating users, managing accounts, and performing transactions (deposit, withdraw, transfer).

---

## Features

- Create, view, and manage **Users**
- Create, view, and manage **Accounts**
- Perform **Transactions**:
  - Deposit
  - Withdraw
  - Transfer between accounts
- REST API endpoints with JSON responses
- MySQL database integration with JPA/Hibernate
- Cross-Origin Resource Sharing (CORS) enabled for frontend integration

---

## Technologies

- Java 17+  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- MySQL  
- Maven  


## Database Setup

Before running the application, create the database and user:

```sql

CREATE DATABASE bankdb;

CREATE USER 'bankuser'@'localhost' IDENTIFIED BY 'bankpwd';

GRANT ALL PRIVILEGES ON bankdb.* TO 'bankuser'@'localhost';

FLUSH PRIVILEGES;


```

---

## API Endpoints

### Users
- `POST /api/users` – Create a new user  
- `GET /api/users` – Get all users  
- `GET /api/users/{id}` – Get a user by ID  

### Accounts
- `POST /api/accounts?userId={userId}` – Create an account for a user  
- `GET /api/accounts` – Get all accounts  
- `GET /api/accounts/user/{userId}` – Get accounts by user ID  

### Transactions
- `POST /api/transactions/deposit?accountId={id}&amount={amount}` – Deposit amount into an account  
- `POST /api/transactions/withdraw?accountId={id}&amount={amount}` – Withdraw amount from an account  
- `POST /api/transactions/transfer?fromAccountId={id}&toAccountId={id}&amount={amount}` – Transfer amount between accounts  

---

## Setup

1. Clone the repository:
```bash
git clone https://github.com/SiPoo8/bank-app-spring.git
```

2. Configure MySQL database:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=1234
```

3. Run the application:
```bash
mvn spring-boot:run
```

4. The backend will run on: `http://localhost:8080`

---

## Notes

- Make sure MySQL server is running and the database `bankdb` exists.
- CORS is enabled for `http://localhost:3000` to allow frontend integration.

---

## Author

**SiPoo8**  
- GitHub: [SiPoo8](https://github.com/SiPoo8)  

---

## License

This project is licensed under the MIT License.

