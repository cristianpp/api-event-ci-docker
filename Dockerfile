FROM openjdk:8-jdk-alpine

WORKDIR /usr/src/app

COPY . .

RUN ./gradlew build

ARG JAR_FILE=/usr/src/app/build/libs/\*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar", "app.jar"]
