package com.testmaster.service.impl;

import com.testmaster.model.User;
import com.testmaster.service.InOut;
import com.testmaster.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final InOut inOut;

    @Override
    public User initUser() {
        String name = inOut.readLineWithMessage("ask.name.message");
        String surname = inOut.readLineWithMessage("ask.surname.message");

        return new User(name, surname);
    }
}
