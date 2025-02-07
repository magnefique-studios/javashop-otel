package com.shabushabu.javashop.products.resources;

import com.google.inject.AbstractModule;
import com.shabushabu.javashop.products.configuration.ProductServiceConfiguration;

public class ProductModule extends AbstractModule {
    private final ProductServiceConfiguration configuration;

    public ProductModule(ProductServiceConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        // Bind your dependencies here
        bind(ProductServiceConfiguration.class).toInstance(configuration);
        // If you have any services/repositories, bind them here
        // bind(ProductService.class).to(ProductServiceImpl.class);
        // bind(ProductRepository.class).to(ProductRepositoryImpl.class);
    }
}