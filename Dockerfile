FROM openjdk:11

ADD target/demo-0.0.1-SNAPSHOT.jar ProducerSB.jar

RUN ["java", "-jar", "ProducerSB.jar"]