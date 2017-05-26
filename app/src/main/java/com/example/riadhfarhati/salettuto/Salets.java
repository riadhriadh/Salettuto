package com.example.riadhfarhati.salettuto;

/**
 * Created by i on 24/05/2017.
 */

public class Salets {

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String dates) {
        Dates = dates;
    }

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getShurooq() {
        return shurooq;
    }

    public void setShurooq(String shurooq) {
        this.shurooq = shurooq;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    private int Uid;
    private String Dates;
    private String fajr;
    private String shurooq;
    private  String dhuhr;
    private String asr;
    private String maghrib;
    private String isha;


    public Salets(int Uid, String dates, String fajr, String shurooq, String dhuhr, String asr, String maghrib, String isha) {
        this.Uid = Uid;
        Dates = dates;
        this.fajr = fajr;
        this.shurooq = shurooq;
        this.dhuhr = dhuhr;
        this.asr = asr;
        this.maghrib = maghrib;
        this.isha = isha;
    }
}
