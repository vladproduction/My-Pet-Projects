package com.app.vp.wookiebooks.mapper;

import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.model.User;

/**
 * Class presenting conversion model object of User class to Dto
 * and vise-versa;
 * Contain:
 * -mapToUser(UserDto),
 * -mapToUserDto(User);
 * For creating objects using @Builder;
 * */
public class UserMapper {

    /**
     * Method for conversion UserDto to User object.
     * @param userDto UserDto object
     * @return user User object
     * */
    public static User mapToUser(UserDto userDto){
        User user = User.builder()
                .authorPseudonym(userDto.getAuthorPseudonym())
                .build();
        return user;
    }

    /**
     * Method for conversion User to UserDto object.
     * @param user User object
     * @return userDto UserDto object
     * */
    public static UserDto mapToUserDto(User user){
        UserDto userDto = UserDto.builder()
                .authorPseudonym(user.getAuthorPseudonym())
                .build();
        return userDto;
    }
}
