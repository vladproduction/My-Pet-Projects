package com.app.vp.wookiebooks.dto;

import lombok.*;

import java.util.Objects;

/**
 * Data transferring object based on User class
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserDto {

    private Long userId;
    private String authorPseudonym; //authorPseudonym
    private String authorPassword;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserDto userDto = (UserDto) object;
        return Objects.equals(userId, userDto.userId) && Objects.equals(authorPseudonym, userDto.authorPseudonym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, authorPseudonym);
    }
}
