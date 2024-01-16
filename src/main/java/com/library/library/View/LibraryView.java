package com.library.library.View;

import com.library.library.Books.Book;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
@Route(value = "/", layout = MainView.class)
@PageTitle("Library")
public class LibraryView extends VerticalLayout {
    private BookRepository bookRepository;
    private Component imageComponent;

    public LibraryView(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        List<Book> books = bookRepository.findAll();
        int index = 0;
        while (index < books.size()) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

            for (int i = 0; i < 4 && index < books.size(); i++) {
                VerticalLayout imageAndUrlLayout = new VerticalLayout();

                Book book = books.get(index);

                Anchor bookLink = new Anchor("/book/" + book.getId());
                Div bookInfo = new Div();
                Text bookName = new Text(book.getName() + " ");
                Text authorName = new Text(book.getAuthor());
                Html lineBreak = new Html("<br>");

                String bookImage = book.getImage();
                if(bookImage == null) {
                    bookImage = "https://gnbuffzrevgnubafcjdt.supabase.co" +
                            "/storage/v1/object/sign/Book%20image/" +
                            "blank-cover-for-book-or-magazine-template_212889-605.jpg" +
                            "?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJCb29rIGltYWdlL2JsYW5rLWNvdmVyLW" +
                            "Zvci1ib29rLW9yLW1hZ2F6aW5lLXRlbXBsYXRlXzIxMjg4OS02MDUuanBnIiwiaWF0IjoxNzA1NDI5MjcxLCJleHA" +
                            "iOjE4NjMxMDkyNzF9.amaXUxpjThWT1G-KALZma-oPEDli246StkXFxr11q9w&t=2024-01-16T18%3A21%3A07.880Z";

                    Image image = new Image(bookImage, "");
                    image.setHeight("300px");
                    image.setWidth("200px");
                    Component imageComponent = image;

                    bookInfo.add(bookName, lineBreak, authorName);
                    bookLink.add(imageComponent, bookInfo);
                }
                else{
                    Image image = new Image(bookImage, book.getName());
                    image.setHeight("300px");
                    image.setWidth("200px");
                    Component imageComponent = image;

                    bookInfo.add(bookName, lineBreak, authorName);
                    bookLink.add(imageComponent, bookInfo);
                }

                bookLink.getElement().getStyle().set("text-decoration", "none");
                bookLink.getElement().getStyle().set("color", "black");

                imageAndUrlLayout.add(bookLink);
                horizontalLayout.add(imageAndUrlLayout);
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
