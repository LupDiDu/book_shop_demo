package com.library.library.View;


import com.library.library.Books.Author;
import com.library.library.Repository.AuthorRepository;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

@Route(value = "/author/:id", layout = MainView.class)
@PageTitle("Author")
public class AuthorView extends VerticalLayout implements BeforeEnterObserver {
    public String id;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Author author;
    private Component authorDateOfDeath;


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        id = event.getRouteParameters().get("id").get();
        Long authorId = Long.valueOf(id);
        author = authorRepository.findAuthorById(authorId);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout verticalLayoutAuthorInformation = new VerticalLayout();

        Component nameLabel = new Span("Name: " + author.getName() + " " + author.getSurname());
        Component idLabel = new Span("Id : " + String.valueOf(author.getId()));
        Component dateOfBirthLabel = new Span("Date of birth: " +
                String.valueOf(author.getDateOfBirth().get(Calendar.YEAR))
                + "-" + String.valueOf(author.getDateOfBirth().get(Calendar.MONTH))
                + "-" + String.valueOf(author.getDateOfBirth().get(Calendar.DAY_OF_MONTH)));

        if(author.getDateOfDeath() == null){
            authorDateOfDeath = new Span("Date of death: -");
        }
        else{
            authorDateOfDeath = new Span("Date of death: " +   String.valueOf(author.getDateOfDeath().get(Calendar.YEAR))
                    + "-" + String.valueOf(author.getDateOfDeath().get(Calendar.MONTH))
                    + "-" + String.valueOf(author.getDateOfDeath().get(Calendar.DAY_OF_MONTH)));
        }
        verticalLayoutAuthorInformation.add(nameLabel, idLabel, dateOfBirthLabel, authorDateOfDeath);
        horizontalLayout.add(verticalLayoutAuthorInformation);
        add(horizontalLayout);
    }
    @Autowired
    public AuthorView( BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
}