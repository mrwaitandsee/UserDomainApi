FROM openjdk:11

COPY tomcat-app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=docker"]