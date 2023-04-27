FROM openjdk:11
WORKDIR /app
COPY . /app
RUN ./gradlew build
CMD ["java", "-jar", "/app/build/libs/boardProject.jar"]
