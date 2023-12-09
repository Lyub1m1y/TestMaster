package com.testmaster.service.impl;

import com.testmaster.model.User;
import com.testmaster.service.InOutService;
import com.testmaster.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    @NonNull
    private final InOutService inOutService;

    private static final String ASK_NAME_MESSAGE = "Your name: ";
    private static final String ASK_SURNAME_MESSAGE = "Your surname: ";


    @Override
    public User initUser() {
        inOutService.printMessage(ASK_NAME_MESSAGE);
        String name = inOutService.readLine();
        inOutService.printMessage(ASK_SURNAME_MESSAGE);
        String surname = inOutService.readLine();

        return new User(name, surname);
    }
}
