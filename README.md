# **Healthcare CSV API 🗂️**

## **Project Overview**

This project is a healthcare record management application that allows users to:
- **Upload a CSV file** containing healthcare records.
- **Search for a record** by its unique code.
- **List all records** in the database.
- **Delete all records** from the database.

The application is built using **Spring Boot** for the backend, **Angular** for the frontend, and uses **MySQL** as the database. A **Docker container** is provided for easy setup of the MySQL database.

---

## **Technologies Used**

### **Backend:**
- **Spring Boot**: Version `3.4.0`
    - RESTful API Development
    - Data Persistence with Spring Data JPA
- **Hibernate**: For ORM (Object-Relational Mapping)

### **Frontend:**
- **Angular**: Version `19.0.0`
    - Angular Material for UI components

### **Database:**
- **MySQL**: Version `8.0` (Dockerized)

### **Docker:**
- Docker Compose for managing the MySQL database container

---

## **Requirements**

To run this project, make sure you have the following installed:
- **Java Development Kit (JDK)**: Version `17` or above
- **Node.js**: Version `18.0.0` or above
- **Angular CLI**: Version `19.0.2` or above
- **Docker**: Version `20.10.0` or above

---
## Setup Instructions

### 1. Backend Setup

#### Install Dependencies
1. Navigate to the `backend` folder.
2. Build the project with Maven:

    ```bash
    mvn clean install
    ```

#### Run the Backend
1. Start the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

2. The backend will be available at: [http://localhost:8080](http://localhost:8080).

---

### 2. Database Setup

#### Run MySQL with Docker
1. Ensure Docker is installed and running on your system.
2. Run the Docker Compose file to set up the MySQL database:

    ```bash
    docker-compose up -d
    ```

#### Verify MySQL
- MySQL will run on `localhost:3306` with the following credentials:
    - **Username**: `root`
    - **Password**: `password`
    - **Database**: `health_db`

---

### 3. Frontend Setup

#### Install Dependencies
1. Navigate to the `frontend` folder.
2. Install the required dependencies:

    ```bash
    npm install
    ```

#### Run the Frontend
1. Start the Angular development server:

    ```bash
    ng serve
    ```

2. The frontend will be available at: [http://localhost:4200](http://localhost:4200).

---

## Endpoints

### Backend Endpoints

| **Method** | **Endpoint**             | **Description**                        |
|------------|--------------------------|----------------------------------------|
| POST       | `/api/records/upload`    | Upload a CSV file with records.        |
| GET        | `/api/records`           | Get all records.                       |
| GET        | `/api/records/{code}`    | Get a record by its unique code.       |
| DELETE     | `/api/records`           | Delete all records.                    |

---

## CSV Format

The CSV file must have the following structure:

```csv
source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority
ZIB,ZIB001,271636001,"Polsslag regelmatig","Description of Polsslag",2019-01-01,,1
```

---
## How to Use

### Frontend Navigation

1.	Open the frontend in a browser: http://localhost:4200.
2.	Use the navigation menu to:
•	Upload Records: Upload a CSV file.
•	Search Records: Search for a specific record by code.
•	List Records: View all records stored in the database.
•	Delete Records: Delete all records from the database.

### Additional Notes
The project uses Angular Material for a modern and consistent UI design.
•	Ensure the database is running before testing the backend or frontend.
•	If you encounter any issues, refer to the “Error Messages” section in the README or reach out to the project maintainer.


## Enjoy 🚀 !