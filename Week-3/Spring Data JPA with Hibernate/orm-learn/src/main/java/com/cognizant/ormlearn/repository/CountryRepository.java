package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    
    // Find countries by partial name match (case-insensitive)
    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    
    // Alternative using @Query annotation
    @Query("SELECT c FROM Country c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY c.name ASC")
    List<Country> findCountriesByNameContaining(@Param("name") String name);
    
    // Find countries starting with a specific letter
    List<Country> findByNameStartingWithIgnoreCaseOrderByNameAsc(String prefix);
} 