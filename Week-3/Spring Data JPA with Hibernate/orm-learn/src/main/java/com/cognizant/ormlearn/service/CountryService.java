package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    
    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Fetching all countries");
        return countryRepository.findAll();
    }
    
    /**
     * Find a country based on country code
     * @param countryCode the country code to search for
     * @return the country if found
     * @throws CountryNotFoundException if country is not found
     */
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        LOGGER.info("Finding country by code: {}", countryCode);
        
        // Get the country based on findById() built in method
        Optional<Country> result = countryRepository.findById(countryCode);
        
        // From the result, check if a country is found. If not found, throw CountryNotFoundException
        if (!result.isPresent()) {
            LOGGER.warn("Country not found with code: {}", countryCode);
            throw new CountryNotFoundException("Country not found with code: " + countryCode);
        }
        
        // Use get() method to return the country fetched
        Country country = result.get();
        LOGGER.info("Found country: {}", country);
        return country;
    }
    
    /**
     * Add new country
     */
    @Transactional
    public void addCountry(Country country) {
        LOGGER.info("Adding new country: {}", country);
        countryRepository.save(country);
        LOGGER.info("Country added successfully: {}", country);
    }
    
    /**
     * Update country
     */
    @Transactional
    public Country updateCountry(String code, Country countryDetails) {
        LOGGER.info("Updating country with code: {}", code);
        Optional<Country> existingCountry = countryRepository.findById(code);
        
        if (existingCountry.isPresent()) {
            Country country = existingCountry.get();
            country.setName(countryDetails.getName());
            Country updatedCountry = countryRepository.save(country);
            LOGGER.info("Country updated successfully: {}", updatedCountry);
            return updatedCountry;
        } else {
            LOGGER.warn("Country not found with code: {}", code);
            return null;
        }
    }
    
    /**
     * Delete country
     */
    @Transactional
    public boolean deleteCountry(String code) {
        LOGGER.info("Deleting country with code: {}", code);
        if (countryRepository.existsById(code)) {
            countryRepository.deleteById(code);
            LOGGER.info("Country deleted successfully with code: {}", code);
            return true;
        } else {
            LOGGER.warn("Country not found with code: {}", code);
            return false;
        }
    }
    
    /**
     * Find list of countries matching a partial country name
     */
    @Transactional
    public List<Country> findCountriesByNameContaining(String name) {
        LOGGER.info("Finding countries containing name: {}", name);
        List<Country> countries = countryRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
        LOGGER.info("Found {} countries containing '{}'", countries.size(), name);
        return countries;
    }
    
    /**
     * Alternative method using @Query annotation
     */
    @Transactional
    public List<Country> searchCountriesByName(String name) {
        LOGGER.info("Searching countries by name: {}", name);
        List<Country> countries = countryRepository.findCountriesByNameContaining(name);
        LOGGER.info("Found {} countries for search term '{}'", countries.size(), name);
        return countries;
    }
    
    /**
     * Find countries starting with a specific letter
     */
    @Transactional
    public List<Country> findCountriesStartingWith(String prefix) {
        LOGGER.info("Finding countries starting with: {}", prefix);
        List<Country> countries = countryRepository.findByNameStartingWithIgnoreCaseOrderByNameAsc(prefix);
        LOGGER.info("Found {} countries starting with '{}'", countries.size(), prefix);
        return countries;
    }
} 