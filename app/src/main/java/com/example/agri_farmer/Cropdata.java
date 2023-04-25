package com.example.agri_farmer;

public class Cropdata {
    String startdate,duration,district,ph,firstimage,secondimage,thirdimage,firstheading,secondheading,thirdheading;

    public Cropdata() {
    }



    public Cropdata(String startdate, String duration, String district, String ph, String firstimage, String secondimage, String thirdimage, String firstheading, String secondheading, String thirdheading) {
        this.startdate = startdate;
        this.duration = duration;
        this.district = district;
        this.ph = ph;
        this.firstimage = firstimage;
        this.secondimage = secondimage;
        this.thirdimage = thirdimage;
        this.firstheading = firstheading;
        this.secondheading = secondheading;
        this.thirdheading = thirdheading;
    }

    public Cropdata(String startdate, String duration, String district, String ph) {
        this.startdate = startdate;
        this.duration = duration;
        this.district = district;
        this.ph = ph;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getSecondimage() {
        return secondimage;
    }

    public void setSecondimage(String secondimage) {
        this.secondimage = secondimage;
    }

    public String getThirdimage() {
        return thirdimage;
    }

    public void setThirdimage(String thirdimage) {
        this.thirdimage = thirdimage;
    }

    public String getFirstheading() {
        return firstheading;
    }

    public void setFirstheading(String firstheading) {
        this.firstheading = firstheading;
    }

    public String getSecondheading() {
        return secondheading;
    }

    public void setSecondheading(String secondheading) {
        this.secondheading = secondheading;
    }

    public String getThirdheading() {
        return thirdheading;
    }

    public void setThirdheading(String thirdheading) {
        this.thirdheading = thirdheading;
    }
}
