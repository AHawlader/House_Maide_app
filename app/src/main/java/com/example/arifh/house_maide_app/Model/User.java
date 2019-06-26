package com.example.arifh.house_maide_app.Model;

public class User {

    int id;
    String name;
    String address;
    String phone_num;
    String email;
    String dateOfBirth;
    String jobJobless;
    String jobAddress;
    String startTime;
    String endTime;
    String currentSalary;
    String pass;
    String type;


    public User( String name, String address, String phone_num, String email, String dateOfBirth, String jobJobless, String jobAddress, String startime,String endtime, String currentSalary, String pass, String type) {
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.jobJobless = jobJobless;
        this.jobAddress = jobAddress;
        this.startTime = startime;
        this.endTime = endtime;
        this.currentSalary = currentSalary;
        this.pass = pass;
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJobJobless() {
        return jobJobless;
    }

    public void setJobJobless(String jobJobless) {
        this.jobJobless = jobJobless;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(String currentSalary) {
        this.currentSalary = currentSalary;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
