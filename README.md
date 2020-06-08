# Getting Started

### Requirements:

* PostgreSQL Server (please check application.properties file to verify credentials)
* Maven

### How to init database

* cd scripts
* psql -U postgres postgres -f initDb.sql

### How to start

* mvn clean package
* java -jar target/burenok-0.0.1-SNAPSHOT.war

### Result
* [Running web application](http://localhost:8040/contacts/list)
