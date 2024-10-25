package com.example.weatheranalyzer.weather;

import java.util.Map;

/**
 * WeatherService is a service class with methods responsible for creating program java objects
 */
public class WeatherService {

    private static WeatherService weatherService;

    private WeatherService(){}

    /**
     * getInstance creates or returns the singleton WeatherService class object
     * @return weatherService singleton object
     */
    public static WeatherService getInstance(){
        
        if(weatherService == null){
            weatherService = new WeatherService();
        }
        
        return WeatherService.weatherService;
    }


    /**
     * getWeatherDataByCity function which returns a WeatherData object with all relevant weather info of a city
     * @param cityName city name to create the WeatherData object for the function call's date
     * @return the WeatherData Object
     */
    public WeatherData getWeatherDataByCity(String cityName){
        
        Map<String,String> weatherMap = WeatherRetriever.retrieveTodayWeather(cityName);

        return new WeatherData(weatherMap.get("date"), weatherMap.get("city"), weatherMap.get("temp"), weatherMap.get("prcp"));

    }

    
}
