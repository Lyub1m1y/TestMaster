package com.testmaster.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InOutFacade implements InOut {
    private final InOutService inOutService;

    private final LocalizationService localizationService;

    @Override
    public String readLine() {
        return inOutService.readLine();
    }

    @Override
    public void printMessage(String message) {
        inOutService.printMessage(message);
    }

    @Override
    public void printMessageByCodeMessage(String codeMessage, Object... args) {
        inOutService.printMessage(localizationService.getMessage(codeMessage, args));
    }

    @Override
    public int readIntByIntervalAndCodeMessage(int min, int max, String codeMessage,
                                               String codeErrorMessage, Object... args) {
        return inOutService.readIntByInterval(min, max,
                localizationService.getMessage(codeMessage),
                localizationService.getMessage(codeErrorMessage, args));
    }

    @Override
    public void printMessageInterval() {
        inOutService.printMessageInterval();
    }
}
