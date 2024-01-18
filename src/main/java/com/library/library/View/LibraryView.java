package com.library.library.View;

import com.library.library.Books.Book;
import com.library.library.Components.LibraryComponent;
import com.library.library.Repository.AuthorRepository;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
@Route(value = "/", layout = MainView.class)

@PageTitle("Library")
public class LibraryView extends VerticalLayout {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Component imageComponent;
    private Text authorName;

    public LibraryView(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        List<Book> books = bookRepository.findAll();
        LibraryComponent libraryComponent = new LibraryComponent( books, authorRepository);
        add(libraryComponent);

    }
}
