package org.metrodatacademy.repository;

import org.metrodatacademy.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    public static final List<Product> PRODUCTS = Arrays.asList(
            new Product(1, "Iced Latte", 15000),
            new Product(2, "Cappuccino", 20000),
            new Product(3, "Americano", 12000),
            new Product(4, "Red Velvet", 15000),
            new Product(5, "Croissant", 12000)
    );
}