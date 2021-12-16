package com.example.projectfile;

public class ListVO {
    String number;
    String business;
    String location;

    public ListVO(String number, String business, String location) {
        this.number = number;
        this.business = business;
        this.location = location;
    }

    public ListVO() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "itemVO{" +
                "number=" + number +
                ", business='" + business + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
