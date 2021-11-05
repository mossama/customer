FROM openjdk:11-slim
RUN apt update
RUN apt install -y sqlite3 libsqlite3-dev
COPY target/customer-0.0.1-SNAPSHOT.jar customer-0.0.1-SNAPSHOT.jar 
COPY sample.db .
EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","customer-0.0.1-SNAPSHOT.jar"]
