package com.example.projectfile;

public class MotionVO {

    private int motion;
    private String content;
    private String day;

    public MotionVO(int motion, String content, String day) {
        this.motion = motion;
        this.content = content;
        this.day = day;
    }
    public MotionVO(){

    }

    public int getMotion() {
        return motion;
    }

    public void setMotion(int motion) {
        this.motion = motion;
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
}
