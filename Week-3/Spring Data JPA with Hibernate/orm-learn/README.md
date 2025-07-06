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
- Edit `src/main/resources/application.properties` if your MySQL username/password differs from `root`/`123456789`.
- The application uses `spring.jpa.hibernate.ddl-auto=update` to automatically create/update tables.

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

## Features Implemented (Hands-on 5)

### Country Management Services
The application demonstrates the following Spring Data JPA features:

1. **Find a country based on country code**
   - `findCountryByCode(String code)`

2. **Add new country**
   - `addCountry(Country country)`

3. **Update country**
   - `updateCountry(String code, Country countryDetails)`

4. **Delete country**
   - `deleteCountry(String code)`

5. **Find list of countries matching a partial country name**
   - `findCountriesByNameContaining(String name)`
   - `searchCountriesByName(String name)` (using @Query annotation)
   - `findCountriesStartingWith(String prefix)`

### Repository Methods
- **Query Methods**: Spring Data JPA automatically generates queries based on method names
- **@Query Annotation**: Custom JPQL queries for complex searches
- **Case-insensitive searches**: Using `IgnoreCase` in method names

### Data Population
- The application automatically populates the country table with 249 countries from around the world
- Data is loaded from `src/main/resources/data.sql` on application startup

### Testing
When you run the application, it will automatically test all features:
- Retrieving all countries
- Finding countries by code
- Adding a test country
- Updating the test country
- Searching countries by partial name
- Finding countries starting with specific letters
- Deleting the test country

## Project Structure
- `src/main/java/com/cognizant/ormlearn/model/Country.java` - Entity class
- `src/main/java/com/cognizant/ormlearn/repository/CountryRepository.java` - Repository interface with custom queries
- `src/main/java/com/cognizant/ormlearn/service/CountryService.java` - Service class with all CRUD operations
- `src/main/java/com/cognizant/ormlearn/OrmLearnApplication.java` - Main application with test methods
- `src/main/resources/application.properties` - Configuration
- `src/main/resources/data.sql` - Initial data population

## Hibernate DDL Auto Modes
- **create**: Drops existing tables and creates new ones
- **create-drop**: Creates tables, drops them when application stops
- **update**: Creates tables/columns if they don't exist
- **validate**: Validates schema, throws exception if mismatch 