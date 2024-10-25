package com.example.weatheranalyzer.weather;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * WeatherCommandLineInterfaceTest is test class to check if a CLI is successfully created for a WeatherService object
 */
public class WeatherCommandLineInterfaceTest {
    
    @Mock
    private WeatherService weatherService = mock(WeatherService.class);

    @InjectMocks
    private WeatherCommandLineInterface weatherCommandLineInterface;

    /**
     * testPrintWeatherDataByCity is a test function which tests if the print function works correctly isolated from the WeatherService object
     */
    @Test
    void testPrintWeatherDataByCity() {
        
        // arrange
        String cityName = "Bonn";
        when(weatherService.getWeatherDataByCity(cityName)).thenReturn(
            new WeatherData("25-10-2024", "Bonn", "13.7", "0.7")
        );
        weatherCommandLineInterface = WeatherCommandLineInterface.getInstance(weatherService);

        // act
        weatherCommandLineInterface.printWeatherDataByCity("Bonn");

        // verify
        verify(weatherService).getWeatherDataByCity(cityName);
    }
    
}
