FROM openjdk:8-jre-alpine

ADD target/tdc-write-service-0.1.0-SNAPSHOT-standalone.jar /filez-service.jar

VOLUME /filez

EXPOSE 3000

CMD ["java", "-jar", "/filez-service.jar"]
