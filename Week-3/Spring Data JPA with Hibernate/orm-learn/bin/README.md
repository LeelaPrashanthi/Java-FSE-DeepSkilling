# orm-learn

Demo project for Spring Data JPA and Hibernate

## Prerequisites
- Java 8+
- Maven 3.6+
- MySQL Server 8.0

## Database Setup
1. Start MySQL and log in as root:
   ```
   mysql -u root -p
   ```
2. Create the schema and table:
   ```sql
   create schema ormlearn;
   use ormlearn;
   create table country(co_code varchar(2) primary key, co_name varchar(50));
   insert into country values ('IN', 'India');
   insert into country values ('US', 'United States of America');
   ```

## Configuration
- Edit `src/main/resources/application.properties` if your MySQL username/password differs from `root`/`root`.

## Build
```
mvn clean package
```

## Run
- Run the main class `com.cognizant.ormlearn.OrmLearnApplication` from your IDE or with:
```
mvn spring-boot:run
```
- Check the logs for output from the `testGetAllCountries()` method.

## Project Structure
- `src/main/java/com/cognizant/ormlearn/model/Country.java` - Entity class
- `src/main/java/com/cognizant/ormlearn/repository/CountryRepository.java` - Repository interface
- `src/main/java/com/cognizant/ormlearn/service/CountryService.java` - Service class
- `src/main/java/com/cognizant/ormlearn/OrmLearnApplication.java` - Main application
- `src/main/resources/application.properties` - Configuration 