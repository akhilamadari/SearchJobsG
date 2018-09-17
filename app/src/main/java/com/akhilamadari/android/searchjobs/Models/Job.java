package com.akhilamadari.android.searchjobs.Models;

import com.google.gson.annotations.SerializedName;

public class Job {

    @SerializedName("title")
    private String jobTitle;

    @SerializedName("location")
    private String jobLocation;

    @SerializedName("company")
    private String company;

    @SerializedName("description")
    private String jobDescription;

    public Job(String jobTitle, String jobLocation, String company, String jobDescription) {
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.company = company;
        this.jobDescription = jobDescription;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
