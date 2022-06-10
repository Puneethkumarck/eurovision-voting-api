[![CI](https://github.com/Puneethkumarck/eurovision-voting-api/actions/workflows/main.yml/badge.svg)](https://github.com/Puneethkumarck/eurovision-voting-api/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Puneethkumarck_eurovision-voting-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Puneethkumarck_eurovision-voting-api)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Puneethkumarck_eurovision-voting-api&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Puneethkumarck_eurovision-voting-api)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Puneethkumarck_eurovision-voting-api&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Puneethkumarck_eurovision-voting-api)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Puneethkumarck_eurovision-voting-api&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Puneethkumarck_eurovision-voting-api)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Puneethkumarck_eurovision-voting-api&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=Puneethkumarck_eurovision-voting-api)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Puneethkumarck_eurovision-voting-api&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Puneethkumarck_eurovision-voting-api)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Puneethkumarck_eurovision-voting-api&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Puneethkumarck_eurovision-voting-api)

# eurovision-voting-api

Assumptions

* Database of our choice (For Simplicity selected H2 database)
* Build Database schema as per functional requirement 
* Build 3 client facing endpoints as per functional requirements
* Build Service and repository layers to fetch data and perform business logic
* Write tests for each layer (Web/service/repository)
* Add Input validation and exception handlers
* Add API documentation with Spring Rest Docs
* Add Docker file to create docker image and deploy in a kubernetes env
* Create an helm chart to deploy voting app (which contains K8s Manifests)
* No Specific requirements for API security

### Pre requisite installation

* Java 11
* Gradle
* kubernetes docker for desktop/minikube
* Helm

### Tech
* Java 11
* Springboot
* Spring-Data-Jpa
* Spring-Boot-Actuator
* Spring-rest-docs
* Lombok
* Gradle
* kubernetes
* Docker
* Helm


### Build

```
Build application which produces snapshot Jar

./gradlew build

Build docker image 

docker build -t pega/votingapi:v1 .
```

### Run

```
without Docker/helm -
./gradlew bootRun

With Docker/Helm

helm template charts
helm lint charts
helm install pegarelease charts
helm upgrade -i pegarelease . (incremental deployments)
```

## Postman

App is deployed in heroku and available under https://eurovision-voting-api.herokuapp.com

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/685178-9a661b0a-87f7-4b9e-a6c2-67834043ed0a?action=collection%2Ffork&collection-url=entityId%3D685178-9a661b0a-87f7-4b9e-a6c2-67834043ed0a%26entityType%3Dcollection%26workspaceId%3De03b2ab3-447a-4a5f-8818-be163b36a6e7)


## Spring rest docs

![img.png](images/restdoc1.png)
![img.png](images/restdoc2.png)
![img.png](images/restdoc3.png)


## Sql Queries and Results with initial data setup

```
SELECT VOTED_FOR,count(VOTED_FOR) as total FROM VOTE WHERE VOTING_YEAR = '2022' group by VOTED_FOR order by count(VOTED_FOR) DESC limit 3

SELECT VOTED_FOR , count(VOTED_FOR) as total FROM VOTE WHERE VOTING_YEAR ='2022' and COUNTRY_FROM='Netherlands' group by VOTED_FOR order by count(VOTED_FOR) DESC limit 3
```

### Top three countries with maximum votes for given year
![img.png](images/topthreevotes_2022.png)

### Top three Fav songs from each country and given year
![img.png](images/Favsongs_from_eachcountry.png)

<embed src="/src/main/asciidoc/index.pdf" type="application/pdf">
