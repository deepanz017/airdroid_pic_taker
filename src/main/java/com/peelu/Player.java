package com.peelu;

public class Player {
    double cost;
    double value;
    String name;

    public Player(String name, double cost, double value) {
        this.cost = cost;
        this.value = value;
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
