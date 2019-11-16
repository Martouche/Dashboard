FROM gradle:5.6.3-jdk8
USER root
COPY . .
RUN gradle build
CMD java -jar build/libs/dashboard-0.0.1-SNAPSHOT.jar

EXPOSE 8080
