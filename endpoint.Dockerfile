FROM maven:3.6.3-openjdk-11 AS build
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package -Pprod
FROM openjdk:11
COPY --from=build /home/app/endpoint/target/endpoint-1.0.0.jar endpoint-1.0.0.jar
EXPOSE 8080
EXPOSE 5432
ENTRYPOINT ["java", "-jar", "endpoint-1.0.0.jar"]
