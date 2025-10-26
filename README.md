# School Management System

A **Spring Boot RESTful API** for managing school operations — including teachers, students, subjects, classrooms, and grades.  
Built with **Spring Security**, **JWT**, and **MySQL**, this project supports multiple user roles: **Admin**, **Teacher**, and **Parent**.

---

## Features
- JWT authentication and role-based authorization  
- CRUD operations for schools, teachers, students, subjects, classrooms, and grades  
- Parents can view their children’s grades  
- Centralized exception handling  
- All APIs tested using Postman  

---

## Tech Stack
- **Backend:** Spring Boot, Spring Security, Spring Data JPA (Hibernate), MapStruct  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Testing:** Postman  

---

## Roles and Permissions
| Role | Permissions |
|------|--------------|
| **Admin** | Full management of all entities |
| **Teacher** | Manage grades and classroom information |
| **Parent** | View their child’s grades |

---

## API Testing
All endpoints were tested using **Postman**.

Example:
```http
POST /api/auth/login
Authorization: Bearer <token>
Content-Type: application/json
