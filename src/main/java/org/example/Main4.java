package org.example;
import java.io.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import  java.util.List;
import java.util.Scanner;
public class Main4 {
    public String[] run1(String city,String wheelchair ,double rating,int datenum,String pet,String cost){
        AccuWeatherAPI3 a=new AccuWeatherAPI3();
        DateCalculator d=new DateCalculator();
        String date=d.getDateInXDays(datenum);
        System.out.println(date);
        return a.display(city,wheelchair,rating,date,pet,cost);
    }

    public void run2(double rating,String city,String name){
        AccuWeatherAPI3 a=new AccuWeatherAPI3();
        a.updaterating(rating,city,name);
    }


    public static void main(String [] args){


    }

 }

class AccuWeatherAPI3 {

    private static final String API_KEY = "dfHgRnwDjIKDnEUgW5ZYgaxWhjexH8Iv"; // Replace with your API key
    private static final String LOCATION_URL = "http://dataservice.accuweather.com/locations/v1/search";
    private static final String FORECAST_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/";


    // Method to get the location key for a city
    public static String getLocationKey(String city) throws IOException {
        // Encode the city name to handle spaces and special characters
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
        String url = LOCATION_URL + "?apikey=" + API_KEY + "&q=" + encodedCity;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            // Parse the JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            if (rootNode.isArray() && rootNode.size() > 0) {
                JsonNode location = rootNode.get(0);
                String locationKey = location.get("Key").asText();
                System.out.println("Location Key: " + locationKey);
                return locationKey;
            }
        }
        return null;
    }

    // Method to get the 5-day forecast for a location key
    // Method to get the 5-day forecast for a location key


    public static String[] getFiveDayForecast(String locationKey, String date1, String name, String place,String rating) throws IOException {
        String url = FORECAST_URL + locationKey + "?apikey=" + API_KEY + "&metric=true"; // Use metric for temperatures
        double temp[] = new double[5];
        String arr[] = new String[5];

        int i = 0;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            // Parse the JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);

            // Check if the root node has DailyForecasts
            if (rootNode.has("DailyForecasts")) {
                JsonNode dailyForecasts = rootNode.get("DailyForecasts"); // Access the DailyForecasts array

                for (JsonNode dayForecast : dailyForecasts) {
                    String date = dayForecast.get("Date").asText();

                    date = date.substring(0, 10);
                    if (date1.equals(date)) {
                        JsonNode temperature = dayForecast.get("Temperature");
                        JsonNode day = dayForecast.get("Day");
                        JsonNode night = dayForecast.get("Night");

                        // Get minimum and maximum temperatures
                        double minTemp = temperature.get("Minimum").get("Value").asDouble();
                        double maxTemp = temperature.get("Maximum").get("Value").asDouble();
                        double avg = (minTemp + maxTemp) / 2;
                        temp[i] = avg;
                        i++;
                        // Get day and night precipitation types
                        String dayPrecipitationType = day.has("PrecipitationType") ? day.get("PrecipitationType").asText() : "None";
                        String nightPrecipitationType = night.has("PrecipitationType") ? night.get("PrecipitationType").asText() : "None";

                        // Print out the forecast data

                        arr[0] = name;
                        arr[1] = place;
                        arr[2] = Double.toString(avg);

                        if (dayPrecipitationType.equals("None") && nightPrecipitationType.equals("None")) {
                            arr[3] = "None";
                        }
                        if (dayPrecipitationType.equals("Rain") || nightPrecipitationType.equals("Rain")) {
                            arr[3] = "Rain";
                        }
                        arr[4]=rating;


                    }
                }


            } else {
                System.out.println("Unexpected response structure: " + json);
            }
        }
        return arr;

    }
    public String[] display(String city,String wheelchair ,double rating,String date,String pet,String cost) {
        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\" + city +"t" +".csv";
        String line = "";
        String csvSplitBy = ",";
        String arr1[] = new String[100];
        String arr2[][] = new String[50][5];
        int index = 0;
        int finalindex = 0;
        String array[] = new String[100];
        int count = 0;
        boolean isHeader = true;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int c = 0;
            while ((line = br.readLine()) != null) {
                // Use comma as separator

                String[] data = line.split(csvSplitBy);

                if (isHeader) {
                    isHeader = false; // Set flag to false after the first line
                    continue; // Skip processing for the header
                }
                if(wheelchair=="Yes" && pet=="Yes") {

                    if(cost=="Free") {
                        if (pet.equals(data[6]) && wheelchair.equals(data[5]) && Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4]) == 0.0) {
                            System.out.println(data[1]);
                            String locationkey = getLocationKey(data[2]);
                            arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                        }
                    }
                    else{
                        if (pet.equals(data[6]) && wheelchair.equals(data[5]) && Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4]) != 0.0) {
                            System.out.println(data[1]);
                            String locationkey = getLocationKey(data[2]);
                            arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                        }
                    }
                }
                else if(wheelchair=="Yes" && pet=="No"){
                   if(cost=="Free") {
                       if (wheelchair.equals(data[5]) && Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4]) == 0.0 ) {
                           System.out.println(data[1]);
                           String locationkey = getLocationKey(data[2]);
                           arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                       }
                   }
                   else{
                       if (wheelchair.equals(data[5]) && Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4]) != 0.0 ) {
                           System.out.println(data[1]);
                           String locationkey = getLocationKey(data[2]);
                           arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                       }
                   }
                }
                else if(wheelchair=="No" && pet=="Yes"){
                  if(cost=="Free") {
                      if (pet.equals(data[6]) && Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4])==0.0 ) {
                          System.out.println(data[1]);
                          String locationkey = getLocationKey(data[2]);
                          arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                      }
                  }
                  else{
                      if (pet.equals(data[6]) && Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4])!=0.0 ) {
                          System.out.println(data[1]);
                          String locationkey = getLocationKey(data[2]);
                          arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                      }
                  }
                }
                else if(wheelchair=="No" && pet=="No"){
                   if(cost=="Free") {
                       if (Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4])==0.0) {
                           System.out.println(data[1]);
                           String locationkey = getLocationKey(data[2]);
                           arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                       }
                   }
                   else{
                       if (Double.parseDouble(data[3]) >= rating && Double.parseDouble(data[4])!=0.0) {
                           System.out.println(data[1]);
                           String locationkey = getLocationKey(data[2]);
                           arr2[index++] = getFiveDayForecast(locationkey, date, data[1], data[2],data[3]);
                       }
                   }
                }


            }
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < 5; j++) {
                array[finalindex++] = arr2[i][j];
            }
        }
        return array;
    }

    public void updaterating(double rating,String city,String place){
        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\" + city + ".csv";
        String line = "";
        String csvSplitBy = ",";
        List<List <String>> arr1 = new ArrayList<>();
        int index1=0;
        boolean isHeader = true;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int c = 0;
            while ((line = br.readLine()) != null) {
                // Use comma as separator

                String data[]= line.split(csvSplitBy);
                List<String> dataList = new ArrayList<>(List.of(data));

                if (isHeader) {
                    isHeader = false; // Set flag to false after the first line
                    continue; // Skip processing for the header
                }
                if (place.equals(data[1])) {
                    double ratings=(Double.parseDouble(dataList.get(3))+rating)/2;
                    dataList.set(3,String.valueOf(ratings));

                }
                arr1.add(dataList);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try (FileWriter writer = new FileWriter(csvFile,false)) {
            for (List<String> row : arr1) {
                writer.append(String.join(csvSplitBy, row));
                writer.append(",");
                writer.append("\n");
            }
        }   catch (Exception e) {
            System.out.println(e);
        }




    }
}