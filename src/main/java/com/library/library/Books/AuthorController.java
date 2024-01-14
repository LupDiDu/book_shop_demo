package com.library.library.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/addAuthor")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthor(){
        return authorService.getAuthor();
    }

    @PostMapping
    public void registryNewAuthor(@RequestBody Author author){
        authorService.addNewAuthor(author);
    }
    @DeleteMapping(path = "authorId")
    public void deleteAuthor(@PathVariable("authorId") Long authorId){
        authorService.deleteAuthorById(authorId);
    }

    @PutMapping(path = "authorId")
    public void updateAuthor(@PathVariable("authorId") Long authorId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = false) Calendar dateOfBirth,
                               @RequestParam(required = false) Calendar dateOfDeath){
        authorService.updateAuthor(authorId, name, surname, dateOfBirth, dateOfDeath);
    }
}
