package com.school.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.Nullable;

import com.school.models.User;

public class UserProcessor implements ItemProcessor<User, User> {

    @Override
    @Nullable
    public User process(User user) throws Exception {
        return user;
    } 

}
