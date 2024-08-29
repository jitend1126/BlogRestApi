
# Blog REST API

This project is a RESTful API for managing blog posts, including categories and comments. It features role-based access control, where only admins can create, update, and delete blogs. Users can comment on posts.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [API Endpoints](#api-endpoints)

## Features
- **CRUD operations for blog posts and comments**
- **Category management** for organizing blog posts
- **Role-based access control**:
  - Only admins can create, update, and delete blog posts
  - Users can't create, update, and delete comments
- **Authentication** with JWT Basic Authentication
- **Pagination and sorting** for listing blog posts
- **Swagger documentation** for easy API exploration and testing

## Tech Stack
- **Backend**: Spring Boot, Spring Data JPA
- **Database**: MySQL 
- **Security**: Spring Security with Basic
- **Documentation**: Swagger


## Prerequisites
- Java 17+
- Maven 3.6+
- MySQL database
- Postman or any API testing tool

## Installation

1. **Clone the repository**
   ```bash
   git clone  https://github.com/jitend1126/BlogRestApi.git
   cd BlogRestApi
   ```

2. **Configure the database**
   - Update the `application.properties` file located in `src/main/resources` with your database credentials.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Install dependencies**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## Running the Application

Once the application is running, you can access it at `http://localhost:8080`.
#It Automatically add User, and Admin Role in database.

## API Documentation

This project uses **Swagger** for API documentation. Once the application is running, you can access the Swagger UI to explore and test the API at:
- `http://localhost:8080/swagger-ui/index.html`

Swagger provides a user-friendly interface to interact with the API, view available endpoints, and see detailed request/response structures.

## API Endpoints

### Blog Posts
- `GET /api/posts` - Retrieve all blog posts
- `GET /api/posts/{id}` - Retrieve a single blog post by ID
- `POST /api/posts` - **(Admin only)** Create a new blog post
- `PUT /api/posts/{id}` - **(Admin only)** Update an existing blog post
- `DELETE /api/posts/{id}` - **(Admin only)** Delete a blog post

### Categories
- `GET /api/categories` - Retrieve all categories
- `POST /api/categories` - **(Admin only)** Create a new category
- `PUT /api/categories/{id}` - **(Admin only)** Update an existing category
- `DELETE /api/categories/{id}` - **(Admin only)** Delete a category

### Comments
- `GET /api/posts/{postId}/comments` - Retrieve comments for a blog post
- `POST /api/posts/{postId}/comments` - Add a comment to a blog post
- `PUT /api/posts/{postId}/comments/{commentId}` - Update a comment
- `DELETE /api/posts/{postId}/comments/{commentId}` - Delete a comment

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Log in with a user account



