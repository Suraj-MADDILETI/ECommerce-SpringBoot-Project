# ECommerce Spring Boot Project
## Overview
RESTful API service for ecommerce product management built with Spring Boot, MySQL, and Hibernate ORM.

## Tech Stack
* Spring Boot 3
* MySQL
* Hibernate ORM
* Maven
* JDK 17

## Features
* Complete CRUD operations for product management
* Image handling
* Integrated database schema
* RESTful API endpoints
* Hibernate ORM integration

## API Endpoints
## Products
* GET /api/products - Get all products
* GET /api/products/{id} - Get product by ID
* POST /api/products - Create new product
* PUT /api/products/{id} - Update product
* DELETE /api/products/{id} - Delete product

## Request/Response Examples
## Create Product
json
CopyPOST /api/products
{
    "name": "Smart Watch",
    "description": "Advanced fitness tracker",
    "brand": "TechFit",
    "price": 199.99,
    "category": "Electronics",
    "releaseDate": "2024-02-10",
    "productAvailable": true,
    "stockQuantity": 50,
    "imageName": "watch.jpg",
    "imageType": "image/jpeg"
}
