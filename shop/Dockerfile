FROM openjdk:8-jre
ADD target/javashop.shop-latest.jar ./app.jar
EXPOSE 8010

#EXPOSE 8001

ADD https://github.com/signalfx/splunk-otel-java/releases/latest/download/splunk-otel-javaagent-all.jar .

#ENTRYPOINT java -javaagent:splunk-otel-javaagent-all.jar -Dsplunk.access.token=${TOKEN} -Dsplunk.realm=${REALM} -Dotel.resource.attributes=service.name=shop,deployment.environment=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar

ENTRYPOINT java $JAVA_OPTS -javaagent:splunk-otel-javaagent-all.jar -Dotel.exporter.otlp.endpoint=http://${OTEL_COLLECTOR}:4317  -Dsplunk.metrics.endpoint=http://${OTEL_COLLECTOR}:9943 -Dsplunk.profiler.tracing.stacks.only=true -Dsplunk.profiler.enabled=true -Dsplunk.profiler.memory.enabled=true -Dotel.resource.attributes=service.name=shop,deployment.environment=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar


# Test Start: ENTRYPOINT java  -Dotel.resource.attributes=service.name=shop,deployment.environment=${USERNAME}2_Apm_Instrumentation_Shop -jar app.jar
