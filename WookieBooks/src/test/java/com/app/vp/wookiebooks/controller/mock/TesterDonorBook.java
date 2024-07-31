package com.app.vp.wookiebooks.controller.mock;

public class TesterDonorBook {



//    @Test
//    void findBookByTitle() throws Exception {
//        //create & save author
//        User user1 = User.builder()
//                .authorPseudonym("LinkTest")
//                .build();
//        userService.createUser(user1);
//        //create & save book
//        Book book = Book.builder()
//                .title("Test title1")
//                .description("Test description1")
//                .author(user1)
//                .price(20.99)
//                .coverImage("Test cover image")
//                .build();
//        Book savedBook = bookService.createBook(book);
//        String bookTitle = savedBook.getTitle();
//        //testing endpoint findBookByTitle
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .get(BASE_URL)
//                        .param("title", bookTitle)
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //find book that has been saved by Title
//        Optional<Book> optionalBook = Optional.of(bookService.findBookByTitle(bookTitle)).orElseThrow();
//        //assert that result of endpoint is the same as book been saved
//        assertThat(result.getResponse().getContentAsString())
//                .isEqualTo(toJson(mapToBookDto(optionalBook.get())));
//    }
//
//    @Test
//    void findAllBooks() throws Exception {
//        //create & save author
//        User user11 = User.builder()
//                .authorPseudonym("TestAuthorAAA")
//                .build();
//        userService.createUser(user11);
//        //create & save books
//        Book book11 = Book.builder()
//                .title("Test title11")
//                .description("Test description1")
//                .author(user11)
//                .price(20.99)
//                .coverImage("Test cover image")
//                .build();
//        Book book12 = Book.builder()
//                .title("Test title12")
//                .description("Test description2")
//                .author(user11)
//                .price(20.99)
//                .coverImage("Test cover image")
//                .build();
//        bookService.createBook(book11);
//        bookService.createBook(book12);
//        //check if books been saved
//        List<Book> savedBooks = bookService.findAllBooks();
//        int sizeOfSavedBooksList = savedBooks.size();
//        System.out.println("sizeOfSavedBooksList = " + sizeOfSavedBooksList); //expected: 2
//        //testing endpoint findAllBooks
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .get(BASE_URL)
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //check if savedBooks and response are equal
//        List<BookDto> booksSaved = mapToListDtoBooks(savedBooks);
//        System.out.println("booksSaved = " + toJson(booksSaved));//saved
//        System.out.println("result = " + result.getResponse().getContentAsString());//response
//
//        assertThat(toJson(booksSaved).equals(result.getResponse().getContentAsString()));
//
//    }
//
//    @Test
//    void findAllBooksByUserId() throws Exception {
//        //create & save author
//        User userA = User.builder()
//                .authorPseudonym("TrinityTest")
//                .build();
//        User savedUser = userService.createUser(userA);
//        Long userId = savedUser.getUserId();
//        //create & save books
//        Book book122 = Book.builder()
//                .title("title1")
//                .description("Test description1")
//                .author(userA)
//                .price(10.99)
//                .coverImage("Test cover image")
//                .build();
//        Book book222 = Book.builder()
//                .title("title2")
//                .description("Test description2")
//                .author(userA)
//                .price(30.99)
//                .coverImage("Test cover image")
//                .build();
//        bookService.createBook(book122);
//        bookService.createBook(book222);
//        //check if books been saved
//        Optional<List<Book>> savedBooks = Optional.of(bookService
//                .findAllBooksByAuthorPseudonym(userA.getAuthorPseudonym())).orElseThrow();
//        int sizeOfSavedBooksList = savedBooks.get().size();
//        System.out.println("sizeOfSavedBooksList = " + sizeOfSavedBooksList); //expected: 2
//        //testing endpoint findAllBooksByUserId
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .get(BASE_URL + "/{userId}", userId)
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //list of books that has been saved mapping to dto
//        List<BookDto> bookDtoList = mapToListDtoBooks(savedBooks.get());
//        //assert that result of endpoint is the same as books been saved (dto)
//        assertThat(result.getResponse().getContentAsString())
//                .isEqualTo(toJson((bookDtoList)));
//    }
//
//    @Test
//    void findAllBooksByAuthorPseudonym() throws Exception {
//        //create & save author
//        User userB = User.builder()
//                .authorPseudonym("TestTest")
//                .build();
//        User savedUser = userService.createUser(userB);
//        String authorPseudonym = savedUser.getAuthorPseudonym();
//        Long userId = savedUser.getUserId();
//        //create & save books
//        Book bookA = Book.builder()
//                .title("title1test")
//                .description("Test description1")
//                .author(userB)
//                .price(20.99)
//                .coverImage("Test cover image")
//                .build();
//        Book bookB = Book.builder()
//                .title("title2test")
//                .description("Test description2")
//                .author(userB)
//                .price(20.99)
//                .coverImage("Test cover image")
//                .build();
//        bookService.createBook(bookA);
//        bookService.createBook(bookB);
//        //check if books been saved
//        Optional<List<Book>> allBooksByUserId = Optional.of(bookService.findAllBooksByUserId(userId)).orElseThrow();
//        int allBookSize = allBooksByUserId.get().size();
//        System.out.println("allBookSize = " + allBookSize); //expected: 2
//        //testing endpoint findAllBooksByAuthorPseudonym
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .get(BASE_URL)
//                        .param("authorPseudonym", authorPseudonym)
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //list of books that has been saved mapping to dto
//        List<BookDto> bookDtoList = mapToListDtoBooks(allBooksByUserId.get());
//        //assert that result of endpoint is the same as books been saved (dto)
//        assertThat(result.getResponse().getContentAsString())
//                .isEqualTo(toJson((bookDtoList)));
//    }
//
//    @Test
//    void findBooksByPriceLessThan() throws Exception {
////        //create & save author
////        User userC = User.builder()
////                .authorPseudonym("TestAuthorCCC")
////                .build();
////        User user = userService.createUser(userC);
////        Long userId = user.getUserId();
////        //create & save books
////        Book bookA = Book.builder()
////                .title("test111")
////                .description("Test description1")
////                .author(userC)
////                .price(10.99)
////                .coverImage("Test cover image")
////                .build();
////        Book bookB = Book.builder()
////                .title("test222")
////                .description("Test description2")
////                .author(userC)
////                .price(20.99)
////                .coverImage("Test cover image")
////                .build();
////        Book bookC = Book.builder()
////                .title("test333")
////                .description("Test description2")
////                .author(userC)
////                .price(30.99)
////                .coverImage("Test cover image")
////                .build();
////        Book book = bookService.createBook(bookA);
////        bookService.createBook(bookB);
////        bookService.createBook(bookC);
////        //modeling the list of price less than (as expected after filtering by price)
////        List<Book> booksLessTan = new ArrayList<>();
////        booksLessTan.add(book);
//
//        //check if we already have a books been saved
//        List<Book> bookList = Optional.of(bookService.findAllBooks()).orElseThrow();
//        int sizeListBooks = bookList.size();
//        System.out.println("sizeListBooks = " + sizeListBooks); //expected: 3
//        //testing endpoint findBooksByPriceLessThan
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .get("/api/wookie_books/books/findBooksByPriceLessThan")
//                        .param("price", String.valueOf(20.99))
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //expected book
//        List<BookDto> bookDtoList = mapToListDtoBooks(bookList);
//        List<BookDto> bookByPriceLessThan = new ArrayList<>();
//        for (BookDto bookDto : bookDtoList) {
//            if(bookDto.getPrice() < 20.99){
//                bookByPriceLessThan.add(bookDto);
//            }
//        }
//        System.out.println("bookByPriceLessThan = " + bookByPriceLessThan.size()); //expected: 1
//        for (BookDto bookDto : bookByPriceLessThan) {
//            System.out.println(bookDto.toString());
//        }
//        assertThat(result.getResponse().getContentAsString())
//                .isEqualTo(toJson((bookByPriceLessThan)));
//    }
//
//    @Test
//    void findBooksByMiddlePrice() throws Exception {
//        //just to see what books are in db for the moment
//        List<Book> bookList = Optional.of(bookService.findAllBooks()).orElseThrow();
//        int sizeListBooks = bookList.size();
//        System.out.println("sizeListBooks = " + sizeListBooks); //expected: 3
//        System.out.println("bookList = " + bookList);
//        //testing endpoint findBooksByMiddlePrice
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .get("/api/wookie_books/books/findBooksByMiddlePrice")
//                        .param("minPrice", String.valueOf(11.99))
//                        .param("maxPrice", String.valueOf(30.99))
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //expected book
//        List<BookDto> bookDtoList = mapToListDtoBooks(bookList);
//        List<BookDto> findBooksByMiddlePrice = new ArrayList<>();
//        for (BookDto bookDto : bookDtoList) {
//            if(bookDto.getPrice() >= 11.99 & bookDto.getPrice() < 30.99){
//                findBooksByMiddlePrice.add(bookDto);
//            }
//        }
//        System.out.println("findBooksByMiddlePrice = " + findBooksByMiddlePrice.size()); //expected: 1
//        for (BookDto bookDto : findBooksByMiddlePrice) {
//            System.out.println(bookDto.toString());
//        }
//        assertThat(result.getResponse().getContentAsString())
//                .isEqualTo(toJson((findBooksByMiddlePrice)));
//
//    }
//
//    @Test
//    void findBooksByHighPrice() throws Exception {
//        //just to see what books are in db for the moment
//        List<Book> bookList = Optional.of(bookService.findAllBooks()).orElseThrow();
//        int sizeListBooks = bookList.size();
//        System.out.println("sizeListBooks = " + sizeListBooks); //expected: 3
//        System.out.println("bookList = " + bookList);
//        //testing endpoint findBooksByMiddlePrice
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .get("/api/wookie_books/books/findBooksByHighPrice")
//                        .param("minPrice", String.valueOf(30.99))
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //expected book
//        List<BookDto> bookDtoList = mapToListDtoBooks(bookList);
//        List<BookDto> findBooksByHighPrice = new ArrayList<>();
//        for (BookDto bookDto : bookDtoList) {
//            if(bookDto.getPrice() >= 30.99){
//                findBooksByHighPrice.add(bookDto);
//            }
//        }
//        System.out.println("findBooksByHighPrice = " + findBooksByHighPrice.size()); //expected: 1
//        for (BookDto bookDto : findBooksByHighPrice) {
//            System.out.println(bookDto.toString());
//        }
//        assertThat(result.getResponse().getContentAsString())
//                .isEqualTo(toJson((findBooksByHighPrice)));
//    }
//
//    @Test
//    void findBookAndUserByUserId() {
//    }
//
//    @Test
//    void deleteBookByUserTest() throws Exception {
//        //scenario:
//        //1)create and save user
//        User user = User.builder() //create
//                .authorPseudonym("John")
//                .authorPassword("1233334455")
//                .build();
//        User savedUser = userService.createUser(user);//save
//        String authorPseudonym = savedUser.getAuthorPseudonym();//get pseudonym to help to find existing user
//        //2)create a couple of books for this user
//        Book book1 = Book.builder() //create
//                .title("TestBook1")
//                .author(user)
//                .price(20.99)
//                .coverImage("cover")
//                .description("text")
//                .build();
//        Book book1Saved = bookService.createBook(book1);
//        Long bookId = book1Saved.getBookId();
//        Book book2 = Book.builder() //create
//                .title("TestBook2")
//                .author(user)
//                .price(20.99)
//                .coverImage("cover")
//                .description("text")
//                .build();
//        bookService.createBook(book2);
//        //3)find all books by this user
//        Optional<List<Book>> books = bookService.findAllBooksByAuthorPseudonym(authorPseudonym);
//        //4)define a book wanted to delete
//        System.out.println("-------books before delete-------");
//        if(books.isPresent()){
//            List<Book> bookList = books.get();
//            for (Book book : bookList) {
//                String bookTitle = book.getTitle();
//                System.out.println(bookTitle);
//            }
//        }
//        //5)delete the book
//        //testing endpoint
//        Long userId = savedUser.getUserId();
//        var result = mockMvc.perform(MockMvcRequestBuilders
//                        .delete(BASE_URL + "/user/deleteBookByUser/{userId}", userId)
//                        .param("bookId", String.valueOf(bookId))
//                        .contentType("application/json")
//                        .accept("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        //6)check if book was removed
//        Optional<List<Book>> booksUpdated = bookService.findAllBooksByAuthorPseudonym(authorPseudonym);
//        System.out.println("-------books after delete-------");
//        if(booksUpdated.isPresent()){
//            List<Book> bookList = booksUpdated.get();
//            for (Book book : bookList) {
//                System.out.println(book);
//            }
//        }
//        System.out.println("result = " + result.getResponse().getContentAsString());
//        //assertion
//        List<Book> bookList = booksUpdated.get();
//        List<BookDto> bookDtoList = BookMapper.mapToListDtoBooks(bookList);
//        assertThat(result.getResponse().getContentAsString().equals(toJson(bookDtoList)));
//    }
//
//    @Test
//    void updateBookByUserTest() {
//
//    }

}
