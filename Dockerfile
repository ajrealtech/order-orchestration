FROM eclipse-temurin:17-jdk-alpine
VOLUME C:\dockerVol
ARG target\*.jar
COPY  target/*.jar order-orchestration.jar
ENTRYPOINT ["java","-jar","/order-orchestration.jar"]