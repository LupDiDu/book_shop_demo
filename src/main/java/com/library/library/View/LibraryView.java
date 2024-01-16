package com.library.library.View;

import com.library.library.Books.Book;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
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
            horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

            // Добавляем 4 книги в текущую группу
            for (int i = 0; i < 4 && index < books.size(); i++) {
                Book book = books.get(index);
                Anchor bookLink = new Anchor("https://localhost/book/" + book.getName() + "/" + book.getId());
                Div bookInfo = new Div();
                Text bookName = new Text(book.getName() + " ");
                Text authorName = new Text(book.getAuthor());
                Html lineBreak = new Html("<br>");

                bookInfo.add(bookName, lineBreak, authorName);
                bookLink.add(bookInfo);
                bookLink.getElement().getStyle().set("text-decoration", "none");
                bookLink.getElement().getStyle().set("color", "black");

//                Element anchorElement = bookLink.getElement();
//
//                anchorElement.addEventListener("click", event -> {
//                    getUI().ifPresent(ui -> ui.navigate("/book/"+ book.getName() + "/" + book.getId()));
//                });
                horizontalLayout.add(bookLink);


                index++;
            }

            add(horizontalLayout);
        }
    }
//    private void showBooks() {
//        if (MainView.searchField.isEmpty()) {
//            customerGrid.setItems(customerRepository.findAll());
//        } else {
//            customerGrid.setItems(customerRepository.findCustomerByEmail(email));
//        }
//    }
}
