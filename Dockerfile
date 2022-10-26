FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE=out/artifacts/TeachCareerAirtiesBlog_jar/TeachCareerAirtiesBlog.jar
ADD ${JAR_FILE} application.jar
ENTRYPOINT ["java","jar","/application.jar"]