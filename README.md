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

