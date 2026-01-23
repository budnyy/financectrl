# 💸 FinanceCTRL

## 📌 Overview

Web application aimed towards financial management, focused on expense tracking, real-time updates, and CSV export for external analysis tools.

## 🎯 Objective

The objective of this project was to **learn** more about **backend development**, **Rest API structure**, **MVC design pattern**, **Spring Boot** fundamentals and its connection with HTML using **Thymeleaf**. 

Moreover, I focused on to coding essential features for real life usage, such as updating the database without  the need of refreshing the page, for which I used JS fetch functions. 

## 🛠️ Technologies used

### ⚙️ Backend
* Java 
* Spring Boot
* PostgreSQL
* Thymeleaf 

### 💻 Frontend
* HTML
* CSS
* Javascript (fetch functions)

## ☑️ Project Scope
* Create user
* Create expense
* Read expenses
* Update expense
* Delete expense
* Export expenses as .csv file
* Read sum of expenses (all-time/current month)

## 🧠 Concepts applied
* MVC Design Pattern, using Model, View, Controller (and Service)
* Javascript to fetch Java API endpoints and to manipulate UI
* HTML and CSS to form the web page structure

## ▶️ How to use
1. Clone this repository
2. Open it with an IDE (e.g. IntelliJ IDEA)
3. Create a PostgreSQL database
4. Configure database credentials in `application.properties`
5. Run the Spring Boot application
6. Open ``` http://localhost:8080/financectrl/user ``` on your browser

## 🚧 Limitations and possible improvements
* Better frontend polishing using Bootstrap
* Expense table filtering for price and date
* Categories for recurrent expenses
* deployment using AWS EC2 or Docker
* User management (delete user, update profile, etc) 
* Swagger for API endpoints visualization
