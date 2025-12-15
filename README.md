# Weather Dataset Reader

A simple and interactive web application that allows users to view weather information for a specific **city** and **date** using a CSV dataset. The project visually displays temperature, humidity, and rainfall with icons and highlight effects for better understanding.

---

## Project Overview

The Weather Dataset Reader reads weather data from a `dataset.csv` file and presents it in a clean, user-friendly interface. Users can enter a city name and select a date to instantly view weather conditions represented through styled weather bars.

This project is suitable for **college mini-projects**, **frontend practice**, and **basic data handling using JavaScript**.

---

## Features

* Search weather by **city name** and **date**
* Reads data dynamically from a **CSV file**
* Displays:

  * Temperature (°C)
  * Humidity (%)
  * Rainfall (mm)
* Automatic **highlight (glow) effect** for extreme values
* Input validation with warning messages
* Simple and responsive UI design

---

## Technologies Used

* **HTML5** – Structure of the webpage
* **CSS3** – Styling and glow effects
* **JavaScript** – Logic and CSV data processing
* **Java (Weather.java)** – Demonstrates basic Java weather-related logic

---

## Project Structure

```
Dataset-Reader/
│
├── index.html        # Main HTML file
├── style.css         # CSS styling
├── script.js         # JavaScript logic
├── dataset.csv       # Weather dataset
├── Weather.java      # Java program
└── README.md         # Project documentation
```

---

## How to Run the Project

1. Download or clone the repository
2. Make sure all files are in the same folder
3. Open `index.html` in any modern web browser
4. Enter a **city name** and **date** available in `dataset.csv`
5. Click **Show Weather** to view the results

---

## Dataset Format

The `dataset.csv` file should follow this format:

```
City,Date,Temperature,Humidity,Rainfall
Chennai,2024-01-01,32,70,3
Bangalore,2024-01-01,25,60,0
```

---

## Use Case

* Academic mini project
* JavaScript CSV handling practice
* Frontend UI development
* Weather data visualization

---

## Author

**Gopika S**
Student | Frontend Learner | Java & JavaScript Enthusiast

---

## License

This project is created for educational purposes.
