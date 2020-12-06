FROM openjdk:11

ADD target/producer-0.0.1-SNAPSHOT.jar ProducerSB.jar

ENTRYPOINT ["java", "-jar", "ProducerSB.jar"]