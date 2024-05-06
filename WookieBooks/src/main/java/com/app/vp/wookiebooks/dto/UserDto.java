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
public class UserDto {

    private String authorPseudonym;
}
