package org.example;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import  java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
 class DateCalculator {
     public static String getDateInXDays(int x) {
         if (x < 0 || x > 4) {
             throw new IllegalArgumentException("x must be between 0 and 4 (inclusive).");
         }

         LocalDate targetDate = LocalDate.now().plusDays(x);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         return targetDate.format(formatter);
     }




}
public class Main3 {
    //String city,date,sport,stadium;
    int capacity;
    public String[] run1(String city,int datenum,String sport){
        AccuWeatherAPI2 a=new AccuWeatherAPI2();
        DateCalculator d=new DateCalculator();
        String date=d.getDateInXDays(datenum);
        System.out.println(date);
         return a.display(date,city,sport);

        }
    public String[] run2(String city,int datenum,String sport,int capacity){
        AccuWeatherAPI2 a=new AccuWeatherAPI2();
        DateCalculator d=new DateCalculator();
        String date=d.getDateInXDays(datenum);
        System.out.println(date);
        return a.display(date,city,sport,capacity);
    }
    public void run3(int  datenum,String stadium,String city){
        DateCalculator d=new DateCalculator();
        String date=d.getDateInXDays(datenum);
        Booking b=new Booking();
        b.book(date,stadium,city);
    }
    public static void main(String [] args){

    }


}
class AccuWeatherAPI2 {

    private static final String API_KEY = "Rf8uYGrfj4ytGmo4haGcl81UoDLqn1AL"; // Replace with your API key
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
    public static String[] getFiveDayForecast(String locationKey, String date1, String stadium, String place) throws IOException {
        String url = FORECAST_URL + locationKey + "?apikey=" + API_KEY + "&metric=true"; // Use metric for temperatures
        double temp[] = new double[5];
        String arr[] = new String[4];

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

                        arr[0] = stadium;
                        arr[1] = place;
                        arr[2] = Double.toString(avg);
                        if (dayPrecipitationType.equals("None") && nightPrecipitationType.equals("None")) {
                            arr[3] = "None";
                        }
                        if (dayPrecipitationType.equals("Rain") || nightPrecipitationType.equals("Rain")) {
                            arr[3] = "Rain";
                        }


                    }
                }


            } else {
                System.out.println("Unexpected response structure: " + json);
            }
        }
        return arr;

    }

    public String[] display(String date2, String city, String sport) {
        System.out.println("City:"+city);
        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\" + city + ".csv";
        String line = "";
        String csvSplitBy = ",";
        String arr1[] = new String[100];
        String arr2[][] = new String[50][4];
        String finalarray[] = new String[100];
        int finalindex = 0;
        int index = 0;
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
                int c1 = 0;
                if (sport.equals(data[3])) {
                    String place = data[1];
                    String date1 = date2;
                    String locationkey = getLocationKey(place);

                    String stadium = data[4];

                    for (String date : data) {
                        if (date.equals(date1)) {
                            c1 = 1;
                            //System.out.println("Booked aldready");
                            break;
                        }


                    }
                    if (c1 == 0) {
                        arr2[index++] = getFiveDayForecast(locationkey, date1, stadium, place);
                    }
                }


            }


        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < 4; j++) {
                finalarray[finalindex++] = arr2[i][j];
            }


        }
        return finalarray;
    }

    public String[] display(String date2, String city, String sport, int capacity) {
        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\" + city + ".csv";
        String line = "";
        String csvSplitBy = ",";
        String arr1[] = new String[100];
        String array[] = new String[100];
        int finalindex = 0;
        String arr2[][] = new String[50][4];
        int count = 0;
        int index = 0;
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
                int c1 = 0;
                if (sport.equals(data[3]) && capacity <= (Integer.parseInt(data[2]))) {
                    String place = data[1];
                    String date1 = date2;
                    String locationkey = getLocationKey(place);
                    System.out.println(data[4]);

                    for (String date : data) {
                        if (date.equals(date1)) {
                            c1 = 1;
                            //System.out.println("Booked aldready");
                            break;
                        }


                    }
                    if (c1 == 0) {
                        arr2[index++] = getFiveDayForecast(locationkey, date1, data[4], place);
                    }
                }


            }


        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < 4; j++) {
                array[finalindex++] = arr2[i][j];
            }

        }
        return array;
    }
}

    class Booking {

        public void book(String date, String stadium, String city) {
            String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\" + city + ".csv";
            String line = "";
            String csvSplitBy = ",";

            List<List<String>> arr1 = new ArrayList<>();
            int index1 = 0;
            boolean isHeader = true;
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                int c = 0;
                while ((line = br.readLine()) != null) {
                    // Use comma as separator

                    String data[] = line.split(csvSplitBy);
                    List<String> dataList = new ArrayList<>(List.of(data));

                    if (isHeader) {
                        isHeader = false; // Set flag to false after the first line
                        continue; // Skip processing for the header
                    }
                    System.out.println("stadium:"+stadium);
                    System.out.println("data[4]"+data[4]);
                    System.out.println("Check:"+data[4].equals(stadium));
                    if (stadium.equals(data[4])) {
                        dataList.set(dataList.size() - 1, date);
                        System.out.println("Date:"+date);
                        dataList.add("NULL");


                    }
                    arr1.add(dataList);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

            try (FileWriter writer = new FileWriter(csvFile, false)) {
                for (List<String> row : arr1) {
                    writer.append(String.join(csvSplitBy, row));
                    writer.append(",");
                    writer.append("\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }


        }


    }




