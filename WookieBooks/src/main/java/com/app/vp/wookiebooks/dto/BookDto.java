package com.app.vp.wookiebooks.dto;

import lombok.*;

/**
 * Data transferring object based on Book class
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {

    private String title;
    private String description;
    private UserDto author;
    private String coverImage;
    private double price;

}
