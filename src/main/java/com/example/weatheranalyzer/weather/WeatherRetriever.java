package com.example.weatheranalyzer.weather;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * WeatherRetriever is a class which contains methods responsible for making http requests
 * in order to recieve weather information.
 */
public class WeatherRetriever {

    private static final String weatherApiKey = "3374391c7cmsh6de795e7cabb2b4p104144jsn4803496571b6";
    private static final String geoApiKey = "AIzaSyADC7jizwhj1WueDP20-PwYcHQYOUsBg3E";


    /**
     * retrieveCityCoordinates function which retrieves the latitude and longtitude of a city
     * @param cityName the city which its coordinates will be retrieved
     * @return the coordinates (longtitude and latitude) as a map
     */
    private static Map<String,String> retrieveCityCoordinates(String cityName){
        
        // construct the url with the correct request parameters
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + cityName +  "&key=" + geoApiKey;
        
        // construct the http request
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

        // send the request
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Something went wrong with making the geo request");
        }
        
        // build a json object from the string 
        JsonObject geoResponseBody = JsonParser.parseString(response.body()).getAsJsonObject();


        // get the longtitude value from the json object
        String longtitude = geoResponseBody.getAsJsonArray("results")
                .get(0).getAsJsonObject()
                .getAsJsonObject("geometry")
                .getAsJsonObject("location")
                .get("lng").getAsString();

        // get the latitude value from the json object
        String latitutde = geoResponseBody.getAsJsonArray("results")
            .get(0).getAsJsonObject()
            .getAsJsonObject("geometry")
            .getAsJsonObject("location")
            .get("lat").getAsString();


        // return them as a map
        return new HashMap<String, String>()
        {
            {
        put("longtitude", longtitude);
        put("latitude", latitutde);
            }
        };
    }

    /**
     * retrieveTodayWeather is a function which retrieves weather info (temperature, percipitation, date) of a city in function call day
     * @param cityName name of the city which its weather info will be retrieved
     * @return Map of city name, temperature, percipitation and date values
     */
    public static Map<String,String> retrieveTodayWeather(String cityName){
        
        // get city coordinates
        Map<String,String> cityCoords = WeatherRetriever.retrieveCityCoordinates(cityName); 

        // construct the url
        String url = "https://meteostat.p.rapidapi.com/point/daily?lat="+ cityCoords.get("latitude") + "&lon=" + cityCoords.get("longtitude") + "&start=" + String.valueOf(LocalDate.now()) + "&end=" + String.valueOf(LocalDate.now());

        // construct the request
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("x-rapidapi-host", "meteostat.p.rapidapi.com")
        .header("x-rapidapi-key", weatherApiKey)
        .GET()
        .build();

        // send the request and get response
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Something went wrong with making the weather request");
        }

        // construct the JsonObject
        JsonObject weatherResponseObject = JsonParser.parseString(response.body()).getAsJsonObject();

  
        // return relevant weather info as a map
        return new HashMap<String, String>()
        {
            {   
                put("city", cityName);
                put("temp", weatherResponseObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("tavg").getAsString());
                put("prcp", weatherResponseObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("prcp").getAsString());
                put("date", weatherResponseObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("date").getAsString());
            }
        };
    }

}
