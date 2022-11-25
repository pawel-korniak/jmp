# Module JDBC

### 1. Task 1
Description
Please, complete the following task.


Create simple database with tables:
- Users (id, name, surname, birthdate);
- Friendships (userid1, userid2, timestamp);
- Posts (id, userId, text, timestamp);
- Likes (postid, userid, timestamp).
Populate tables with data which are make sense (> 1 000 users, > 70 000 friendships, > 300 000 likes in 2025). *
Program should print out all names (only distinct) of users who has more than 100 friends and 100 likes in March 2025. Implement all actions (table creation, insert data and reading) with SpringJDBC.
You could prepare dictionaries (maps) in memory (with usernames for example) or data in files to generate data for the populating process.


### ~~2. Task 2~~
Description
Please, complete the following task.

1 point.

Create a Highload Writing Console Tool that populates the database (URL/Name are configuration settings).

Required functionality:

It creates N random tables with random unique name (or names from dictionary) and K random columns with type taken from Z random types.
It creates m random rows for the i-th table, where m is an i-th element of M. M is an N-dimensional array predefined by a user of this tool.
It supports the table creation/populating via L concurrent connections (from different threads or from a few instances of classes running simultaneously).
All settings are located in a configuration file; the path to this file is a parameter of main() function.
Discuss with mentor how to improve performance of the suggested solution. 

### ~~3. Task 3 (Optional)~~
Description
Please, complete the following task.

1 point.

Create a Database Copy Console Tool that copies the database (URL/Name are command line parameters) step-by-step (table by table).

Required functionality:

Copying of tables in lexicographic order.
Rows in direct or reverse order (command line parameter).
It works for 10 GB database in minimal time (should tune performance using Java and database performance features).
The solution is delivered with a test database (populated with a huge volume of data).
10 GB sample database could be generated via Highload Writing Tool.


### ~~4. Task 4 (Optional)~~
Description
Please, complete the following task.

1 point.

Add five DBUnit tests to appropriate project (pet project) or another. Prepare test datasets if it is required.


### 5. Task 5



Description
Please, complete the following task.

1 point.

Take the existing (or write from zero) JDBC solution with a few CRUD operations and more complex SQL (for simple report generation) and migrate it to stored procedures. *
Write SQL script to create those stored procedures with Java style parameters and specific external names. **
Write a test which drops all stored procedures and creates a few of them via Java code.
Also, provide the script to print out all stored procedure in your database and dropping them for test purposes, for example.
Check that the application works properly, all test are green and so on.
Compare the performance of two solutions; explain to your mentor the benefits or disadvantages of storage procedure usage for the taken application.
3-5 tables with CRUD operations and two complex SELECTs can be enough.
Use MySQL or PostgreSQL or Oracle.

### (what is procedure?)

### 6. Task 6 (Optional)
Description
Please, complete the following task.

2 points.

If your database * has pre-defined storage procedure, use a few of them to print out interesting information or maintain something in the database.

Use MySQL or PostgreSQL or Oracle.


### 7. Task 7
Description
Please, complete the following task.

1 point.

Implement the next use cases of File Share application:

Save file to the database.
Retrieve file from the database.
Optional: file expiration.
Large files should be supported (size up to 200 MB).

Acceptance criteria:

File Share database schema is developed:
DB schema diagram is provided (5 points);
stored procedures for saving and retrieving files from DB are created (5 points).
DAO on JDBC is implemented:
DAO methods that are not used in proposed use cases can throw UnsupportedOperationException (2 points);
CallableStatement is used to call DB stored procedures (3 points);
large binary files are retrievable from DB (5 points).
Think about pros and cons of stored procedures usage comparing to SQL statement stored in Java code. Describe what difficulties you’ve faced when working with large binary files. Make demo via console interface or via special main method.

### (do DB holds files by default?)

### ~~8. Task 8 (Optional)~~
Description
Please, complete the following task.

1 point.

Write your own annotation-based or XML-based JabaORM that parses specific class and generates SQL-queries for CRUD (or SCRUD) operations.

Your mini-ORM should have one entry point, which supports CRUD operations for parsed class passed as a parameter in

.save
.load
.update
.delete
methods.
Implement all actions via RowSet if it is possible.

# Module Kafka

### Task description in [pdf](HW.pdf)

# Module Spring testing

### Main task
Description
Please, complete the following task.

