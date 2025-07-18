package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;


public class AccuWeatherAPI {

    private static final String API_KEY = "1u2jCfh97AzA1UGrZlnWXpQbN2m9EZay"; // Replace with your API key
    private static final String LOCATION_URL = "http://dataservice.accuweather.com/locations/v1/search";
    private static final String CURRENT_WEATHER_URL = "http://dataservice.accuweather.com/currentconditions/v1/";

    public String[] run(String city) throws IOException {
       System.out.println("city passed:"+city);
        String locationKey = getLocationKey(city);

        return getCurrentWeather(locationKey);

    }
    public String[] run1(String userid) throws IOException{
        return favcities(userid);
    }
    public void run2(String userid,String cityname){
        addcity(userid,cityname);
    }
    public double[] run3(String city) throws IOException{
        String locationKey = getLocationKey(city);
        double a []=get6hours(locationKey);
        return a;
    }

    public static void main(String[] args) {

    }

    // Method to get the location key for a city
    public static String getLocationKey(String city) throws IOException {
        // Encode the city name to handle spaces and special characters
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
        String url = LOCATION_URL + "?apikey=" + API_KEY + "&q=" + encodedCity;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());
            System.out.println("API Response: " + json);

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

    // Method to get the current weather for a location key
    public static String[] getCurrentWeather(String locationKey) throws IOException {
        String url = CURRENT_WEATHER_URL + locationKey + "?apikey=" + API_KEY;
        String arr[] = new String[2];

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            // Parse the JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            if (rootNode.isArray() && rootNode.size() > 0) {
                JsonNode weather = rootNode.get(0);
                String weatherText = weather.get("WeatherText").asText();
                double temperature = weather.get("Temperature").get("Metric").get("Value").asDouble();
                String ptype = weather.get("PrecipitationType").asText();

                arr[0] = weatherText;
                arr[1] = String.valueOf(temperature);
                System.out.println("Current Weather: " + weatherText);
                System.out.println("Temperature: " + temperature + " °C");
                System.out.println("Precipitation" + ptype);
            }
        }
        return arr;
    }

    public String[] favcities(String userid) throws IOException {
        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\favourites.csv";
        String line = "";
        String csvSplitBy = ",";
        System.out.println("hi");

        String[] arr1 = new String[100];
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int c = 0;
            System.out.println("begin");
            while ((line = br.readLine()) != null) {
                // Use comma as separator

                String[] data = line.split(csvSplitBy);

                System.out.println("answer:"+data[0].equals(userid));
                if (data[0].equals(userid)) {

                    int j = 0;
                    for (int i = 1; i < data.length; i++) {
                        arr1[j++] = data[i];
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return arr1;


    }
    public void addcity(String userid,String cityname){
        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\favourites.csv";
        String line = "";
        String csvSplitBy = ",";
        boolean isHeader = true;

        List<List<String>> arr1 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int c = 0;
            while ((line = br.readLine()) != null) {
                // Use comma as separator

                String[] data = line.split(csvSplitBy);
                List<String> dataList = new ArrayList<>(List.of(data));

                if(data[0].equals(userid)){
                    dataList.set(dataList.size() - 1,cityname);

                    dataList.add("NULL");
                }
                arr1.add(dataList);

            }
        }
        catch (Exception e){
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
    double[] get6hours(String locationKey) throws IOException{
        String url = "http://dataservice.accuweather.com/currentconditions/v1/" + locationKey + "/historical?apikey=" + API_KEY + "&metric=true";
        double[] arr = new double[6];
        int i=0;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            // Parse the JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);

            if (rootNode.isArray()) { // Check if the root node is an array of historical data
                for (JsonNode hourlyData : rootNode) {
                    if (i >= 6) break; // Limit to 6 hours of data
                    JsonNode temperature = hourlyData.get("Temperature").get("Metric");
                    double temp = temperature.get("Value").asDouble();
                    arr[i]=temp;
                    i++;
                }
            }
            else {
                System.out.println("Unexpected response structure: " + json);
            }
        }
        catch (Exception e){
               System.out.println(e);
        }
        return arr;
    }
    public String homecity(String userid){
        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\login.csv";
        String line = "";
        String csvSplitBy = ",";
        String a=null;
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
                if (data[0].equals(userid)) {
                    a = data[6];
                }
            }

        }
        catch (Exception e){
              System.out.println(e);
        }
        return a;
    }

}
