package com.example.demo.domain.api;

import com.example.demo.adapters.driving.http.handler.commands.request.CreateUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PatchUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PaymentMethodsCommand;
import com.example.demo.domain.models.User;

public interface IUserServicePort {
    void createUser(CreateUserCommand createUserCommand, PaymentMethodsCommand paymentMethodsCommand);
    User getUserById(long id);
    void patchUser(PatchUserCommand patchUserCommand, PaymentMethodsCommand paymentMethodsCommand);
}
