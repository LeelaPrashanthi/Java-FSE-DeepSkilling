package com.example.api;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApiServiceTest {

    @Test
    public void testServiceWithMockRestClient() {
        // Step 1: Create a mock REST client
        RestClient mockRestClient = mock(RestClient.class);

        // Step 2: Stub the method
        when(mockRestClient.getResponse()).thenReturn("Mock Response");

        // Step 3: Inject mock and call service
        ApiService apiService = new ApiService(mockRestClient);
        String result = apiService.fetchData();

        // Step 4: Verify the result
        assertEquals("Fetched Mock Response", result);
    }
}
