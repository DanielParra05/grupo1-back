# java image to use
FROM openjdk:8-jdk-alpine
# Copy jar from target dir to docker image 
ADD target/*.jar app.jar
# Run app
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ] 
