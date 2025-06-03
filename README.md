
# 📦 API_REST_Spring

A RESTful API built with Spring Boot and Kotlin for managing an inventory system. This project supports full CRUD operations for products and includes basic session-based user authentication. Designed primarily for educational purposes, it serves as a foundational example for building REST APIs with MySQL.

---

## 🚀 Features

- **Product Management**: Create, read, update, and delete products.
- **User Authentication**: Session-based login system.
- **MySQL Integration**: Data persistence using MySQL with JPA Hibernate.
- **Swagger UI**: Interactive API documentation available at `http://localhost:8080/stockman`.
- **Environment Configuration**: Manage environment variables using `.env` file.

---

## 🛠️ Technologies Used

- **Kotlin**: Programming language for modern multiplatform applications.
- **Spring Boot**: Framework for building stand-alone, production-grade applications.
- **Spring Data JPA**: Simplifies data access using JPA and Hibernate.
- **MySQL**: Relational database management system.
- **ModelMapper**: Object mapping library to simplify DTO conversions.
- **SpringDoc OpenAPI**: Generates OpenAPI documentation for Spring Boot applications.

---

## 📂 Project Structure

```
API_REST_Spring/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/example/api/
│   │   │       ├── controllers/
│   │   │       ├── models/
│   │   │       ├── repositories/
│   │   │       └── services/
│   │   └── resources/
│   │       └── application.properties
├── .gitignore
├── build.gradle.kts
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

---

## 📦 Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/Lainezz/API_REST_Spring.git
   cd API_REST_Spring
   ```

2. **Set up the database**:

   Ensure you have MySQL installed and create a database:

   ```sql
   CREATE DATABASE inventory_db;
   ```

3. **Configure application properties**:

   Update `src/main/resources/application.properties` with your MySQL credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Build and run the application**:

   ```bash
   ./gradlew bootRun
   ```

   The API will be accessible at `http://localhost:8080`.

---

## 📚 API Endpoints

### 🔐 Authentication

- **Login**

    - **URL**: `/login`
    - **Method**: `POST`
    - **Body Parameters**:

      ```json
      {
        "username": "your_username",
        "password": "your_password"
      }
      ```

    - **Success Response**:

        - **Code**: `200 OK`
        - **Content**:

          ```json
          {
            "message": "Login successful"
          }
          ```

- **Logout**

    - **URL**: `/logout`
    - **Method**: `POST`
    - **Success Response**:

        - **Code**: `200 OK`
        - **Content**:

          ```json
          {
            "message": "Logout successful"
          }
          ```

### 📦 Products

- **Get All Products**

    - **URL**: `/productos`
    - **Method**: `GET`
    - **Success Response**:

        - **Code**: `200 OK`
        - **Content**:

          ```json
          [
            {
              "id": 1,
              "name": "Product Name",
              "price": 100,
              "quantity": 10
            },
            ...
          ]
          ```

- **Get Product by ID**

    - **URL**: `/productos/{id}`
    - **Method**: `GET`
    - **Success Response**:

        - **Code**: `200 OK`
        - **Content**:

          ```json
          {
            "id": 1,
            "name": "Product Name",
            "price": 100,
            "quantity": 10
          }
          ```

- **Create New Product**

    - **URL**: `/productos`
    - **Method**: `POST`
    - **Body Parameters**:

      ```json
      {
        "name": "New Product",
        "price": 100,
        "quantity": 10
      }
      ```

    - **Success Response**:

        - **Code**: `201 Created`
        - **Content**:

          ```json
          {
            "message": "Product created successfully",
            "product": {
              "id": 2,
              "name": "New Product",
              "price": 100,
              "quantity": 10
            }
          }
          ```

- **Update Product**

    - **URL**: `/productos/{id}`
    - **Method**: `PUT`
    - **Body Parameters**:

      ```json
      {
        "name": "Updated Product",
        "price": 150,
        "quantity": 5
      }
      ```

    - **Success Response**:

        - **Code**: `200 OK`
        - **Content**:

          ```json
          {
            "message": "Product updated successfully",
            "product": {
              "id": 1,
              "name": "Updated Product",
              "price": 150,
              "quantity": 5
            }
          }
          ```

- **Delete Product**

    - **URL**: `/productos/{id}`
    - **Method**: `DELETE`
    - **Success Response**:

        - **Code**: `200 OK`
        - **Content**:

          ```json
          {
            "message": "Product deleted successfully"
          }
          ```

---

## 🧪 Sample Data

You can test the API endpoints using tools like Postman or Insomnia. Additionally, Swagger UI is available at `http://localhost:8080/stockman` for interactive API documentation and testing.

---

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/license/mit).

---

## 👨‍💻 Author

- **Name**: Diego Linares Ortiz
- **Email**: <diegolinaresmojacar@gmail.com>

---

Feel free to contribute to this project by submitting issues or pull requests. Happy coding!
