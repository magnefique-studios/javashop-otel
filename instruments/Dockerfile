FROM openjdk:15
ADD target/javashop.instruments-latest.jar app.jar
EXPOSE 8040

ADD 'https://dtdg.co/latest-java-tracer' dd-java-agent.jar

ENV JAVA_OPTS="-Xms2048M -Xmx2048M"

ENTRYPOINT java $JAVA_OPTS  -javaagent:dd-java-agent.jar \
  -Ddd.profiling.enabled=true \
  -XX:FlightRecorderOptions=stackdepth=256 \
  -Ddd.agent.host=datadog \
  -Ddd.logs.injection=true \
  -Ddd.trace.sample.rate=1 \
  -Ddd.appsec.enabled=true \
  -Ddd.iast.enabled=true  \
  -Ddd.service=instruments \
  -Ddd.env=${USERNAME}_xApm_Instrumentation_Shop -jar app.jar
