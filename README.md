# JDK telepítése Windowson

Oracle Disztribúció
https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html

java -version

Windows csomagkezelő:

https://chocolatey.org/

# Linux

SDKMan!

curl -s "https://get.sdkman.io" | bash

source "/root/.sdkman/bin/sdkman-init.sh"

sdk install java 25.0.2-tem

https://www.jtechlog.hu/2015/07/04/g1.html
https://www.jtechlog.hu/2011/12/30/java-memoriakezeles-szemetgyujto.html

# Fordítás, futtatás

```
javac Hello.java
java Hello
```

# Csomagolás

```
jar cvf hello.jar *.class
```

# Maven

mvn archetype:generate -DgroupId=training -DartifactId=hello-maven -DarchetypeArtifactId=hello-maven -Dpackage=hello -DinteractiveMode=false

mvn package

# Clone

```shell
git clone https://github.com/Training360/java-pl2-2026-04-15
git pull
```

# Spring Boot config

- Parancssori paraméter
- Környezeti változó
- `conf/application.properties` vagy `./application.properties`
- jar fájlon belüli `application.properties` állomány

# DB indítása

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432 --name employees-postgres postgres
```

# Táblák törlése

```shell
docker exec -it employees-postgres  bash
```

```shell
psql -U employees -d employees
```

```sql
drop table databasechangelog;
drop table databasechangeloglock;
drop table employees;
drop table log_entries;
```

# Jakarta EE

`curl -o postgresql-42.7.10.jar https://repo.maven.apache.org/maven2/org/postgresql/postgresql/42.7.10/postgresql-42.7.10.jar`

```shell
jboss-cli.bat
```

```
# Kapcsolódás
connect

# Add PostgreSQL module
module add --name=com.postgresql --resources=postgresql-42.7.10.jar --dependencies=javax.api,javax.transaction.api

# Add PostgreSQL driver
/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=com.postgresql,driver-xa-datasource-class-name=org.postgresql.xa.PGXADataSource)

# Create DataSource
data-source add --name=EmployeeDS --jndi-name=java:/jdbc/EmployeeDS --driver-name=postgresql --connection-url=${env.DB_CONNECTION_URL:jdbc:postgresql://localhost:5432/employees} --user-name=${DB_USERNAME:employees} --password=${DB_PASSWORD:employees}
```

# RabbitMQ

```shell
docker run -d --hostname my-rabbit --name my-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:4.3.0-management
```

http://localhost:15672/

Felhasználónév/jelszó: `guest` / `guest`

# Docker image készítése

```shell
mvn package
docker build -t employees-app:0.0.1 .
```

