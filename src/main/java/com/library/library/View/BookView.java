package com.library.library.View;

import com.library.library.Books.Author;
import com.library.library.Books.Book;
import com.library.library.Repository.AuthorRepository;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/book/:id", layout = MainView.class)
@PageTitle("Book")
public class BookView extends VerticalLayout  implements BeforeEnterObserver{

    //Инициализируем репозитории и книгу
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Book book;
    private Author author;
    private Component authorNameLabel;
    private Span authorLabel;
    private Anchor authorLink;
    public String id;



    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        id = event.getRouteParameters().get("id").get();

        Long bookId = Long.valueOf(id);

        // Получаем информацию о книге из репозитория
        book = bookRepository.findBookById(bookId).get();

        String imageUrl =  book.getImage();
        if(imageUrl == null){
            imageUrl = "https://gnbuffzrevgnubafcjdt.supabase.co" +
                    "/storage/v1/object/sign/Book%20image/" +
                    "blank-cover-for-book-or-magazine-template_212889-605.jpg" +
                    "?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJCb29rIGltYWdlL2JsYW5rLWNvdmVyLW" +
                    "Zvci1ib29rLW9yLW1hZ2F6aW5lLXRlbXBsYXRlXzIxMjg4OS02MDUuanBnIiwiaWF0IjoxNzA1NDI5MjcxLCJleHA" +
                    "iOjE4NjMxMDkyNzF9.amaXUxpjThWT1G-KALZma-oPEDli246StkXFxr11q9w&t=2024-01-16T18%3A21%3A07.880Z";
        }

        Image image = new Image(imageUrl, book.getName());
        image.setHeight("350px");
        image.setWidth("250px");

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout(verticalLayout);

        Component nameLabel = new Span("Name: " + book.getName());
        Component idLabel = new Span("Id : " + String.valueOf(book.getId()));
        Component costLabel = new Span("Cost: " + String.valueOf(book.getCost()) + "$");

        if(book.getAuthorId() == null){
            authorLabel = new Span("Author: Unknown author");
        }
        else{
            author = authorRepository.findAuthorById(book.getAuthorId());
            authorNameLabel = new Span(author.getName() + " " + author.getSurname());
            authorLink = new Anchor("/author/"
                    + authorRepository.findAuthorById(book.getAuthorId()).getId() + "/", authorNameLabel);
            Component author = new Span("Author: ");
            authorLabel = new Span(author, authorLink);
        }

        Component yearOfPublishingLabel = new Span("Year of publishing: " + book.getYearOfPublishing());
        Component genresLabel = new Span("Genres: " + book.getGenres());
        if(book.getDescription() == null){
            book.setDescription("");
        }

        Component descriptionLabel = new Span("Description to the book: " + book.getName());
        Div descriptionLabelWrapper = new Div(descriptionLabel);
        descriptionLabelWrapper.setWidth("300px");

        Component lineBreak = new Text("\n");
        Component descriptionText = new Text(book.getDescription());

        Component descriptionComponent = new Span(descriptionLabelWrapper, lineBreak, descriptionText);

        horizontalLayout.add(image);
        verticalLayout.add(nameLabel, authorLabel, genresLabel,yearOfPublishingLabel, idLabel, costLabel);
        horizontalLayout.add(verticalLayout);
        horizontalLayout.add(descriptionComponent);
        add(horizontalLayout);

    }

    @Autowired
    public BookView( BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

}
