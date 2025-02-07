FROM openjdk:17-jdk-slim
ADD target/javashop.instruments-latest.jar app.jar
EXPOSE 8040


#ADD https://github.com/signalfx/splunk-otel-java/releases/latest/download/splunk-otel-javaagent-all.jar .

ENV JAVA_OPTS="-Xms2048M -Xmx2048M"

ENTRYPOINT java $JAVA_OPTS  -Dsplunk.access.token=${TOKEN} -Dsplunk.realm=${REALM} -Dotel.traces.sampler=rules -Dotel.traces.sampler.arg=drop=healthcheck,drop=ingest -Dotel.resource.attributes=service.name=instruments,deployment.environment=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar


#ENTRYPOINT java $JAVA_OPTS -javaagent:splunk-otel-javaagent-all.jar -Dsplunk.access.token=${TOKEN} -Dsplunk.realm=${REALM} -Dotel.traces.sampler=rules -Dotel.traces.sampler.arg=drop=healthcheck,drop=ingest -Dotel.resource.attributes=service.name=instruments,deployment.environment=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar


