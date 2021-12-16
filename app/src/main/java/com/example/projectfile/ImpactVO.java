package com.example.projectfile;

public class ImpactVO {

    private int impact;
    private String content;
    private String day;

    public ImpactVO(int impact, String content, String day) {
        this.impact = impact;
        this.content = content;
        this.day = day;
    }
    public ImpactVO(){

    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
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
