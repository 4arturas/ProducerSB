FROM openjdk:11

ADD target/producer-0.0.1-SNAPSHOT.jar ProducerSB.jar

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar", "ProducerSB.jar"]