package com.railway.model;

public class Passenger {
    private String name;
    private int age;
    private String gender;
    private String contactNumber;
    private String email;

    public Passenger(String name, int age, String gender, String contactNumber, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Passenger: " + name + " (Age: " + age + ", Gender: " + gender + ")";
    }
}
