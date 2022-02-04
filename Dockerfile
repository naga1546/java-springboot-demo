FROM maven:3.5-jdk-8-alpine as build
RUN mkdir -p /app
WORKDIR /app
#ONBUILD ADD . /app
#ONBUILD RUN mvn install
#COPY --from=clone /app/springboot-docker-demo /app
ADD . /app
#RUN mvn install # This step is not required if done manually

#FROM openjdk:8-jre-alpine
FROM java:8
WORKDIR /app
COPY --from=build /app/target/springboot-docker-demo-0.0.1-SNAPSHOT.jar /app/app.jar
#CMD ["java -jar /app/app.jar"]
RUN bash -c 'touch /app/app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]

# Open Ports
EXPOSE 8080