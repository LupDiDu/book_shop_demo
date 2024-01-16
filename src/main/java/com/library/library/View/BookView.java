package com.library.library.View;

import com.library.library.Books.Book;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;

@Route("book/${id}")
public class BookView extends VerticalLayout {
    BookRepository bookRepository;
    Book book;

    public BookView(RouteParameters id) {
        int bookID = Integer.parseInt(id.get("id").orElse(""));

        book = bookRepository.findBookById(bookID).get();

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout(verticalLayout);
        Component nameLabel = new Span(book.getName());
        Component idLabel = new Span(String.valueOf(book.getId()));
        Component costLabel = new Span(String.valueOf(book.getCost()));
        Component authorLabel = new Span(book.getAuthor());
        Component yearOfPublishingLabel = new Span(book.getYearOfPublishing());

        verticalLayout.add(nameLabel, idLabel, costLabel, authorLabel, yearOfPublishingLabel);

        add(horizontalLayout, verticalLayout);
    }
}