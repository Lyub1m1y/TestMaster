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
        inOut.printMessageByCodeMessage("ask.name.message");
        String name = inOut.readLine();
        inOut.printMessageByCodeMessage("ask.surname.message");
        String surname = inOut.readLine();

        return new User(name, surname);
    }
}
