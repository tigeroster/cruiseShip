package com.company;

import java.io.Serializable;

public class Passenger implements Serializable{
    private String firstName;
    private String surName;
    private double expenses;

    public Passenger(){
        firstName = "nobody";
        surName = "nobody";
        expenses = 0.0;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getSurName() {
        return surName;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }
}
