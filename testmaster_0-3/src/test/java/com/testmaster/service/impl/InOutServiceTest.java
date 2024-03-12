package com.testmaster.service.impl;

import com.testmaster.service.InOutService;
import com.testmaster.service.impl.io.system.SystemInOutService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SystemInOutService.class})
class InOutServiceTest {

    @MockBean
    private SystemInOutService systemInOutServiceMock;

    @Autowired
    private InOutService inOutService;

    @Test
    @DisplayName("readInput should return input")
    void readInputShouldReturnInput() {
        String input = "Test Input";
        when(systemInOutServiceMock.readLine()).thenReturn(input);

        String result = inOutService.readLine();

        verify(systemInOutServiceMock).readLine();
        assertEquals(input, result);
    }

    @Test
    @DisplayName("printOutput should print output")
    void printOutputShouldPrintOutput() {
        String message = "Test Message";

        inOutService.printMessage(message);

        verify(systemInOutServiceMock).printMessage(message);
    }
}