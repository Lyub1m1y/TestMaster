package com.testmaster.service.impl;

import com.testmaster.model.User;
import com.testmaster.service.InOutService;
import com.testmaster.service.LocalizationService;
import com.testmaster.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final InOutService inOutService;

    private final LocalizationService localizationService;

    @Override
    public User initUser() {
        inOutService.printMessage(localizationService.getMessage("ASK_NAME_MESSAGE"));
        String name = inOutService.readLine();
        inOutService.printMessage(localizationService.getMessage("ASK_SURNAME_MESSAGE"));
        String surname = inOutService.readLine();

        return new User(name, surname);
    }
}
