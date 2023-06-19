package com.shabushabu.javashop.conductors.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


import javax.sql.DataSource;

@Component
public class DataGenerator {

    @Autowired
    private DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
       
    }
}



