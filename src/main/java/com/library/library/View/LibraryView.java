package com.library.library.View;

import com.library.library.Books.Book;
import com.library.library.Components.LibraryComponent;
import com.library.library.Repository.AuthorRepository;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
@Route(value = "/", layout = MainView.class)
@RouteAlias(value = "/search/:search", layout = MainView.class)
@RouteAlias(value = "/search", layout = MainView.class)

@PageTitle("Library")
public class LibraryView extends VerticalLayout implements BeforeEnterObserver {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Component imageComponent;
    private Text authorName;
    List<Book> books;
    String search;
    VerticalLayout verticalLayout = new VerticalLayout();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            search = event.getRouteParameters().get("search").get();
            System.out.println("Search: " + search);
            books = bookRepository.findByName(search);
            System.out.println("Book: " + books);
            LibraryComponent libraryComponent = new LibraryComponent(books, authorRepository);

            try{
                if (!verticalLayout.getChildren().findFirst().isPresent()) {
                    System.out.println("VerticalLayout не содержит компонентов");
                }
                else {
                    verticalLayout.removeAll();
                    System.out.println("Все компоненты успешно удалены из VerticalLayout");
                }
            }
            catch (Exception ex){}
            verticalLayout.add(libraryComponent);
            add(verticalLayout);
        }
        catch (Exception e){
            search = "";
            books = bookRepository.findAll();
            LibraryComponent libraryComponent = new LibraryComponent(books, authorRepository);
            try{
                if (!verticalLayout.getChildren().findFirst().isPresent()) {
                System.out.println("VerticalLayout не содержит компонентов");
            }
                else {
                verticalLayout.removeAll();
                System.out.println("Все компоненты успешно удалены из VerticalLayout");
            }
            }
            catch (Exception ex){}
            verticalLayout.add(libraryComponent);
            add(verticalLayout);
        }
    }

    public LibraryView(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
}
