FROM maven:3.5-jdk-8-alpine as build
RUN mkdir -p /app
ADD . /app
WORKDIR /app
ONBUILD ADD pom.xml /app
#ONBUILD RUN mvn install
#COPY --from=0 /app/springboot-docker-demo /app
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/springboot-docker-demo-0.0.1-SNAPSHOT.jar /app
CMD ["java -Djava.security.egd=file:/dev/./urandom -jar springboot-docker-demo-0.0.1-SNAPSHOT.jar"]

#FROM java:8
#VOLUME /app
##ADD springboot-docker-demo-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

# Open Ports
EXPOSE 8080