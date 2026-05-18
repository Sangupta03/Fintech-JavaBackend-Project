# 💳 FinTech Digital Wallet System

A secure and scalable **Digital Wallet System** built using **Spring Boot, REST API, JWT Authentication, and MySQL**. This project simulates real-world fintech wallet operations such as user registration, authentication, wallet management, balance tracking, and transaction handling.

Designed to demonstrate **backend development, authentication, database management, RESTful APIs, exception handling, and layered architecture**, making it a strong resume project for software engineering roles.

---

## 🚀 Features

### 🔐 Authentication & Security
- User Registration and Login
- JWT-based Authentication
- Secure API endpoints using Spring Security
- Token validation using JWT filter

### 👤 User Management
- Register new users
- Login with credentials
- Fetch authenticated user details

### 💰 Wallet Management
- Create a digital wallet
- Add balance to wallet
- Check wallet balance
- Manage wallet details

### 💸 Transaction Management
- Transfer money functionality
- Track wallet transactions
- Maintain transaction history
- Balance validation before transactions

### ⚠️ Exception Handling
- Global Exception Handling
- Custom Exceptions
- Insufficient Balance Validation
- Resource Not Found Handling

---

## 🛠️ Tech Stack

### Backend
- **Java**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **REST API**
- **JWT Authentication**

### Database
- **MySQL**

### Build Tool
- **Maven**

### IDE Used
- **VS Code / IntelliJ IDEA**

---

## 📂 Project Structure

```text
FINTECH/
│── pom.xml
│── application.properties
│── DigitalWalletApplication.java
│
├── Entity Classes
│   ├── User.java
│   ├── Wallet.java
│   └── Transaction.java
│
├── DTOs
│   └── WalletDto.java
│
├── Repository Layer
│   ├── UserRepository.java
│   ├── WalletRepository.java
│   └── TransactionRepository.java
│
├── Service Layer
│   ├── AuthService.java
│   ├── WalletService.java
│   ├── AuthServiceImpl.java
│   └── WalletServiceImpl.java
│
├── Security
│   ├── JwtUtil.java
│   ├── JwtAuthFilter.java
│   └── SecurityConfig.java
│
├── Exception Handling
│   ├── WalletException.java
│   ├── ResourceNotFoundException.java
│   ├── InsufficientBalanceException.java
│   └── GlobalExceptionHandler.java
│
└── target/
```

---

## 🗄️ Database Configuration

Update the **application.properties** file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digital_wallet
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## ▶️ How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/your-repository-name.git
cd your-repository-name
```

### 2. Configure MySQL

- Create a database in MySQL:

```sql
CREATE DATABASE digital_wallet;
```

- Update database credentials in `application.properties`

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

---

## 🔑 Authentication Flow

1. User registers an account.
2. User logs in using credentials.
3. JWT token is generated.
4. Token is used to access secured APIs.
5. Wallet operations are performed securely.

---

## 📌 Core Concepts Implemented

- RESTful API Development
- Layered Architecture
- JWT Authentication
- Spring Security
- Exception Handling
- DTO Pattern
- Database Integration using JPA
- Repository Pattern
- Dependency Injection
- Backend Validation

---

## 🎯 Learning Outcomes

Through this project, I gained hands-on experience in:

- Building secure backend systems
- Implementing JWT authentication
- Designing REST APIs
- Handling database operations with JPA
- Exception handling in Spring Boot
- Writing modular and maintainable code
- Understanding fintech-based workflows

---

## 📈 Future Improvements

- Add Swagger API Documentation
- Role-based Authorization (Admin/User)
- Transaction Analytics Dashboard
- Email Notifications
- Payment Gateway Integration
- Docker Deployment
- Cloud Deployment (AWS/Azure)

---

## 👨‍💻 Author
**Sanjoli Gupta**
**Ridhima Sharma**



