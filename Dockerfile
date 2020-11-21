FROM openjdk:11

ADD target/demo-0.0.1-SNAPSHOT.jar ProducerSB.jar

ENTRYPOINT ["java", "-jar", "ProducerSB.jar"]