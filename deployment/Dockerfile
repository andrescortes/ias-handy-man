FROM adoptopenjdk/openjdk11-openj9:alpine-slim
VOLUME /tmp
COPY *.jar hours-calculator.jar
ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
# Replace with a non-root user to avoid running the container with excessive privileges
USER appuser
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar hours-calculator.jar" ]

#build a version from openjdk with your configurations, its similar a image o app
FROM openjdk:11.0.16-jdk-slim-buster as builder

WORKDIR /app/msvc-courses

COPY ./pom.xml /app

COPY ./msvc-courses/.mvn ./.mvn

COPY ./msvc-courses/mvnw .

COPY ./msvc-courses/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/ #only download dependencies, one time
# other way
#RUN ./mvnw dependency:go-offline
COPY ./msvc-courses/src ./src

RUN ./mvnw clean package -DskipTests

#start new image begin with image
FROM openjdk:11.0.16-jdk-slim-buster
#create a new directory
WORKDIR /app
#create a dir to save logs, preview config this in application.yml or properties logging...
RUN mkdir ./logs
#our current path is /app, then copy from our image o apk called "builder" with the file "msvc-users-0.0.1-SNAPSHOT.jar" of our app
COPY --from=builder /app/msvc-courses/target/msvc-courses-0.0.1-SNAPSHOT.jar .

EXPOSE 8002
#entrypoint is Strict
#ENTRYPOINT ["java", "-jar", "msvc-users-0.0.1-SNAPSHOT.jar"]

#CMD is mutable
#CMD sleep 10 && java -jar msvc-courses-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "msvc-courses-0.0.1-SNAPSHOT.jar"]
