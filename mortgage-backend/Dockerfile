FROM openjdk:17
ARG JAR_FILE=target/mortgage-backend.jar
COPY ${JAR_FILE} mortgage-backend.jar
ENTRYPOINT ["java","-jar","/mortgage-backend.jar"]