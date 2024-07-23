package com.app.vp.wookiebooks.dto;

import lombok.*;

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

}
