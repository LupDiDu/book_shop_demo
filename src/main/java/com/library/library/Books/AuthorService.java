package com.library.library.Books;

import com.library.library.Repository.AuthorRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class AuthorService {
    public final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Long id) {
        return authorRepository.findAuthorById(id);
    }

    public void addNewAuthor(Author author) {
        authorRepository.save(author);
    }

    public void deleteAuthorById(Long authorId) {
        boolean exists = authorRepository.existsById(authorId);
        if (exists){
            authorRepository.deleteById(authorId);
        }
        else {
            throw new IllegalStateException("Author with id: " + authorId + " doesn't exists.");
        }
    }

    @Transactional
    public void updateAuthor(Long authorId, String name, String  surname, Calendar dateOfBirth, Calendar dateOfDeath) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(()-> new IllegalStateException("Author with id: " + authorId + " doesn't exists."));

        if(name != null && name.length() > 0 && Objects.equals(author.getName(), name)){
            author.setName(name);
        }

        if(surname != null && surname.length() > 0 && Objects.equals(author.getSurname(), surname)){
            author.setSurname(surname);
        }


    }
}
