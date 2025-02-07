package com.example;

import com.google.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@Singleton
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts() {
        return Arrays.asList(
            new Product("1", "Laptop", 1200.99),
            new Product("2", "Smartphone", 799.49),
            new Product("3", "Tablet", 399.99)
        );
    }
}
