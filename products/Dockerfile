FROM openjdk:8-jre
ADD target/javashop.products-latest.jar app.jar
ADD products.yml app-config.yml
EXPOSE 8020

ADD https://github.com/signalfx/splunk-otel-java/releases/latest/download/splunk-otel-javaagent-all.jar .

ENTRYPOINT java -javaagent:splunk-otel-javaagent-all.jar -Dsplunk.access.token=S4mEj3vnr9ev3kAdM-9kBQ -Dsplunk.realm=us1  -Dotel.traces.sampler=rules -Dotel.traces.sampler.arg=drop=healthcheck  -Dotel.resource.attributes=service.name=products,deployment.environment=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar  server app-config.yml

#Test Start: ENTRYPOINT java  -Dotel.resource.attributes=service.name=products,deployment.environment=${USERNAME}2_Apm_Instrumentation_Shop -jar app.jar  server app-config.yml

