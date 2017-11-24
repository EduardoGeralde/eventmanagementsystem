package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.UserCommand;
import com.eduardoportfolio.eventmanagementsystem.models.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand userCommand) {
        if (userCommand == null) {
            return null;
        }

        User user = new User();
        user.setUserId(userCommand.getUserId());
        user.setUsername(userCommand.getUsername());
        user.setPassword(userCommand.getPassword());
        user.setUserEmail(userCommand.getUserEmail());

        return user;
    }


}
