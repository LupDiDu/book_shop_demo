package com.library.library.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/Author/{id}")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Author getAuthor(@PathVariable("id") Long id){
        return authorService.getAuthor(id);
    }

    @PostMapping
    public void registryNewAuthor(@RequestBody Author author){
        authorService.addNewAuthor(author);
    }
    @DeleteMapping(path = "id")
    public void deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthorById(id);
    }

    @PutMapping(path = "id")
    public void updateAuthor(@PathVariable("id") Long id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = false) Calendar dateOfBirth,
                               @RequestParam(required = false) Calendar dateOfDeath){
        authorService.updateAuthor(id, name, surname, dateOfBirth, dateOfDeath);
    }
}
