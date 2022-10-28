FROM openjdk:11-jdk

RUN mkdir -p /home/ToDoListService

COPY . /home/ToDoListService

WORKDIR /home/ToDoListService

EXPOSE 8400

CMD ["./gradlew", "clean", "bootRun"]
