package com.library.library;

import com.library.library.Books.Book;
import com.library.library.Books.BookService;
import com.library.library.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(BookService.class)
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testAddNewBook() {
        // Создаем новую книгу
        Book newBook = new Book();
        newBook.setName("New Book");
        newBook.setAuthor(1L);
        newBook.setYearOfPublishing("2021");
        newBook.setCost(100);
        newBook.setGenres(new ArrayList<>(Arrays.asList("Genre1", "Genre2")));

        // Добавляем книгу в базу данных
        bookService.addNewBook(newBook);

        // Проверяем, что книга была добавлена
        Optional<Book> foundBook = bookRepository.findBookById(newBook.getId());
        assertTrue(foundBook.isPresent());
        assertEquals(newBook.getName(), foundBook.get().getName());
    }

    @Test
    public void testDeleteBookById() {
        // Добавляем и удаляем книгу
        Book book = new Book();
        book.setName("Book to Delete");
        book.setAuthor(2L);
        book.setYearOfPublishing("2020");
        book.setCost(200);
        book.setGenres(new ArrayList<>(List.of("Genre1")));
        book = bookRepository.save(book);

        bookService.deleteBookById(book.getId());

        // Проверяем, что книга была удалена
        assertFalse(bookRepository.existsById(book.getId()));
    }

    @Test
    public void testGetBooks() {
        Book book = new Book();
        book.setName("Book to Delete");
        book.setAuthor(2L);
        book.setYearOfPublishing("2020");
        book.setCost(200);
        book.setGenres(new ArrayList<>(List.of("Genre1")));
        bookRepository.save(book);
        // Получаем список всех книг
        List<Book> books = bookService.getBook();

        // Проверяем, что список книг не пуст
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    public void testUpdateBook() {
        // Обновляем информацию о книге
        Book book = new Book();
        book.setName("Book to Update");
        book.setAuthor(3L);
        book.setYearOfPublishing("2019");
        book.setCost(300);
        book.setGenres(new ArrayList<>(List.of("Genre1")));
        book = bookRepository.save(book);

        bookService.updateBook(book.getId(), "Updated Book", 4L, "2022",
                150, new ArrayList<>(List.of("Genre2")));

        // Проверяем, что информация о книге была обновлена
        Book updatedBook = bookRepository.findById(book.getId()).orElse(null);
        assertNotNull(updatedBook);
        assertEquals("Updated Book", updatedBook.getName());
        assertEquals("2022", updatedBook.getYearOfPublishing());
        assertEquals(4L, updatedBook.getAuthorId());
        assertEquals(150, updatedBook.getCost());
        assertTrue(updatedBook.getGenres().contains("Genre2"));
    }
}