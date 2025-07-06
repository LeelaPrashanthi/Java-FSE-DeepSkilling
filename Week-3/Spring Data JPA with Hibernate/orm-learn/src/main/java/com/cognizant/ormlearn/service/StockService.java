package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StockService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);
    
    @Autowired
    private StockRepository stockRepository;
    
    /**
     * Get all stock details of Facebook in the month of September 2019
     */
    @Transactional
    public List<Stock> getFacebookStocksInSeptember2019() {
        LOGGER.info("Fetching Facebook stocks for September 2019");
        
        // Create date range for September 2019
        Calendar startCal = Calendar.getInstance();
        startCal.set(2019, Calendar.SEPTEMBER, 1, 0, 0, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date startDate = startCal.getTime();
        
        Calendar endCal = Calendar.getInstance();
        endCal.set(2019, Calendar.SEPTEMBER, 30, 23, 59, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        Date endDate = endCal.getTime();
        
        List<Stock> stocks = stockRepository.findByCodeAndDateBetweenOrderByDateAsc("FB", startDate, endDate);
        LOGGER.info("Found {} Facebook stocks for September 2019", stocks.size());
        return stocks;
    }
    
    /**
     * Get all Google stock details where the stock price was greater than 1250
     */
    @Transactional
    public List<Stock> getGoogleStocksAbove1250() {
        LOGGER.info("Fetching Google stocks with close price greater than 1250");
        
        BigDecimal priceThreshold = new BigDecimal("1250.00");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThanOrderByCloseDesc("GOOGL", priceThreshold);
        LOGGER.info("Found {} Google stocks with close price > 1250", stocks.size());
        return stocks;
    }
    
    /**
     * Find the top 3 dates which had highest volume of transactions
     */
    @Transactional
    public List<Stock> getTop3HighestVolumeStocks() {
        LOGGER.info("Fetching top 3 stocks with highest volume");
        
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        LOGGER.info("Found {} stocks with highest volume", stocks.size());
        return stocks;
    }
    
    /**
     * Identify three dates when Netflix stocks were the lowest
     */
    @Transactional
    public List<Stock> getNetflixLowestStocks() {
        LOGGER.info("Fetching Netflix stocks with lowest close prices");
        
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        LOGGER.info("Found {} Netflix stocks with lowest prices", stocks.size());
        return stocks;
    }
    
    /**
     * Alternative method using @Query annotation for Facebook September 2019 data
     */
    @Transactional
    public List<Stock> getFacebookStocksInSeptember2019WithQuery() {
        LOGGER.info("Fetching Facebook stocks for September 2019 using @Query annotation");
        
        Calendar startCal = Calendar.getInstance();
        startCal.set(2019, Calendar.SEPTEMBER, 1, 0, 0, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date startDate = startCal.getTime();
        
        Calendar endCal = Calendar.getInstance();
        endCal.set(2019, Calendar.SEPTEMBER, 30, 23, 59, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        Date endDate = endCal.getTime();
        
        List<Stock> stocks = stockRepository.findFacebookStocksInSeptember("FB", startDate, endDate);
        LOGGER.info("Found {} Facebook stocks for September 2019 using @Query", stocks.size());
        return stocks;
    }
    
    /**
     * Alternative method using @Query annotation for Google stocks above price
     */
    @Transactional
    public List<Stock> getGoogleStocksAbove1250WithQuery() {
        LOGGER.info("Fetching Google stocks with close price greater than 1250 using @Query annotation");
        
        BigDecimal priceThreshold = new BigDecimal("1250.00");
        List<Stock> stocks = stockRepository.findGoogleStocksAbovePrice("GOOGL", priceThreshold);
        LOGGER.info("Found {} Google stocks with close price > 1250 using @Query", stocks.size());
        return stocks;
    }
    
    /**
     * Alternative method using @Query annotation for top 3 highest volume
     */
    @Transactional
    public List<Stock> getTop3HighestVolumeStocksWithQuery() {
        LOGGER.info("Fetching top 3 stocks with highest volume using @Query annotation");
        
        List<Stock> stocks = stockRepository.findTop3HighestVolume();
        // Limit to top 3 since @Query doesn't support Top keyword
        if (stocks.size() > 3) {
            stocks = stocks.subList(0, 3);
        }
        LOGGER.info("Found {} stocks with highest volume using @Query", stocks.size());
        return stocks;
    }
    
    /**
     * Alternative method using @Query annotation for Netflix lowest stocks
     */
    @Transactional
    public List<Stock> getNetflixLowestStocksWithQuery() {
        LOGGER.info("Fetching Netflix stocks with lowest close prices using @Query annotation");
        
        List<Stock> stocks = stockRepository.findNetflixLowestStocks("NFLX");
        // Limit to top 3 since @Query doesn't support Top keyword
        if (stocks.size() > 3) {
            stocks = stocks.subList(0, 3);
        }
        LOGGER.info("Found {} Netflix stocks with lowest prices using @Query", stocks.size());
        return stocks;
    }
} 