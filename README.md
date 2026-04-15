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

# Spring Boot config

- Parancssori paraméter
- Környezeti változó
- `conf/application.properties` vagy `./application.properties`
- jar fájlon belüli `application.properties` állomány

# DB indítása

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432 --name employees-postgres postgres
```
