package com.prodev.cvbuilder.data;

public class Education {

    private String uniStage;
    private String fromDate;
    private String toDate;
    private String degree;

    public Education(String uniStage, String fromDate, String toDate, String degree) {
        this.uniStage = uniStage;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.degree = degree;
    }

    public String getUniStage() {
        return uniStage;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        return "Education{" +
                "uniStage='" + uniStage + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
