FROM maven:3.6-adoptopenjdk-11 as build

COPY / /build

WORKDIR /build

RUN mvn -f pom.xml clean package
RUN    mkdir -p /app
RUN    cp ./target/aims-license-generator-0.0.1-SNAPSHOT.jar /app
RUN    cp -r ./src/main/resources/* /app

FROM adoptopenjdk:11-jre

COPY --from=build /app /app

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/aims-license-generator-0.0.1-SNAPSHOT.jar"]


