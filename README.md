# Account management service.

## How it run.

Run with **node**:
```
cd ./node
docker-compose up
```

Run with **Spring/Tomcat**:
```
cd ./tomcat
docker-compose up
```

Run with **Spring/Netty**:
```
cd ./netty
docker-compose up
```

## Project database schema.

![](./md/UserDomainApi.jpg)

## REST API

* POST /api/user-management/registration
* POST /api/user-management/login
* POST /api/user-management/validate-username
* POST /api/user-management/change-password
* GET  /api/user-management/users
* GET  /api/user-management/users/{id}

* POST /api/auth-management/authenticate-user

* POST /api/project-management/projects
* PUT /api/project-management/projects/{id}
* GET /api/project-management/projects
* GET /api/project-management/projects/{id}

* GET /api/project-management/projects/{id}/users
* GET /api/project-management/projects/{id}/users/{id}