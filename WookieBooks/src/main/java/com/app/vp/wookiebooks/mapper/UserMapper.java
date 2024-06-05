package com.app.vp.wookiebooks.mapper;

import com.app.vp.wookiebooks.dto.UserDto;
import com.app.vp.wookiebooks.model.Roles;
import com.app.vp.wookiebooks.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class presenting conversion model object of User class to Dto
 * and vise-versa;
 * Contain:
 * -mapToUser(UserDto),
 * -mapToUserDto(User);
 * For creating objects using @Builder;
 */
public class UserMapper {

    /**
     * Method for conversion UserDto to User object.
     *
     * @param userDto UserDto object
     * @return user User object
     */
    public static User mapToUser(UserDto userDto) {
        User user = User.builder()
                .authorPseudonym(userDto.getAuthorPseudonym())
                .authorPassword(userDto.getAuthorPassword())
                .roles(Roles.USER)
                .build();
        return user;
    }

    /**
     * Method for conversion User to UserDto object.
     *
     * @param user User object
     * @return userDto UserDto object
     */
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .authorPseudonym(user.getAuthorPseudonym())
                .authorPassword(user.getAuthorPassword())
                .build();
        return userDto;
    }

    /**
     * Method for conversion List<User> to List<UserDto>.
     *
     * @param users List<User>;
     * @return listDtoUsers List<UserDto>;
     */
    public static List<UserDto> mapToListDtoUsers(List<User> users) {
        List<UserDto> listDtoUsers = new ArrayList<>();
        for (User user : users) {
            listDtoUsers.add(mapToUserDto(user));
        }
        return listDtoUsers;
    }

}
