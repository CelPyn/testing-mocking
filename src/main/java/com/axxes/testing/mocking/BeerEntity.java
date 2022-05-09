package com.axxes.testing.mocking;

import java.util.Objects;

public class BeerEntity {

    private long id;
    private String name;
    private double price;
    private double rating;

    public BeerEntity(long id, String name, double price, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public BeerEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BeerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public BeerEntity setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public BeerEntity setRating(double rating) {
        this.rating = rating;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BeerEntity that = (BeerEntity) o;
        return id == that.id && Double.compare(that.price, price) == 0 && Double.compare(that.rating, rating) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, rating);
    }
}
