package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    
    /**
     * Get all stock details of Facebook in the month of September 2019
     * Query Method: Find stocks by code and date range
     */
    List<Stock> findByCodeAndDateBetweenOrderByDateAsc(String code, Date startDate, Date endDate);
    
    /**
     * Get all Google stock details where the stock price was greater than 1250
     * Query Method: Find stocks by code and close price greater than specified value
     */
    List<Stock> findByCodeAndCloseGreaterThanOrderByCloseDesc(String code, java.math.BigDecimal price);
    
    /**
     * Find the top 3 dates which had highest volume of transactions
     * Query Method: Find top 3 stocks ordered by volume in descending order
     */
    List<Stock> findTop3ByOrderByVolumeDesc();
    
    /**
     * Identify three dates when Netflix stocks were the lowest
     * Query Method: Find top 3 stocks by code ordered by close price in ascending order
     */
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
    
    /**
     * Alternative method using @Query annotation for Facebook September 2019 data
     */
    @Query("SELECT s FROM Stock s WHERE s.code = :code AND s.date BETWEEN :startDate AND :endDate ORDER BY s.date ASC")
    List<Stock> findFacebookStocksInSeptember(@Param("code") String code, 
                                             @Param("startDate") Date startDate, 
                                             @Param("endDate") Date endDate);
    
    /**
     * Alternative method using @Query annotation for Google stocks above price
     */
    @Query("SELECT s FROM Stock s WHERE s.code = :code AND s.close > :price ORDER BY s.close DESC")
    List<Stock> findGoogleStocksAbovePrice(@Param("code") String code, 
                                          @Param("price") java.math.BigDecimal price);
    
    /**
     * Alternative method using @Query annotation for top 3 highest volume
     */
    @Query("SELECT s FROM Stock s ORDER BY s.volume DESC")
    List<Stock> findTop3HighestVolume();
    
    /**
     * Alternative method using @Query annotation for Netflix lowest stocks
     */
    @Query("SELECT s FROM Stock s WHERE s.code = :code ORDER BY s.close ASC")
    List<Stock> findNetflixLowestStocks(@Param("code") String code);
} 