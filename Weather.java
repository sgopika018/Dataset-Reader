import java.io.*;
import java.util.*;

// 🔹 Abstract parent class
abstract class WeatherData implements Serializable { // ✅ Serializable added
    protected String city;
    protected String date;

    public WeatherData(String city, String date) {
        this.city = city;
        this.date = date;
    }

    abstract void displayWeather();
}

// 🔹 Child classes
class Temperature extends WeatherData {
    private double temp;

    public Temperature(String city, String date, double temp) {
        super(city, date);
        this.temp = temp;
    }

    @Override
    void displayWeather() {
        System.out.println("City: " + city + " | Date: " + date + " | Temperature: " + temp + "°C");
    }
}

class Humidity extends WeatherData {
    private double humidity;

    public Humidity(String city, String date, double humidity) {
        super(city, date);
        this.humidity = humidity;
    }

    @Override
    void displayWeather() {
        System.out.println("City: " + city + " | Date: " + date + " | Humidity: " + humidity + "%");
    }
}

class Rainy extends WeatherData {
    private double rainfall;

    public Rainy(String city, String date, double rainfall) {
        super(city, date);
        this.rainfall = rainfall;
    }

    @Override
    void displayWeather() {
        System.out.println("City: " + city + " | Date: " + date + " | Rainfall: " + rainfall + " mm");
    }
}

// 🔹 Main class
public class Weather {
    public static void main(String[] args) {
        String fileName = "dataset.csv";
        String line;
        boolean found = false;

        // ✅ Collection: Store all matching weather data
        ArrayList<WeatherData> weatherList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String userCity = sc.nextLine().trim();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String userDate = sc.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String city = data[0];
                String date = data[1];
                double temp = Double.parseDouble(data[2]);
                double humidity = Double.parseDouble(data[3]);
                double rainfall = Double.parseDouble(data[4]);

                if (city.equalsIgnoreCase(userCity) && date.equals(userDate)) {
                    // ✅ Store in collection
                    weatherList.add(new Temperature(city, date, temp));
                    weatherList.add(new Humidity(city, date, humidity));
                    weatherList.add(new Rainy(city, date, rainfall));
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No data found for " + userCity + " on " + userDate);
            } else {
                System.out.println("\nWeather Report:");
                for (WeatherData wd : weatherList) {
                    wd.displayWeather();
                }

                // ✅ Serialization — saving objects to file
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("weatherData.ser"))) {
                    oos.writeObject(weatherList);
                    System.out.println("\n✅ Weather data serialized successfully to weatherData.ser");
                } catch (IOException e) {
                    System.out.println("Serialization error: " + e.getMessage());
                }

                // ✅ Deserialization — reading objects from file
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("weatherData.ser"))) {
                    ArrayList<WeatherData> loadedList = (ArrayList<WeatherData>) ois.readObject();
                    System.out.println("\n✅ Deserialized Weather Data:");
                    for (WeatherData wd : loadedList) {
                        wd.displayWeather();
                    }
                } catch (Exception e) {
                    System.out.println("Deserialization error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        sc.close();
    }
}