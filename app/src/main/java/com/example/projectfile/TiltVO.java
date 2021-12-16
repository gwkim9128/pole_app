package com.example.projectfile;

public class TiltVO {

    private int tilt;
    private String content;
    private String day;

    public TiltVO(int tilt, String content, String day) {
        this.tilt = tilt;
        this.day = day;
    }
    public TiltVO(){

    }

    public int getTilt() {
        return tilt;
    }

    public void setTilt(int tilt) {
        this.tilt = tilt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    //    public int getSensora() {
//        return tilt;
//    }
//
//    public void setSensora(int sensora) {
//        this.tilt = sensora;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getDay() {
//        return day;
//    }
//
//    public void setDay(String day) {
//        this.day = day;
//    }
}
