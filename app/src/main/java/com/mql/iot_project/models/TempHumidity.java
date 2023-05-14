package com.mql.iot_project.models;

import androidx.annotation.NonNull;

public class TempHumidity {
    private double temperature ;
    private double humidity ;

    public TempHumidity() {}

    public TempHumidity(double temperature , double humidity)
    {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidite) {
        this.humidity = humidity;
    }

    @NonNull
    @Override
    public String toString() {
        return "temperature : "+this.temperature+"° , humidity : "+this.humidity+"%" ;
    }
}
