FROM openjdk:21

WORKDIR /bot_app
COPY bot/target/bot.jar ./bot.jar

EXPOSE 8090 8082 9090 3000

ENTRYPOINT ["java", "-jar", "bot.jar"]
