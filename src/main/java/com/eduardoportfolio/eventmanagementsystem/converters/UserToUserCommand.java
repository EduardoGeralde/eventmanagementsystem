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
public class UserToUserCommand implements Converter<User, UserCommand> {

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User user) {
        if(user == null){
            return null;
        }

        UserCommand userCommand = new UserCommand();
        userCommand.setUserId(user.getUserId());
        userCommand.setUsername(user.getUsername());
        userCommand.setPassword(user.getPassword());
        userCommand.setUserEmail(user.getUserEmail());

        return userCommand;
    }
}
