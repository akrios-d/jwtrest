# Exercise 1
1. Write a spring boot application.
2. The application should expose a REST and SOAP endpoint.
3. Both the rest and soap endpoints should take two integer arrays as input parameters.
4. Both endpoints that have been created then needs to combine the two arrays, remove the duplicates,and manually  sort  (candidate is not  allowed to use  any sorting utilities for this) the combined array from smallest to largest.
5. On every odd number, print out “I’m odd {number}” (where {number} is the actual number).
6. On every even number, print out “I’m even {number}“(where {number} is the actual number).
7. The endpoint needs to return a list of key-value pairs (from the combined array), where the key would be the number value from the array and the value would be whether the number is odd or even.

# Rest Order Utils
| Name| Endpoint | Type | Purpose | 
| ------ | ------ | ------ | ------ |
| OrderUtils | http://localhost:8080/api/v1/orderUtils?list1=42,47,5&list2=2,5,65,43 |  get | Order the 2 list without duplicate |

# SOAP Order Utils
WSDL can be founded at
http://localhost:8080/ws/sortingUtils.wsdl

# Exercise 2
1. Create another rest endpoint backed by any database instance of your choice (MongoDB, Postgres, MySQLetc.).
2. Using the jokes API(https://v2.jokeapi.dev/),  retrieve and populate  a table  within your database with 30 jokes.
3. The newly createdrest endpoint will be used to provide a client a list of 5 jokes stored within your database.
4. Persist how many times a specific joke has been provided to a client.
5. Ensure that you do not provide the same joke to the same client within a 5-minutetimeframe.
6. Each client can be identified using an "authentication" header

# Attention
For the first time uncomment like bellow to auto create tables at application.properties file
```
#drop n create table again, good for testing, comment this in production
spring.jpa.hibernate.ddl-auto=create
```

| Name| Endpoint | Type | Purpose | 
| ------ | ------ | ------ | ------ |
| Joke | http://localhost:8080/api/v1/joke |  get | Retrieve joke |
| Auth | http://localhost:8080/authenticate |  post | Retrieve JWT |
> Helpers endpoint

| Name| Endpoint | Type | Purpose | 
| ------ | ------ | ------ | ------ |
| Populate Jokes | http://localhost:8080/populatejokes |  get | Generate jokes to save in the DB |
| User Creation | http://localhost:8080/create |  post | Create a new user on DB |
Body Post
```
{
"username":"admin",
"password":"password"
}
```
# Logs
To adjusting output and logger config, please check at: *log4j.properties*
# Configurations at application.properties file
# Database
To modify to the database of your choice please change
- spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
- spring.datasource.username=postgres
- spring.datasource.password=1234

# JWT secret
To modify to the jwt.secret of your choice please change
- jwt.secret=secret
