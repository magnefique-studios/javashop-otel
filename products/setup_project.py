import os
import zipfile

# Define project structure
project_name = "dropwizard-guice-service"
file_structure = {
    "src/main/java/com/example/DropwizardApplication.java": """\
package com.example;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardApplication extends Application<DropwizardConfiguration> {

    private GuiceBundle<DropwizardConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        guiceBundle = GuiceBundleProvider.createGuiceBundle();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) {
        // Guice manages dependencies
    }
}
""",
    "src/main/java/com/example/DropwizardConfiguration.java": """\
package com.example;

import io.dropwizard.Configuration;

public class DropwizardConfiguration extends Configuration {
    // Custom configurations can be added here
}
""",
    "src/main/java/com/example/Product.java": """\
package com.example;

public class Product {
    private String id;
    private String name;
    private double price;

    public Product() {}

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
""",
    "src/main/java/com/example/ProductService.java": """\
package com.example;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
}
""",
    "src/main/java/com/example/ProductServiceImpl.java": """\
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
""",
    "src/main/java/com/example/ProductResource.java": """\
package com.example;

import com.google.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductService productService;

    @Inject
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
""",
    "src/main/java/com/example/ProductModule.java": """\
package com.example;

import com.google.inject.AbstractModule;

public class ProductModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(ProductResource.class);
    }
}
""",
    "src/main/java/com/example/GuiceBundleProvider.java": """\
package com.example;

import com.hubspot.dropwizard.guice.GuiceBundle;

public class GuiceBundleProvider {
    public static GuiceBundle<DropwizardConfiguration> createGuiceBundle() {
        return GuiceBundle.<DropwizardConfiguration>newBuilder()
                .addModule(new ProductModule())
                .setConfigClass(DropwizardConfiguration.class)
                .build();
    }
}
""",
    "src/main/resources/config.yml": """\
server:
  applicationConnectors:
    - type: http
      port: 8080
  adminConnectors:
    - type: http
      port: 8081
""",
    "pom.xml": """\
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>dropwizard-guice-service</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-core</artifactId>
            <version>2.1.0</version>
        </dependency>
        <dependency>
            <groupId>com.hubspot.dropwizard</groupId>
            <artifactId>dropwizard-guice</artifactId>
            <version>1.0.4.0</version>
        </dependency>
        <dependency>
            <groupId>com.google.inject</groupId>
            <artifactId>guice</artifactId>
            <version>5.1.0</version>
        </dependency>
        <dependency>
            <groupId>jakarta.ws.rs</groupId>
            <artifactId>jakarta.ws.rs-api</artifactId>
            <version>3.0.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.3.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
""",
    "Dockerfile": """\
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/dropwizard-guice-service-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar", "server", "config.yml"]
EXPOSE 8080
"""
}

# Create directories and files
for filepath, content in file_structure.items():
    full_path = os.path.join(project_name, filepath)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w") as f:
        f.write(content)

# Create a ZIP file
zip_filename = f"{project_name}.zip"
with zipfile.ZipFile(zip_filename, 'w', zipfile.ZIP_DEFLATED) as zipf:
    for root, _, files in os.walk(project_name):
        for file in files:
            full_path = os.path.join(root, file)
            zipf.write(full_path, os.path.relpath(full_path, project_name))

print(f"Project '{project_name}' has been created and zipped as '{zip_filename}'.")