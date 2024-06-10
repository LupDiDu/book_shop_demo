package com.library.library;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library.Books.Book;
import com.library.library.Books.BookController;
import com.library.library.Books.BookService;
import com.library.library.Repository.AuthorRepository;
import com.library.library.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
@WithMockUser(username = "user", roles = {"ADMIN"})
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private CustomerRepository customerRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book("Test Book",1L, "2024", 325, 100, new ArrayList<>());
        book.setId(1L);
    }

    @Test
    void testGetBook() throws Exception {
        List<Book> books = List.of(book);
        when(bookService.getBook()).thenReturn(books);
        mockMvc.perform(get("/api/addBook"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(book.getId()))
                .andExpect(jsonPath("$[0].name").value(book.getName()));
        verify(bookService, times(1)).getBook();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testRegistryNewBook() throws Exception {
        mockMvc.perform(post("/api/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book))
                        .with(csrf()))
                .andExpect(status().isOk());
        verify(bookService, times(1)).addNewBook(any(Book.class));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/addBook/bookId")
                        .param("bookId", String.valueOf(book.getId())).with(csrf()))
                .andExpect(status().isOk());
        verify(bookService, times(1))
                .deleteBookById(book.getId());
    }

    @Test
    void testUpdateBook() throws Exception {
        mockMvc.perform(put(
                        "/api/addBook/bookId")
                        .param("bookId", String.valueOf(book.getId()))
                        .param("name", "Updated Book")
                        .param("author", "24L")
                        .param("yearOfPublishing", "2025")
                        .param("cost", "150").with(csrf()))
                .andExpect(status().isOk());
        verify(bookService, times(
                1
        )).updateBook(eq(book.getId()), eq("Updated Book"), eq(24L),
                eq("2025"), eq(150), any(ArrayList.class));
    }
}