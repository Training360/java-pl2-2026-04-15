# Java platform (Java SE/Java EE) bemutatása üzemeltetőknek

## PostgreSQL adatbázis

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432 --name employees-postgres postgres
```

## WildFly alkalmazásszerver

* Red Hat/JBoss
* Nyílt forráskódú
* Letölthető a [http://www.wildfly.org/](http://www.wildfly.org/) címről
* Kicsomagolható, és a `bin\standalone.bat` állománnyal indítható
