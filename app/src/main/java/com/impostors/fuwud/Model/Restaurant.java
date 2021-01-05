package com.impostors.fuwud.Model;

public class Restaurant {
    private String restaurantName,password,email,latitude, longitude,phoneNumber;


    public Restaurant() {
    }


    public Restaurant(String restaurantName, String password, String email, String latitude, String longitude, String phoneNumber) {
        this.restaurantName = restaurantName;
        this.password = password;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantName='" + restaurantName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", latitude='" + latitude + '\'' +
                ", plane='" + longitude + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
