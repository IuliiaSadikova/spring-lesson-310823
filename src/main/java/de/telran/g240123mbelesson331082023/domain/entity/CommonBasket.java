package de.telran.g240123mbelesson331082023.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class CommonBasket implements Basket{

    private List<Product> products = new ArrayList<>();

    @Override
    public double getTotalCost() {
        return products.stream().mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
