package com.library.library.View;

import com.library.library.Books.Book;
import com.library.library.Repository.BookRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;

@Route(value = "/book/:id", layout = MainView.class)
@PageTitle("Book")
public class BookView extends VerticalLayout  implements BeforeEnterObserver{

    //Инициализируем репозитории и книгу
    private BookRepository bookRepository;
    private Book book;

    public String id;


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        System.out.println("LOGCHECK");
        id = event.getRouteParameters().get("id").get();
        System.out.println("ID: " + id);
        //Получаем ID книги из URL
//        String bookIdString = getThisBookId();

        //Конвертируем String в Long
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
        Component authorLabel = new Span("Author: " + book.getAuthor());
        Component yearOfPublishingLabel = new Span("Year of publishing: " + book.getYearOfPublishing());

        horizontalLayout.add(image);
        verticalLayout.add(nameLabel, idLabel, costLabel, authorLabel, yearOfPublishingLabel);
        horizontalLayout.add(verticalLayout);
        add(horizontalLayout);
    }


    public BookView(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private String getThisBookId() {
        //Получаем Url запроса
        String pathInfo = VaadinService.getCurrentRequest().getPathInfo();
        //Игнорируем перый слеш
        pathInfo = pathInfo.substring(1);

        // Извлекаем значение параметра bookId из пути запроса
        int slashIndex = pathInfo.indexOf("/");
        // Возвращаем bookId
        return pathInfo.substring(slashIndex + 1);
    }

}
