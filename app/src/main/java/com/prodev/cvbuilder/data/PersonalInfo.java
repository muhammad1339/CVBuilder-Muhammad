package com.prodev.cvbuilder.data;

public class PersonalInfo {
    private String name;
    private String phone;
    private String email;
    private int age;

    public PersonalInfo(String name, String phone, String email, int age) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
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

    public int getAge() {
        return age;
    }
}
