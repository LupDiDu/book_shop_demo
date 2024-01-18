package com.library.library.Books;

import com.library.library.Repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class BookService {
    public final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBook() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        Optional<Book> bookByName = bookRepository.findBookById(book.getId());
        if (bookByName.isPresent()) {
            throw new IllegalStateException("Book with this name already exists");
        } else {
            bookRepository.save(book);
        }
    }

    public void deleteBookById(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (exists){
            bookRepository.deleteById(bookId);
        }
        else {
            throw new IllegalStateException("Book with id: " + bookId + " doesn't exists.");
        }
    }

    @Transactional
    public void updateBook(Long bookId, String name, Long  author,
                           String yearOfPublishing, int cost, ArrayList<String> genres){ {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new IllegalStateException("Book with id: " + bookId + " doesn't exists."));

        if(name != null && name.length() > 0 && Objects.equals(book.getName(), name)){
            book.setName(name);
        }

        if(author != null && author.toString().length() > 0 && Objects.equals(book.getAuthorId(), author)){
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if (bookOptional.isPresent()){
                throw new IllegalStateException("Author is the same.");
            }
            book.setAuthor(author);
        }

        if(yearOfPublishing.length() == 4 && Objects.equals(book.getYearOfPublishing(), yearOfPublishing)){
            book.setYearOfPublishing(yearOfPublishing);
        }

        if(cost > 0 && Objects.equals(book.getCost(), cost)){
            book.setCost(cost);
        }

        if(genres != null && genres.size() > 0 && Objects.equals(book.getGenres(), genres)){
            book.setGenres(genres);
        }

    }
}

}
