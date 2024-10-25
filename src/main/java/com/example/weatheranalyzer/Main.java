package com.example.weatheranalyzer;

import com.example.weatheranalyzer.weather.WeatherCommandLineInterface;
import com.example.weatheranalyzer.weather.WeatherService;

public class Main {
    public static void main(String[] args) {
        WeatherCommandLineInterface.getInstance(WeatherService.getInstance()).printWeatherDataByCity(args[0]);
    }
}