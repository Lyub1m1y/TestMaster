package com.testmaster.service.impl;

import com.testmaster.model.User;
import com.testmaster.service.InOutService;
import com.testmaster.service.UserService;
import com.testmaster.utils.MessageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final InOutService inOutService;
    private final MessageUtils messageUtils;

    @Override
    public User initUser() {
        inOutService.printMessage(messageUtils.getMessage("ASK_NAME_MESSAGE"));
        String name = inOutService.readLine();
        inOutService.printMessage(messageUtils.getMessage("ASK_SURNAME_MESSAGE"));
        String surname = inOutService.readLine();

        return new User(name, surname);
    }
}
