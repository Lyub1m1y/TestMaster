package com.testmaster.service.impl;

import com.testmaster.model.User;
import com.testmaster.service.InOutService;
import com.testmaster.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ASK_NAME_MESSAGE = "Your name: ";
    private static final String ASK_SURNAME_MESSAGE = "Your surname: ";

    private final InOutService inOutService;

    @Override
    public User initUser() {
        inOutService.printMessage(ASK_NAME_MESSAGE);
        String name = inOutService.readLine();
        inOutService.printMessage(ASK_SURNAME_MESSAGE);
        String surname = inOutService.readLine();

        return new User(name, surname);
    }
}
