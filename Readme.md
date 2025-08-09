# 🛒 E-Commerce Backend

A Spring Boot–based backend for an e-commerce application, featuring PostgreSQL for relational data, Firestore for image storage, and containerization with Docker.  
This project is designed for local development and testing, with a modular and scalable architecture.

---

## 📌 Features

- **User Management** – Sign up, login, logout (JWT-based authentication)
- **Product Management** – CRUD operations, categories, and image uploads
- **Shopping Cart** – Add, update, remove items
- **Order Management** – Checkout, order history, order status tracking
- **Admin Controls** – Manage inventory, view orders
- **Image Handling** – Store images in Firebase Storage and metadata in PostgreSQL

---

## 🛠 Tech Stack

| Layer                  | Technology |
|------------------------|------------|
| **Backend Framework**  | Spring Boot |
| **Relational Database**| PostgreSQL |
| **NoSQL / File Storage**| Firestore (Firebase Storage) |
| **Authentication**     | JWT |
| **Containerization**   | Docker, Docker Compose |
| **API Testing**        | Postman |
| **Version Control**    | Git + GitHub |

---

## 📂 Project Structure

```
ecommerce-backend/
├── src/
│   └── main/
│       ├── java/com/yourname/ecommerce/
│       └── resources/
│           └── application.yml
├── docker/
│   └── Dockerfile
├── docker-compose.yml
├── README.md
└── postman_collection.json
```

---

## ⚙️ Prerequisites

Before running the project locally, make sure you have:

- Java 17+ (https://adoptopenjdk.net/)
- Maven (https://maven.apache.org/) (if not using wrapper)
- Docker (https://www.docker.com/)
- Postman (https://www.postman.com/) (optional, for API testing)
- A Firebase project with Firestore & Storage enabled

---

## 🚀 Running Locally

### 1️⃣ Clone the repository

```
git clone https://github.com/yourusername/ecommerce-backend.git
cd ecommerce-backend
```

### 2️⃣ Configure environment variables

Create a `.env` file in the root directory:

```
# Database
POSTGRES_DB=ecommerce
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin

# Firebase
FIREBASE_CONFIG_PATH=./firebase-service-account.json
```

Place your Firebase service account key in the root as `firebase-service-account.json`.

---

### 3️⃣ Build and run with Docker

```
docker-compose up --build
```

This will start:
- PostgreSQL database
- Spring Boot backend

---

### 4️⃣ Access the API

The backend will be available at:
```
http://localhost:8080
```

API documentation (if Swagger is integrated) will be at:
```
http://localhost:8080/swagger-ui.html
```

---

## 📬 API Testing

You can use the included **Postman collection**:

1. Import `postman_collection.json` into Postman.
2. Set the `base_url` environment variable to `http://localhost:8080`.

---

## 🗄 Database Schema (High-Level)

**PostgreSQL**:
- `users`
- `products`
- `categories`
- `orders`
- `order_items`
- `cart_items`

**Firestore / Firebase Storage**:
- Product image metadata
- Public download URLs

---

## 🧪 Testing

To run tests locally:

```
mvn test
```

(Optional) For integration tests with PostgreSQL, use Testcontainers:  
https://www.testcontainers.org/

---

## 📜 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

---

## ✨ Author

**Harsh Vaghanie**  
📧 harshvaghani98@gmail.com  
🔗 [LinkedIn](https://linkedin.com/in/harshvaghanii) | [GitHub](https://github.com/harshvaghanii)  
