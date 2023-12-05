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

    @Override
    public User initUser() {
        inOutService.printMessage("Your name: ");
        String name = inOutService.readLine();
        inOutService.printMessage("Your surname: ");
        String surname = inOutService.readLine();

        return new User(name, surname);
    }
}
