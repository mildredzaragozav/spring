# Spring Security + JWT

This repository contains an implementation of Spring Security with JWT to protect RESTful endpoints.

Use Postman or Insomnia for below steps.

1. Hit the registration endpoint:
    - POST Request to: http://localhost:8080/api/v1/login/register
    - Body (JSON format):
   ```
       {
           "username": "any",
           "password": "any"
       }
   ```
    - Expected response: 200 OK - "User registered successfully"

2. Hit the login endpoint:
    - POST Request to: http://localhost:8080/api/v1/login
    - Body (JSON format):
     ``` 
	      {
	       	"username": "any",
	       	"password": "any"
	      }
     ```
    - Expected response: 200 OK - It will return a token similar to 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTc4MTY0NDY3NSwiZXhwIjoxNzgxNjgwNjc1fQ.AoJ4ec6MU-rf9U2WCZa4ZdDeJAudQb-_45hlDlP891U'


3. Hit the hello endpoint:
    - GET Request to: http://localhost:8080/api/v1/greetings/hello
    - Auth:
        - Select 'Bearer Token' and paste the token returned in step 2.

    - Expected response: 200 OK - "Hello, Secure World!"

This application uses an H2 in memory database.
