package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WeatherApp extends Application {
    private static final String BASE_URL = "http://api.weatherstack.com/current";
    private static final String ACCESS_KEY = "3ea52616a4bb825a2402c3b3cf1598e5";
    
    private static String city;
    private static String countryName;
    private static String regionName;
    private static String temperature;
    private static String weatherIcons;    
    private static String weatherDescription;
    private static String isDay;
    
    
    @Override
    public void start(Stage primaryStage) {
    	TextField cityField = new TextField();
        cityField.getStyleClass().add("text-field");
        

        Button getWeatherButton = new Button("Get Weather");
        getWeatherButton.getStyleClass().add("button");

        getWeatherButton.setOnMousePressed(event -> {
            getWeatherButton.getStyleClass().add("button-clicked");
        });
        getWeatherButton.setOnMouseReleased(event -> {
            getWeatherButton.getStyleClass().remove("button-clicked");
        });
        
        
        cityField.setOnKeyPressed(event -> {
        	if (event.getCode().equals(KeyCode.ENTER)) {
        		getWeatherButton.getStyleClass().add("button-clicked");
        		getWeatherButton.fire();
        	}
        });
        cityField.setOnKeyReleased(event -> {
        	if (event.getCode().equals(KeyCode.ENTER)) {
        		getWeatherButton.getStyleClass().remove("button-clicked");
        	}
        });
        
        VBox vbox = new VBox();
        VBox container = new VBox();
        container.getStyleClass().add("container");
        
        getWeatherButton.setOnAction(event -> {
        	container.getChildren().clear();
            String cityName = cityField.getText();
            try {
                String response = fetchWeatherData(cityName);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(response);
                JSONObject location = (JSONObject) jsonObject.get("location");
                JSONObject current = (JSONObject) jsonObject.get("current");
                
                city = (String) location.get("name");
                countryName = (String) location.get("country");
                regionName = (String) location.get("region");
                long temperature_long = (long) current.get("temperature");
                temperature = Long.toString(temperature_long);
                JSONArray url_icon = (JSONArray) current.get("weather_icons");
                JSONArray weather_descriptions = (JSONArray) current.get("weather_descriptions");
                isDay = (String) current.get("is_day");
                
                if (url_icon != null) {
                	weatherIcons = (String) url_icon.get(0);
                }

                if (weather_descriptions != null) {
                	weatherDescription = (String) weather_descriptions.get(0);
                }
                
            	Image iconImage = new Image(weatherIcons);
            	ImageView iconImageView = new ImageView(iconImage);
            	iconImageView.getStyleClass().add("image-view");
            	
            	Label city_label = new Label("\nCity: " + city);
            	Label countryName_label = new Label("\nCountry: " + countryName);
            	Label regionName_label = new Label("\nRegion: " + regionName);
            	Label temperature_label = new Label("\nTemperature: " + temperature + "°");
            	Label weatherDescription_label = new Label("\nWeather: " + weatherDescription);
            	Label isDay_label = new Label("\nIs day: " + isDay);
                
            	container.getChildren().add(iconImageView);
            	container.getChildren().add(city_label);
            	container.getChildren().add(countryName_label);
            	container.getChildren().add(regionName_label);
            	container.getChildren().add(temperature_label);
            	container.getChildren().add(weatherDescription_label);
            	container.getChildren().add(isDay_label);
            	
                
            } catch (IOException | ParseException e) {
                System.out.println("Error: " + e);
            }
        });
        
        vbox.getChildren().add(container);
        vbox.getChildren().add(cityField);
        vbox.getChildren().add(getWeatherButton);
        vbox.setSpacing(10);
        vbox.getStyleClass().add("root");

        Scene scene = new Scene(vbox, 400, 600);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Weather App");
        primaryStage.show();
    }

    private String fetchWeatherData(String cityName) throws IOException {
        String url = BASE_URL + "?access_key=" + ACCESS_KEY + "&query=" + URLEncoder.encode(cityName, StandardCharsets.UTF_8);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Unexpected response code: " + responseCode);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}