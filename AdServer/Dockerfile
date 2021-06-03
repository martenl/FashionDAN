#FROM maven:3.6-jdk-11 AS build
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11

ENV VERTICLE_HOME /usr/verticles
#COPY --from=build /usr/src/app/target/adServer-jar-with-dependencies.jar $VERTICLE_HOME/app.jar
COPY adServer-fat.jar $VERTICLE_HOME/app.jar
COPY src/config/conf.json $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.SLF4JLogDelegateFactory -jar app.jar -conf conf.json"]
