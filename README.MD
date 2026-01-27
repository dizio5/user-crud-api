# Users CRUD Application using Spring Boot

This project consists of an API implementation using REST principles. It also involves CRUD methods to modify users data through PostgreSQL database.
To run properly this project, you need to install maven and PostgreSQL. Run a local hosted database and add credentials to venv. Otherwise, you can hard-code them on properties.yaml (Not recommended).

Future features:
- JWT implementation
- More endpoints
- Frontend

## How to use:
Make sure to install al maven dependencies by using mvn clean install

Turn on your PostgreSQL database. Set environment variables in IntelliJ IDEA for DB_NAME, DB_USERNAME, and DB_PASSWORD of your choice.

Now, send requests to the API, you can use cURL or Postman. Send them to the url: http://localhost:8080/users/

You can create users, find an user by id, delete user, get users by count, and more.

You will receive responses in JSON format.

### GET

![GET](images/get.jpg)

### POST

![POST](images/post.jpg)
