package com.testmaster.service.impl;

import com.testmaster.model.User;
import com.testmaster.service.InOutService;
import com.testmaster.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.testmaster.app.TestMasterConstants.ASK_NAME_MESSAGE;
import static com.testmaster.app.TestMasterConstants.ASK_SURNAME_MESSAGE;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    @NonNull
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
