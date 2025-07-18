package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main1{
   public String[] run(String city) throws IOException {
        AccuWeatherAPI1 a=new AccuWeatherAPI1();
        String locationKey = a.getLocationKey(city);


            String array[]=a.getFiveDayForecast(locationKey);
            for(int i=0;i< array.length;i++){
               System.out.println("final:"+array[i]);

            }
            return array;


    }

    public static void main(String [] args) throws IOException{

    }
}
class AccuWeatherAPI1 {

    private static final String API_KEY = "1u2jCfh97AzA1UGrZlnWXpQbN2m9EZay"; // Replace with your API key
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
    public static String[] getFiveDayForecast(String locationKey) throws IOException {
        String url = FORECAST_URL + locationKey + "?apikey=" + API_KEY + "&metric=true"; // Use metric for temperatures
        double temp[]=new double[5];
        String arr1[]=new String[200];
        //String arr2[][]=new String[30][3];
        int index=0;
        int finalindex=0;
        int i=0;
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

                        JsonNode temperature = dayForecast.get("Temperature");
                        JsonNode day = dayForecast.get("Day");
                        JsonNode night = dayForecast.get("Night");

                        // Get minifinaltemp and maximum temperatures
                        double minTemp = temperature.get("Minimum").get("Value").asDouble();
                        double maxTemp = temperature.get("Maximum").get("Value").asDouble();
                        double avg=(minTemp+maxTemp)/2;
                        temp[i]=avg;
                        i++;
                        // Get day and night precipitation types
                        String dayPrecipitationType = day.has("PrecipitationType") ? day.get("PrecipitationType").asText() : "None";
                        String nightPrecipitationType = night.has("PrecipitationType") ? night.get("PrecipitationType").asText() : "None";

                        // Print out the forecast data
                       /* System.out.println("Date: " + date);
                        System.out.println("Min Temperature: " + minTemp + " °C");
                        System.out.println("Max Temperature: " + maxTemp + " °C");
                        System.out.println("Day Precipitation Type: " + dayPrecipitationType);
                        System.out.println("Night Precipitation Type: " + nightPrecipitationType);
                        System.out.println("-----");*/
                    }
                double sum=0;
                for(double a:temp){
                    sum+=a;
                }
                double finaltemp1=sum/5;
                int finaltemp =(int)Math.floor(finaltemp1);
                System.out.println("the final temepature is"+finaltemp);
                String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\Crop_recommendation.csv";
                String line = "";
                String csvSplitBy = ",";

                String arr3[]=new String[20];
                int count=0;
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
                        double temperature = Double.parseDouble(data[3]); // Converts the String to double
                        int roundedTemperature = (int)Math.floor(temperature);

                        if (roundedTemperature==finaltemp && count==0) {


                              arr1[finalindex++]=data[7];
                              arr3[count++]=data[7];

                            String file = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\market.csv";
                            String line1 = "";


                            try (BufferedReader br1 = new BufferedReader(new FileReader(file))) {
                                int d = 0;
                                while ((line = br1.readLine()) != null) {
                                    // Use comma as separator

                                    String[] data1 = line.split(csvSplitBy);
                                    if (isHeader) {
                                        isHeader = false; // Set flag to false after the first line
                                        continue; // Skip processing for the header

                                    }
                                    if(data1[0].equals(arr1[0])){


                                        arr1[finalindex++]=data1[1];
                                       arr1[finalindex++]=data1[2];
                                       //System.out.println("a:"+arr2[index][0]);
                                       index++;
                                    }

                                }

                            }
                            catch (Exception e){
                                System.out.println(e);
                            }

                              //System.out.println("the crop recommended is"+data[7]);


                              }
                        else if(roundedTemperature==finaltemp && count!=0){

                            int c1=1;
                            for(int j=0;j<count;j++){
                                if(arr3[j].equals(data[7])){
                                    c1=0;
                                    break;
                                }
                            }
                            if(c1==1){
                                arr1[finalindex++]=data[7];
                                //System.out.println("s:"+arr1[0]);
                                arr3[count++]=data[7];
                                String file = "D:\\java project\\untitled2\\src\\main\\java\\org\\example\\market.csv";
                                String line1 = "";
                                String csvSplitBy1 = ",";


                                try (BufferedReader br1 = new BufferedReader(new FileReader(file))) {
                                    int d = 0;
                                    while ((line1 = br1.readLine()) != null) {
                                        // Use comma as separator

                                        String[] data1 = line1.split(csvSplitBy1);

                                        if (isHeader) {
                                            isHeader = false; // Set flag to false after the first line
                                            continue; // Skip processing for the header

                                        }


                                        if(data1[0].equals(data[7])){

                                            arr1[finalindex++]=data1[1];
                                            arr1[finalindex++]=data1[2];

                                            index++;
                                        }
                                    }
                                }
                                catch (Exception e){
                                    System.out.println(e);
                                }



                            }

                        }




                        }

                    }


                 catch (Exception e) {
                    System.out.println(e);
                }

                }
            else {
                System.out.println("Unexpected response structure: " + json);
            }
            return arr1;

        }
    }


}



