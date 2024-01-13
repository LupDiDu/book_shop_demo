package com.library.library.Books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

    @RestController
    @RequestMapping("/api/addUser")
    public class BookController {

        private final BookService bookService;

        @Autowired
        public BookController(BookService bookService) {
            this.bookService = bookService;
        }

        @GetMapping
        public List<Book> getBook(){
            return bookService.getBook();
        }

        @PostMapping
        public void registryNewBook(@RequestBody Book book){
            bookService.addNewBook(book);
        }
        @DeleteMapping(path = "bookId")
        public void deleteCustomer(@PathVariable("bookId") Long bookId){
            bookService.deleteBookById(bookId);
        }

        @PutMapping(path = "bookId")
        public void updateCustomer(@PathVariable("bookId") Long bookId,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String author,
                                   @RequestParam(required = false) String yearOfPublishing,
                                   @RequestParam(required = false) int cost,
                                   @RequestParam(required = false) ArrayList<String> genres
                                   ){
            bookService.updateBook(bookId, name, author, yearOfPublishing, cost, genres);
        }

    }

