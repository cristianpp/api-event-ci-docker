FROM openjdk:8-jdk-alpine

WORKDIR /usr/src/app

COPY . .

RUN ./gradlew build

RUN pwd

RUN ls -la
#ARG JAR_FILE=build/libs/\*.jar

#COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar", "app.jar"]
