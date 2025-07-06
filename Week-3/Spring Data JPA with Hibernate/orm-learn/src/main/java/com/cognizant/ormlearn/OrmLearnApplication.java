package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;
    private static StockService stockService;
    private static EmployeeService employeeService;
    private static SkillService skillService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        stockService = context.getBean(StockService.class);
        employeeService = context.getBean(EmployeeService.class);
        skillService = context.getBean(SkillService.class);
        LOGGER.info("Inside main");
        
        // Test Hands-on 6: Many-to-Many relationship between Employee and Skill
        testGetEmployee();
        testAddSkillToEmployee();
        
        // Test Hands-on 2: Get All Permanent Employees using HQL
        testGetAllPermanentEmployees();
        testGetAllPermanentEmployeesWithFetch();
        
        // Test Hands-on 4: Get Average Salary using HQL
        testGetAverageSalary();
        testGetAverageSalaryByDepartment();
        
        // Test Hands-on 5: Get All Employees using Native Query
        testGetAllEmployeesNative();
        
        // Comment out other test methods for Hands-on 6
        /*
        // Test all Country management features
        testGetAllCountries();
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testFindCountriesByNameContaining();
        testDeleteCountry();
        testFindCountriesStartingWith();
        
        // Test Hands-on 6: Find country by code with exception handling
        getAllCountriesTest();
        
        // Test Hands-on 7: Add a new country
        testAddCountryHandsOn7();
        
        // Test Hands-on 2: Stock Query Methods
        testStockQueryMethods();
        */
    }

    private static void testGetAllCountries() {
        LOGGER.info("=== Testing getAllCountries ===");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("Total countries found: {}", countries.size());
        LOGGER.debug("countries={}", countries);
        LOGGER.info("=== getAllCountries test completed ===\n");
    }
    
    private static void testFindCountryByCode() {
        LOGGER.info("=== Testing findCountryByCode ===");
        
        try {
            // Test finding existing country
            Country india = countryService.findCountryByCode("IN");
            LOGGER.info("Found country: {}", india);
            
            // Verify the country name is correct
            if ("India".equals(india.getName())) {
                LOGGER.info("✅ Country name validation successful: {}", india.getName());
            } else {
                LOGGER.warn("❌ Country name validation failed. Expected: India, Found: {}", india.getName());
            }
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: {}", e.getMessage());
        }
        
        try {
            // Test finding non-existing country
            Country nonExistent = countryService.findCountryByCode("XX");
            LOGGER.info("Found country: {}", nonExistent);
        } catch (CountryNotFoundException e) {
            LOGGER.info("✅ Country with code 'XX' not found (as expected): {}", e.getMessage());
        }
        
        LOGGER.info("=== findCountryByCode test completed ===\n");
    }
    
    /**
     * Test method for Hands-on 6: Find a country based on country code
     */
    private static void getAllCountriesTest() {
        LOGGER.info("=== Hands-on 6: Testing findCountryByCode with Exception Handling ===");
        LOGGER.info("Start");
        
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
            
            // Compare the country name to check if it is valid
            if ("India".equals(country.getName())) {
                LOGGER.info("✅ Country validation successful: {} - {}", country.getCode(), country.getName());
            } else {
                LOGGER.warn("❌ Country validation failed. Expected: India, Found: {}", country.getName());
            }
        } catch (CountryNotFoundException e) {
            LOGGER.error("❌ Country not found: {}", e.getMessage());
        }
        
        LOGGER.info("End");
        LOGGER.info("=== Hands-on 6 test completed ===\n");
    }
    
    /**
     * Test method for Hands-on 7: Add a new country
     */
    private static void testAddCountryHandsOn7() {
        LOGGER.info("=== Hands-on 7: Testing Add a New Country ===");
        
        // Create new instance of country with a new code and name
        Country newCountry = new Country();
        newCountry.setCode("YY");
        newCountry.setName("Wonderland");
        
        LOGGER.info("Created new country: {}", newCountry);
        
        // Call countryService.addCountry() passing the country created in the previous step
        countryService.addCountry(newCountry);
        LOGGER.info("✅ Country added successfully using addCountry() method");
        
        try {
            // Invoke countryService.findCountryByCode() passing the same code used when adding a new country
            Country foundCountry = countryService.findCountryByCode("YY");
            LOGGER.info("✅ Country found in database: {}", foundCountry);
            
            // Check in the database if the country is added
            if ("Wonderland".equals(foundCountry.getName())) {
                LOGGER.info("✅ Database verification successful: Country '{}' with code '{}' is properly stored", 
                           foundCountry.getName(), foundCountry.getCode());
            } else {
                LOGGER.warn("❌ Database verification failed: Expected 'Wonderland', Found '{}'", foundCountry.getName());
            }
        } catch (CountryNotFoundException e) {
            LOGGER.error("❌ Country not found in database after adding: {}", e.getMessage());
        }
        
        LOGGER.info("=== Hands-on 7 test completed ===\n");
    }
    
    private static void testAddCountry() {
        LOGGER.info("=== Testing addCountry ===");
        
        Country newCountry = new Country();
        newCountry.setCode("XX");
        newCountry.setName("Test Country");
        
        countryService.addCountry(newCountry);
        LOGGER.info("Successfully added country: {}", newCountry);
        
        LOGGER.info("=== addCountry test completed ===\n");
    }
    
    private static void testUpdateCountry() {
        LOGGER.info("=== Testing updateCountry ===");
        
        Country updateDetails = new Country();
        updateDetails.setName("Updated Test Country");
        
        Country updatedCountry = countryService.updateCountry("XX", updateDetails);
        if (updatedCountry != null) {
            LOGGER.info("Successfully updated country: {}", updatedCountry);
        }
        
        LOGGER.info("=== updateCountry test completed ===\n");
    }
    
    private static void testFindCountriesByNameContaining() {
        LOGGER.info("=== Testing findCountriesByNameContaining ===");
        
        // Test searching for countries containing "ou" (as per Hands-on 1 requirement)
        LOGGER.info("--- Searching for countries containing 'ou' ---");
        List<Country> ouCountries = countryService.findCountriesByNameContaining("ou");
        LOGGER.info("Countries containing 'ou': {}", ouCountries.size());
        for (Country country : ouCountries) {
            LOGGER.info("  {}       {}", country.getCode(), country.getName());
        }
        
        // Test searching for countries containing "land"
        LOGGER.info("--- Searching for countries containing 'land' ---");
        List<Country> landCountries = countryService.findCountriesByNameContaining("land");
        LOGGER.info("Countries containing 'land': {}", landCountries.size());
        for (Country country : landCountries) {
            LOGGER.info("  {}       {}", country.getCode(), country.getName());
        }
        
        // Test searching for countries containing "stan"
        LOGGER.info("--- Searching for countries containing 'stan' ---");
        List<Country> stanCountries = countryService.searchCountriesByName("stan");
        LOGGER.info("Countries containing 'stan': {}", stanCountries.size());
        for (Country country : stanCountries) {
            LOGGER.info("  {}       {}", country.getCode(), country.getName());
        }
        
        LOGGER.info("=== findCountriesByNameContaining test completed ===\n");
    }
    
    private static void testFindCountriesStartingWith() {
        LOGGER.info("=== Testing findCountriesStartingWith ===");
        
        // Test finding countries starting with "Z" (as per Hands-on 1 requirement)
        LOGGER.info("--- Searching for countries starting with 'Z' ---");
        List<Country> zCountries = countryService.findCountriesStartingWith("Z");
        LOGGER.info("Countries starting with 'Z': {}", zCountries.size());
        for (Country country : zCountries) {
            LOGGER.info("  {}       {}", country.getCode(), country.getName());
        }
        
        // Test finding countries starting with "A"
        LOGGER.info("--- Searching for countries starting with 'A' ---");
        List<Country> aCountries = countryService.findCountriesStartingWith("A");
        LOGGER.info("Countries starting with 'A': {}", aCountries.size());
        for (Country country : aCountries) {
            LOGGER.info("  {}       {}", country.getCode(), country.getName());
        }
        
        LOGGER.info("=== findCountriesStartingWith test completed ===\n");
    }
    
    private static void testDeleteCountry() {
        LOGGER.info("=== Testing deleteCountry ===");
        
        boolean deleted = countryService.deleteCountry("XX");
        if (deleted) {
            LOGGER.info("Successfully deleted country with code 'XX'");
        } else {
            LOGGER.warn("Failed to delete country with code 'XX'");
        }
        
        LOGGER.info("=== deleteCountry test completed ===\n");
    }
    
    /**
     * Test method for Hands-on 2: Stock Query Methods
     */
    private static void testStockQueryMethods() {
        LOGGER.info("=== Hands-on 2: Testing Stock Query Methods ===");
        
        // Test 1: Get all stock details of Facebook in the month of September 2019
        LOGGER.info("--- Test 1: Facebook stocks in September 2019 ---");
        try {
            List<Stock> facebookStocks = stockService.getFacebookStocksInSeptember2019();
            LOGGER.info("Found {} Facebook stocks for September 2019", facebookStocks.size());
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            LOGGER.info("| st_code | st_date    | st_open | st_close | st_volume |");
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            for (Stock stock : facebookStocks) {
                LOGGER.info("| {}      | {} | {:>7.2f} | {:>8.2f} | {:>9} |", 
                           stock.getCode(), 
                           stock.getDate(), 
                           stock.getOpen(), 
                           stock.getClose(), 
                           stock.getVolume());
            }
            LOGGER.info("+---------+------------+---------+----------+-----------+");
        } catch (Exception e) {
            LOGGER.error("Error fetching Facebook stocks: {}", e.getMessage());
        }
        
        // Test 2: Get all Google stock details where the stock price was greater than 1250
        LOGGER.info("--- Test 2: Google stocks with close price > 1250 ---");
        try {
            List<Stock> googleStocks = stockService.getGoogleStocksAbove1250();
            LOGGER.info("Found {} Google stocks with close price > 1250", googleStocks.size());
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            LOGGER.info("| st_code | st_date    | st_open | st_close | st_volume |");
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            for (Stock stock : googleStocks) {
                LOGGER.info("| {}   | {} | {:>7.2f} | {:>8.2f} | {:>9} |", 
                           stock.getCode(), 
                           stock.getDate(), 
                           stock.getOpen(), 
                           stock.getClose(), 
                           stock.getVolume());
            }
            LOGGER.info("+---------+------------+---------+----------+-----------+");
        } catch (Exception e) {
            LOGGER.error("Error fetching Google stocks: {}", e.getMessage());
        }
        
        // Test 3: Find the top 3 dates which had highest volume of transactions
        LOGGER.info("--- Test 3: Top 3 stocks with highest volume ---");
        try {
            List<Stock> topVolumeStocks = stockService.getTop3HighestVolumeStocks();
            LOGGER.info("Found {} stocks with highest volume", topVolumeStocks.size());
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            LOGGER.info("| st_code | st_date    | st_open | st_close | st_volume |");
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            for (Stock stock : topVolumeStocks) {
                LOGGER.info("| {}      | {} | {:>7.2f} | {:>8.2f} | {:>9} |", 
                           stock.getCode(), 
                           stock.getDate(), 
                           stock.getOpen(), 
                           stock.getClose(), 
                           stock.getVolume());
            }
            LOGGER.info("+---------+------------+---------+----------+-----------+");
        } catch (Exception e) {
            LOGGER.error("Error fetching top volume stocks: {}", e.getMessage());
        }
        
        // Test 4: Identify three dates when Netflix stocks were the lowest
        LOGGER.info("--- Test 4: Netflix stocks with lowest close prices ---");
        try {
            List<Stock> netflixLowestStocks = stockService.getNetflixLowestStocks();
            LOGGER.info("Found {} Netflix stocks with lowest prices", netflixLowestStocks.size());
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            LOGGER.info("| st_code | st_date    | st_open | st_close | st_volume |");
            LOGGER.info("+---------+------------+---------+----------+-----------+");
            for (Stock stock : netflixLowestStocks) {
                LOGGER.info("| {}   | {} | {:>7.2f} | {:>8.2f} | {:>9} |", 
                           stock.getCode(), 
                           stock.getDate(), 
                           stock.getOpen(), 
                           stock.getClose(), 
                           stock.getVolume());
            }
            LOGGER.info("+---------+------------+---------+----------+-----------+");
        } catch (Exception e) {
            LOGGER.error("Error fetching Netflix lowest stocks: {}", e.getMessage());
        }
        
        LOGGER.info("=== Hands-on 2 test completed ===\n");
    }
    
    /**
     * Test method for Hands-on 6: Get Employee with Skills
     */
    private static void testGetEmployee() {
        LOGGER.info("=== Hands-on 6: Testing Get Employee with Skills ===");
        
        try {
            // Get employee by ID (assuming employee with ID 1 exists)
            Employee employee = employeeService.get(1);
            
            if (employee != null) {
                LOGGER.info("Employee found: {}", employee);
                LOGGER.info("Department: {}", employee.getDepartment());
                LOGGER.debug("Skills:{}", employee.getSkillList());
                
                // Display skills
                if (employee.getSkillList() != null && !employee.getSkillList().isEmpty()) {
                    LOGGER.info("Employee Skills:");
                    for (Skill skill : employee.getSkillList()) {
                        LOGGER.info("  - {}", skill.getName());
                    }
                } else {
                    LOGGER.info("No skills found for this employee");
                }
            } else {
                LOGGER.warn("Employee with ID 1 not found");
            }
        } catch (Exception e) {
            LOGGER.error("Error fetching employee: {}", e.getMessage());
        }
        
        LOGGER.info("=== Hands-on 6 testGetEmployee completed ===\n");
    }
    
    /**
     * Test method for Hands-on 6: Add Skill to Employee
     */
    private static void testAddSkillToEmployee() {
        LOGGER.info("=== Hands-on 6: Testing Add Skill to Employee ===");
        
        try {
            // Identify an employee id and skill id for which a relationship does not exist
            int employeeId = 1;  // Employee ID
            int skillId = 10;    // Skill ID (AWS)
            
            // Get employee based on employee id
            Employee employee = employeeService.get(employeeId);
            if (employee == null) {
                LOGGER.error("Employee with ID {} not found", employeeId);
                return;
            }
            
            // Get skill based on skill id
            Skill skill = skillService.get(skillId);
            if (skill == null) {
                LOGGER.error("Skill with ID {} not found", skillId);
                return;
            }
            
            LOGGER.info("Employee before adding skill: {}", employee.getName());
            LOGGER.info("Skill to add: {}", skill.getName());
            
            // Check if skill already exists for this employee
            boolean skillExists = false;
            if (employee.getSkillList() != null) {
                for (Skill existingSkill : employee.getSkillList()) {
                    if (existingSkill.getId() == skillId) {
                        skillExists = true;
                        LOGGER.info("Skill {} already exists for employee {}", skill.getName(), employee.getName());
                        break;
                    }
                }
            }
            
            if (!skillExists) {
                // Get the skill list from employee and add the skill
                if (employee.getSkillList() == null) {
                    // Initialize skillList if it's null
                    employee.setSkillList(new java.util.HashSet<>());
                }
                employee.getSkillList().add(skill);
                
                // Call save method in employeeService
                Employee savedEmployee = employeeService.save(employee);
                
                LOGGER.info("✅ Skill '{}' successfully added to employee '{}'", skill.getName(), savedEmployee.getName());
                LOGGER.info("Updated employee skills:");
                for (Skill empSkill : savedEmployee.getSkillList()) {
                    LOGGER.info("  - {}", empSkill.getName());
                }
            }
            
        } catch (Exception e) {
            LOGGER.error("Error adding skill to employee: {}", e.getMessage());
            e.printStackTrace();
        }
        
        LOGGER.info("=== Hands-on 6 testAddSkillToEmployee completed ===\n");
    }
    
    /**
     * Test method for Hands-on 2: Get All Permanent Employees using HQL
     */
    public static void testGetAllPermanentEmployees() {
        LOGGER.info("=== Hands-on 2: Testing Get All Permanent Employees using HQL ===");
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
        LOGGER.info("=== Hands-on 2 testGetAllPermanentEmployees completed ===\n");
    }
    
    /**
     * Test method for Hands-on 2: Get All Permanent Employees using HQL with Fetch
     */
    public static void testGetAllPermanentEmployeesWithFetch() {
        LOGGER.info("=== Hands-on 2: Testing Get All Permanent Employees using HQL with Fetch ===");
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployeesWithFetch();
        LOGGER.debug("Permanent Employees with Fetch:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
        LOGGER.info("=== Hands-on 2 testGetAllPermanentEmployeesWithFetch completed ===\n");
    }
    
    /**
     * Test method for Hands-on 4: Get Average Salary using HQL
     */
    public static void testGetAverageSalary() {
        LOGGER.info("=== Hands-on 4: Testing Get Average Salary using HQL ===");
        double averageSalary = employeeService.getAverageSalary();
        LOGGER.info("Overall Average Salary: {}", averageSalary);
        LOGGER.info("=== Hands-on 4 testGetAverageSalary completed ===\n");
    }
    
    /**
     * Test method for Hands-on 4: Get Average Salary by Department using HQL
     */
    public static void testGetAverageSalaryByDepartment() {
        LOGGER.info("=== Hands-on 4: Testing Get Average Salary by Department using HQL ===");
        
        // Test for Engineering department (ID = 1)
        double engineeringAvgSalary = employeeService.getAverageSalary(1);
        LOGGER.info("Engineering Department Average Salary: {}", engineeringAvgSalary);
        
        // Test for Marketing department (ID = 2)
        double marketingAvgSalary = employeeService.getAverageSalary(2);
        LOGGER.info("Marketing Department Average Salary: {}", marketingAvgSalary);
        
        LOGGER.info("=== Hands-on 4 testGetAverageSalaryByDepartment completed ===\n");
    }
    
    /**
     * Test method for Hands-on 5: Get All Employees using Native Query
     */
    public static void testGetAllEmployeesNative() {
        LOGGER.info("=== Hands-on 5: Testing Get All Employees using Native Query ===");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.info("Found {} employees using native query", employees.size());
        employees.forEach(e -> LOGGER.info("Employee: {}", e.getName()));
        LOGGER.info("=== Hands-on 5 testGetAllEmployeesNative completed ===\n");
    }
} 