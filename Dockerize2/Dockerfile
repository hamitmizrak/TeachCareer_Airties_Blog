#FROM eclipse-temurin:11 as build

FROM adoptopenjdk/openjdk11
ARG JAR_FILE=TeachCareerAirtiesBlog-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} daily.jar
ENTRYPOINT [ "java","-jar","/daily.jar" ]


