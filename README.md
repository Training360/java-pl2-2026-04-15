# Java platform bemutatása üzemeltetőknek

## JDK telepítése Windowson

Oracle Disztribúció
https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html

```shell
java -version
```

Windows csomagkezelő:

https://chocolatey.org/

## Linux

SDKMan!

```shell
curl -s "https://get.sdkman.io" | bash

source "/root/.sdkman/bin/sdkman-init.sh"

sdk install java 25.0.2-tem
```

https://www.jtechlog.hu/2015/07/04/g1.html
https://www.jtechlog.hu/2011/12/30/java-memoriakezeles-szemetgyujto.html

## Fordítás, futtatás

```
javac Hello.java
java Hello
```

## Csomagolás

```
jar cvf hello.jar *.class
```

## Maven

```shell
mvn archetype:generate -DgroupId=training -DartifactId=hello-maven -DarchetypeArtifactId=hello-maven -Dpackage=hello -DinteractiveMode=false

mvn package
```

## Clone

```shell
git clone https://github.com/Training360/java-pl2-2026-04-15
git pull
```

## Spring Boot config

- Parancssori paraméter
- Környezeti változó
- `conf/application.properties` vagy `./application.properties`
- jar fájlon belüli `application.properties` állomány

## DB indítása

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432 --name employees-postgres postgres
```

## Táblák törlése

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

## Jakarta EE

```shell
curl -o postgresql-42.7.10.jar https://repo.maven.apache.org/maven2/org/postgresql/postgresql/42.7.10/postgresql-42.7.10.jar
```

```shell
jboss-cli.bat
```

```plain
# Kapcsolódás
connect

# Add PostgreSQL module
module add --name=com.postgresql --resources=postgresql-42.7.10.jar --dependencies=javax.api,javax.transaction.api

# Add PostgreSQL driver
/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=com.postgresql,driver-xa-datasource-class-name=org.postgresql.xa.PGXADataSource)

# Create DataSource
data-source add --name=EmployeeDS --jndi-name=java:/jdbc/EmployeeDS --driver-name=postgresql --connection-url=${env.DB_CONNECTION_URL:jdbc:postgresql://localhost:5432/employees} --user-name=${DB_USERNAME:employees} --password=${DB_PASSWORD:employees}
```

## RabbitMQ

https://www.jtechlog.hu/2020/09/11/rabbitmq.html

```shell
docker run -d --hostname my-rabbit --name my-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:4.3.0-management
```

http://localhost:15672/

Felhasználónév/jelszó: `guest` / `guest`

## Docker image készítése

```shell
mvn package
docker build -t employees-app:0.0.1 .
```

## Compose

```shell
docker compose up -d
docker compose down
```

## Alternatív Docker build

```shell
./mvnw spring-boot:build-image
```

## HA

```shell
docker compose -f docker-compose-ha.yaml  up -d
```

## Branching strategies

https://www.youtube.com/watch?v=U_IFGpJDbeU

## GitLab

```shell
git init
git add .
git commit -m "Initial commit"
git remote add origin https://gitlab.com/vicziani/employees-spring-spa.git
git push origin master
```

```shell
git update-index --chmod=+x mvnw
```

registry.gitlab.com/vicziani/employees-spring-spa:1.0.0

## Observability

```shell
docker run \
	--name lgtm \
	-p 3000:3000 \
	-p 4040:4040 \
	-p 4317:4317 \
	-p 4318:4318 \
	-p 9090:9090 \
	-d \
	grafana/otel-lgtm:latest
```

Elérhető: `http://localhost:3000`

```shell
curl -L -O https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
set JAVA_TOOL_OPTIONS="-javaagent:opentelemetry-javaagent.jar" 
set OTEL_TRACES_EXPORTER=otlp
set OTEL_METRICS_EXPORTER=otlp
set OTEL_LOGS_EXPORTER=otlp
set OTEL_METRIC_EXPORT_INTERVAL=15000
```

## Kubernetes

```shell
kind create cluster --name employees
kind load docker-image employees-app:1.0.0 --name employees
kubectl apply -f ./deployments
kubectl port-forward svc/employees-app 8080:8080
kind delete cluster --name employees
```