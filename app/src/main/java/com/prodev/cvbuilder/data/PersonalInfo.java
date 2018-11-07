package com.prodev.cvbuilder.data;

public class PersonalInfo {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String dateOfBirth;

    public PersonalInfo(String name, String phone, String email, String address, String dateOfBirth) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
