FROM openjdk:11

ADD target/producer-0.0.1-SNAPSHOT.jar ProducerSB.jar
EXPOSE 8080 5005

ENTRYPOINT exec java $JAVA_OPTS -jar ProducerSB.jar