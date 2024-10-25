package com.example.weatheranalyzer.weather;

import org.apache.commons.lang3.StringUtils;
/**
 * WeatherCommandLineInterface is a class that prints weather information of a city in a pretty way
 */
public class WeatherCommandLineInterface {

    /**
     * ConsoleColors is a class with static values of all colors in an ANSI console
     */
    private static class ConsoleColors {
    
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }

    private static WeatherCommandLineInterface weatherCommandLineInterface;
    private WeatherService weatherService;


    private WeatherCommandLineInterface(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * getInstance returns a singleton instance of the WeatherCommandLineInterface class
     * @param weatherService the WeatherSerive object which i want to create a CLI for 
     * @return the singleton object of the WeatherCommandLineInterface class
     */
    public static WeatherCommandLineInterface getInstance(WeatherService weatherService){
        
        if(weatherCommandLineInterface == null){
            weatherCommandLineInterface = new WeatherCommandLineInterface(weatherService);
        }

        return weatherCommandLineInterface;
    }
    
    /**
     * setTemperatureColor set the temperature color depending on its value 
     * @param temp the temperature value
     * @return the appropriate color
     */
    private String setTemparatureColor(String temp){
        
        if(Float.valueOf(temp) < 10) return ConsoleColors.BLUE;
        else if(Float.valueOf(temp) > 10 && Float.valueOf(temp) < 25) return ConsoleColors.GREEN; 
        else return ConsoleColors.RED;

    }


    /**
     * setTakeUmbrellaColor sets the color of YES/NO string depending if its gonna rain or not
     * @param willItRain the boolean value indicating if its gonna rain
     * @return the appropriate color for the YES/NO string
     */
    private String setTakeUmbrellaColor(boolean willItRain){
        if(willItRain) return ConsoleColors.BLUE;
        else return ConsoleColors.YELLOW; 
    }

    /**
     * printWeatherDataByCity prints weather information of a city in a pretty way
     * @param cityName the name of the city which its weather information will be printed.
     */
    public void printWeatherDataByCity(String cityName){
        
    
        WeatherData weatherData = weatherService.getWeatherDataByCity(cityName);
        String tempColor = setTemparatureColor(weatherData.getTemparature());
        String takeUmbrellaColor = setTakeUmbrellaColor(weatherData.getWillItRain());
        System.out.println("The weather today for the city of " + StringUtils.capitalize(weatherData.getCityName()) + ":");
        System.out.println("Date: " + weatherData.getlocalDate() );
        System.out.println(ConsoleColors.RESET + "Temperature: " + tempColor + weatherData.getTemparature() + ConsoleColors.RESET);
        if(weatherData.getWillItRain()) System.out.println(ConsoleColors.RESET + "Should you take an Umbrella:" + takeUmbrellaColor + " YES" + ConsoleColors.RESET); 
        else System.out.println(ConsoleColors.RESET + "Should you take an Umbrella:" + takeUmbrellaColor + " NO" + ConsoleColors.RESET); 

    }


    
}
