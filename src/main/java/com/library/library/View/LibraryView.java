package com.library.library.View;

import com.library.library.Books.Book;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
@Route("/library")
@PageTitle("Library")
public class LibraryView extends VerticalLayout {
    private BookRepository bookRepository;

    public LibraryView(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        List<Book> books = bookRepository.findAll();
        int index = 0;
        while (index < books.size()) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.getStyle().set("margin-right", "100px");

            // Добавляем 4 книги в текущую группу
            for (int i = 0; i < 4 && index < books.size(); i++) {
                Book book = books.get(index);
                String bookHtml = "<div><div>" + book.getName() + "</div><div>" + book.getAuthor() + "</div></div>";
                Html bookLabel = new Html(bookHtml);
                horizontalLayout.add(bookLabel);
                index++;
            }

            add(horizontalLayout);
        }

//
//        HorizontalLayout layout = new HorizontalLayout();
//        layout.setPadding(true);
//
//        layout.setAlignItems(FlexComponent.Alignment.CENTER);
//        layout.add(new TextArea("Text area 1"));
//        layout.add(new TextArea("Text area 2"));
//        layout.add(new TextArea("Text area 3"));
//
    }
//    private void showBooks() {
//        if (MainView.searchField.isEmpty()) {
//            customerGrid.setItems(customerRepository.findAll());
//        } else {
//            customerGrid.setItems(customerRepository.findCustomerByEmail(email));
//        }
//    }
}