Turn booking service controllers into REST endpoints, returning domain objects directly intead of ModelAndView objects. (3 points)
Implement asynchronous ticket booking. Create JMS consumer which will listen to a particular queue, receive booking messages and process them by adding appropriate database records. (3 points)
Configure Spring JMS infrastructure. (1 point)
Create integration tests that verify asynchronous booking, mock JMS provider using at least 2 of the approaches shown during last Spring Test lecture. (3 points)

# Module Spring boot

### ~~Task 1~~
Description
Please, complete the following task.

1 point.

Using https://start.spring.io create a Spring-boot app.
Create CommandLineRunner and output 'hello world'.
Start your application.
Check that spring context is up and there is 'hello world' message in console.

### ~~Task 2~~
Description
Please, complete the following task

1 point.

Create app that should support create, read, update and delete operations for some entity.
Use Spring Data module.
Don't use Spring Data REST starter.

### Task 3
Description
Please, complete the following task

1 point.

Implement authentication and authorization mechanism.
OAuth2 should be used.
JWT Token should be used.

### Task 4 (Optional)
Description
Please, complete the following task

1 point.

Should support different environments - local, dev, stg, prod.
Spring profiles.
Each environment - different db properties.

### ~~Task 5~~
Description
Please, complete the following task

1 point.

Add tool for migrating data.
Flyway or Liquibase.

### ~~Task 6 (Optional)~~
Description
Please, complete the following task

1 point.

Enable actuator.
Implement a few custom health indicators.
Implement a few custom metrics using Prometheus.

### Task 7
Description
Please, complete the following task

1 point.

In memory db must be used for testing purpose.
Implement repository testing.
Implement unit tests.
Implement tests for RestController using mock mvc.
Implement integration tests.

# TODO:

# Roadmap of Demonstration
[toc]
## Useful links
* [Jacoco](https://www.jacoco.org/jacoco/)
* [SonarQube](https://www.sonarqube.org/)
* [SpotBugs](https://spotbugs.github.io/)
* [PMD](https://pmd.github.io/)
* [OWASP](https://www.owasp.org/index.php/OWASP_Dependency_Check)
## Points at issue:
- ***why do we use SonarQube unless have a pipeline?***
  Instead of it we can use Jacoco locally to check  code coverage.
  SoanrQube can be used as Quality Gate in place.
- ***How about security checks as dedicated part of quality ?***
<details><summary>Not free tools</summary>
https://snyk.io/
https://www.veracode.com/
</details>
## Demo
### Quality
* In most cases projects have `Non-Functionals and SLA's` requirements with clear and have specific numbers.
<details><summary>Code Coverage and Quality Gate recomendations</summary>
* Code Coverage:
    **You should decide how much the amount of code and which parts of application should be covered with unit testing.**
    for example:
    - business layer > 95%
    - total coverage > 60%.
* Quality Gate:
    **You should decide when and how often it should be run.**
    Of course it depends on your branch strategy and other conditions
    for example:
    - in dev branch before merging to master branch
    - in master branch before release 
</details>
<details><summary>How to run?</summary>
You can use maven profiles for this purpose - we have created this one `qualityWithoutSnyk`. 
```
mvn clean package -PqualityWithoutSnyk -DskipTests
```
</details>
#### [Jacoco](https://www.jacoco.org/jacoco/)
We use [Jacoco library](https://www.jacoco.org/jacoco/) to check coverage locally. 
It very fast and convenient way which does not required waiting CI/CD pipeline/s with SonarQube.
<details><summary>How to run?</summary>
```
cd <any-app>
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false
```
</details>
#### [SonarQube](https://www.sonarqube.org/)
>Continuous Inspection
>
>SonarQube provides the capability to not only show health of an application but also to highlight issues newly introduced. With a Quality Gate in place, you can fix the leak and therefore improve code quality systematically.
#### [SpotBugs](https://spotbugs.github.io/)
> SpotBugs is a program which uses static analysis to look for bugs in Java code
<details><summary>Would like to run spotbugs gui?</summary>
```
cd webapp
mvn spotbugs:gui -PqualityWithoutSnyk
```
</details>
#### [PMD](https://pmd.github.io/)
> An extensible cross-language static code analyzer
#### [OWASP](https://www.owasp.org/index.php/OWASP_Dependency_Check)
> Dependency-Check is a utility that identifies project dependencies and checks if there are any known, publicly disclosed, vulnerabilities
<details><summary>Where I can see report?</summary>
```
<app-folder>\target\dependency-check-report.html
```
</details>