package com.app.vp.wookiebooks.mapper;

import com.app.vp.wookiebooks.dto.BookDto;
import com.app.vp.wookiebooks.model.Book;

/**
 * Class presenting conversion model object of Book class to Dto
 * and vise-versa;
 * Contain:
 * -mapToBook(BookDto),
 * -mapToBookDto(Book);
 * For creating objects using @Builder;
 * */
public class BookMapper {

    /**
     * Method for conversion BookDto to Book object.
     * @param bookDto BookDto object
     * @return book Book object
     * */
    public static Book mapToBook(BookDto bookDto){
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .author(UserMapper.mapToUser(bookDto.getAuthor()))
                .coverImage(bookDto.getCoverImage())
                .price(bookDto.getPrice())
                .build();
        return book;
    }

    /**
     * Method for conversion Book to BookDto object.
     * @param book Book object
     * @return bookDto BookDto object
     * */
    public static BookDto mapToBookDto(Book book){
        BookDto bookDto = BookDto.builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .author(UserMapper.mapToUserDto(book.getAuthor()))
                .coverImage(book.getCoverImage())
                .price(book.getPrice())
                .build();
        return bookDto;
    }

}
