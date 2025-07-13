package com.cognizant.spring_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Country;
import com.cognizant.spring_learn.service.CountryService;

import java.util.List;

@RestController
public class CountryController {
    
    @Autowired
    private Country india;
    
    @Autowired
    @Qualifier("countryList")
    private List<Country> countryList;
    
    @Autowired
    private CountryService countryService;
    
    @RequestMapping("/country")
    public Country getCountryIndia() {
        return india;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryList;
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {
        return countryService.getCountry(code);
    }
} 