FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim

LABEL maintainer="Brian Schalme <bschalme@airspeed.ca>"

COPY . /opt/tsheets-proxy/
WORKDIR /opt/tsheets-proxy/
RUN ./gradlew build && \
  cp ./build/libs/tsheets-proxy-*-all.jar /opt/tsheets-proxy.jar && \
  rm -rf /opt/tsheets-proxy/* && \
  rm -rf ~/.gradle/

WORKDIR /opt
EXPOSE 8080
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar tsheets-proxy.jar