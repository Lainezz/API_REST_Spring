# API REST SpringBoot
---

```
{
  "name": "Diego",
  "email": "diegolinaresmojacar@gmail.com",
  "age": undefined
}
``` 

## Description

API Rest implemented with Spring for an inventory control app.

It includes CRUD methods for "productos" resource. Also, it includes an user authentication by Session.

This is implemented only for academic purposes. The implementation of the API should be improved and there are a lot of things that could have be done differently.

---

## DB

Implemented for a MySql database. 
It uses JPA Hibernate as ORM (included in the Spring environment)

---

#### Sample Data
Some examples can be found on this [page](/src/main/resources/requests)
URL to access swagger: http://localhost:8080/stockman

---

#### Dependencies
````
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.modelmapper:modelmapper:3.1.1")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
}
````

