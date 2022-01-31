FROM openjdk:11-jre-slim
EXPOSE 8080
COPY target/weather-dojo-1.0.0.jar weather-dojo.jar
ENTRYPOINT ["java", "-jar", "weather-dojo.jar"]
