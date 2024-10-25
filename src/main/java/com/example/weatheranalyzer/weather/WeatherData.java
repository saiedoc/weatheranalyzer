package com.example.weatheranalyzer.weather;


/**
 * WeatherData is a class which represents weather information in city at certain date
 */
public class WeatherData {
   
    private String localDate;
    private String cityName;
    private String temparature;
    private boolean willItRain;



    /**
     * WeatherData public constructor
     * @param localDate date of the day
     * @param cityName name of the city
     * @param temparature current temparature the city at the specific date
     * @param rainProbability probability of raining at this date.
     */
    public WeatherData(String localDate, String cityName, String temparature, String rainProbability) {
        this.localDate = localDate;
        this.cityName = cityName;
        this.temparature = temparature;
        this.setWillItRain(rainProbability);
    }


    public String getlocalDate() {
        return localDate;
    }


    public void setlocalDate(String localDate) {
        this.localDate = localDate;
    }


    public String getCityName() {
        return cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getTemparature() {
        return temparature;
    }


    public void setTemparature(String temparature) {
        this.temparature = temparature;
    }

    private void setWillItRain(String rainProbability){
        if(Double.valueOf(rainProbability) > 0.5) this.willItRain = true;
        else this.willItRain = false;
    }


    public boolean getWillItRain() {
        return willItRain;
    }



}
