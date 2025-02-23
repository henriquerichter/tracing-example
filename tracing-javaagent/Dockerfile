FROM eclipse-temurin:21.0.6_7-jdk-alpine-3.21 AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package

FROM eclipse-temurin:21.0.6_7-jre-alpine-3.21
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build /usr/app/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
COPY --from=build $JAR_FILE /app.jar
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=90.0 -XX:InitialRAMPercentage=90.0"
ENTRYPOINT ["java","-javaagent:opentelemetry-javaagent.jar","-XshowSettings:vm","-jar","/app.jar"]
