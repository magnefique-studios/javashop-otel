package com.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class DropwizardApplication extends Application<DropwizardConfiguration> {

    public static void main(String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .modules(new ProductModule())
                .build());
    }

    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) {
        // Application startup logic
    }
}