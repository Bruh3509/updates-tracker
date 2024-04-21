FROM openjdk:21

WORKDIR /scrapper_app
COPY scrapper/target/scrapper.jar ./scrapper.jar

EXPOSE 8080 8081 9090 3000

ENTRYPOINT ["java", "-jar", "scrapper.jar"]
