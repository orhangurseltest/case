
### Setup
Before starting the application, mongodb should be running. In /docker/mongodb directory mongodb has own docker-compose file.
This file also start mongo-express for gui, localhost:8081.

It starts with -> ```docker-compose up.```

For building DockerFile -> ```docker build -t reading-is-good . ```

Application will start in 8080 port so, ``` docker run -p 8080:8080 reading-is-good --name getirContainer ```

### Default Login User
```
username=getir
password=getir1234Ab
```
### Usage

First of all, should get token with default username and password. For getting token url,

```localhost:8080/authenticate/token```
 
### Documentation
For swagger, It can be reachable without authentication

```http://localhost:8080/swagger-ui.html```
