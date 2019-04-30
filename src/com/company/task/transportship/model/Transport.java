package com.company.task.transportship.model;

import java.util.List;
import java.util.Objects;

public abstract class Transport {

    private Integer carrying;

    private List<Product> products;

    public Transport() {
    }

    public Integer getCarrying() {
        return carrying;
    }

    public void setCarrying(Integer carrying) {
        this.carrying = carrying;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(carrying, transport.carrying) &&
                Objects.equals(products, transport.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrying, products);
    }

    @Override
    public String toString() {
        return "Transport{" +
                "carrying=" + carrying +
                ", products=" + products +
                '}';
    }
}
