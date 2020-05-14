package com.mvnikitin.boot.service;

import com.mvnikitin.boot.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        // Check the password and repeatPassword are equals.
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "user.password.repeat");
        }
        // Check the password minimal requirements
        if (!user.getPassword().matches(
                "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
                        "(?=.*[@#$%^&+=])(?=\\S+$).{8,20}")) {
            errors.rejectValue("password", "user.password.requirements");
        }
        // Check roles are assigned
        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "user.roles");
        }
    }
}
