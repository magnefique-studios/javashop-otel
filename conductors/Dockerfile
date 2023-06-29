FROM openjdk:8-jre
ADD target/javashop.conductors-latest.jar app.jar
EXPOSE 8044


ADD https://github.com/signalfx/splunk-otel-java/releases/latest/download/splunk-otel-javaagent-all.jar .


#ENTRYPOINT java $JAVA_OPTS -javaagent:splunk-otel-javaagent-all.jar -Dotel.traces.sampler.arg=/healthcheck; -Dsplunk.profiler.call.stack.interval=10000 -Dsplunk.metrics.experimental.enabled=true -Dotel.exporter.otlp.endpoint=http://${OTEL_COLLECTOR}:4317 -Dsplunk.metrics.endpoint=http://${OTEL_COLLECTOR}:9943 -Dsplunk.profiler.enabled=true -Dsplunk.profiler.memory.enabled=true -Dotel.resource.attributes=service.name=conductors,deployment.environment=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar

ENTRYPOINT java $JAVA_OPTS -javaagent:splunk-otel-javaagent-all.jar -Dsplunk.profiler.call.stack.interval=10000 -Dsplunk.metrics.experimental.enabled=true -Dotel.exporter.otlp.endpoint=http://${OTEL_COLLECTOR}:4317 -Dsplunk.metrics.endpoint=http://${OTEL_COLLECTOR}:9943 -Dsplunk.profiler.enabled=true -Dsplunk.profiler.memory.enabled=true -Dotel.resource.attributes=service.name=conductors,deployment.environment=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar


