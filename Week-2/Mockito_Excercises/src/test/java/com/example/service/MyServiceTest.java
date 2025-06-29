package com.example.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InOrder;

public class MyServiceTest {

    @Test
    public void testExternalApi() {
        // Step 1: Create a mock ExternalApi
        ExternalApi mockApi = mock(ExternalApi.class);

        // Step 2: Stub the method to return a fixed value
        when(mockApi.getData()).thenReturn("Mock Data");

        // Step 3: Inject mock into MyService
        MyService service = new MyService(mockApi);

        // Step 4: Call method and assert result
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    @Test
    public void testVerifyInteraction() {
        // Create a mock ExternalApi
        ExternalApi mockApi = mock(ExternalApi.class);

        // Inject mock into MyService
        MyService service = new MyService(mockApi);

        // Call method
        service.fetchData();

        // Verify that getData() was called
        verify(mockApi).getData();

    }


        @Test
        public void testArgumentMatching() {
            ExternalApi mockApi = mock(ExternalApi.class);

            when(mockApi.getUserData(anyString())).thenReturn("Mock User");

            MyService service = new MyService(mockApi);
            String result = service.fetchUserData("user123");

            assertEquals("Mock User", result);
            verify(mockApi).getUserData(eq("user123")); // argument matcher
        }

    @Test
    public void testVoidMethodInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);

        MyService service = new MyService(mockApi);
        service.doLogging("Hello World");

        verify(mockApi).logAction("Hello World");
    }


    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = mock(ExternalApi.class);

        when(mockApi.getData())
                .thenReturn("First")
                .thenReturn("Second")
                .thenReturn("Third");

        MyService service = new MyService(mockApi);

        assertEquals("First", service.fetchData());
        assertEquals("Second", service.fetchData());
        assertEquals("Third", service.fetchData());
    }

    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.executeAll();

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).start();
        inOrder.verify(mockApi).process();
        inOrder.verify(mockApi).finish();
    }


    @Test
    public void testVoidMethodException() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        doThrow(new RuntimeException("Error occurred"))
                .when(mockApi)
                .logAction("Fail log");

        assertThrows(RuntimeException.class, () -> {
            service.doLogging("Fail log");
        });

        verify(mockApi).logAction("Fail log");
    }




}
