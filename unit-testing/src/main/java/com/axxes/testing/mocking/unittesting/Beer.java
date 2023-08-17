package com.axxes.testing.mocking.unittesting;

import java.util.Objects;

public class Beer {

    private final long id;
    private final String name;
    private final double price;
    private final double rating;

    public Beer(long id, String name, double price, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }


    public double getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id && Double.compare(beer.price, price) == 0 && Double.compare(beer.rating, rating) == 0 && Objects.equals(name, beer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, rating);
    }
}
