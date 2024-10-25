package com.example.weatheranalyzer.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;


import java.util.Map;
import java.util.HashMap;

/**
 * WeatherServiceTest is a test class to test WeatherService methods
 */
public class WeatherServiceTest {

    @Mock
    MockedStatic<WeatherRetriever> mockedStaticWeatherRetriever = mockStatic(WeatherRetriever.class); 
    
    @InjectMocks
    private WeatherService weatherService = WeatherService.getInstance();

    /**
     * testGetWeatherDataByCity is a test function to test the getWeatherDataByCity function isolated from WeatherRetriever class
     */
    @Test
    void testGetWeatherDataByCity() {
        
        // arrange
        Map<String, String> weatherMap = new HashMap<String,String>(){
            {
                put("city", "Bonn");
                put("temp", "15");
                put("prcp", "0.3");
                put("date", "25-10-2024");
            }
        };
        
        mockedStaticWeatherRetriever.when(() -> WeatherRetriever.retrieveTodayWeather("Bonn") ).thenReturn(weatherMap);
        
        // act
        WeatherData weatherData = weatherService.getWeatherDataByCity("Bonn");

        // assert
        assertEquals(weatherData.getTemparature(), "15");
        assertTrue(!weatherData.getWillItRain());

        // verify
        mockedStaticWeatherRetriever.verify(()-> WeatherRetriever.retrieveTodayWeather("Bonn"),times(1));
    }

}
