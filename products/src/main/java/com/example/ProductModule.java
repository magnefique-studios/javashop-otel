package com.example;

import com.google.inject.AbstractModule;

public class ProductModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(ProductResource.class);
    }
}
