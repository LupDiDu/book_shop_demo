package com.library.library.View;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLayout;

public class MainView extends Div implements RouterLayout {
    private Div contentWrapper;

    public static TextField searchField = new TextField();

    public MainView() {
        contentWrapper = new Div();
        contentWrapper.setSizeFull();
        add(contentWrapper);

        // Ссылка на главную страницу
        Button homeLink = new Button("Home");
        homeLink.setHeight("50px");
        homeLink.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("/")));
        add(homeLink);

        Button searchButton = new Button(VaadinIcon.SEARCH.create());
        searchButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,
                ButtonVariant.LUMO_CONTRAST);
        searchButton.setHeight("40px");

        // Поле для поиска
        searchField.setPlaceholder("Search...");
        searchField.setSuffixComponent(searchButton);

        searchField.setMinHeight("50px");
        searchField.setMinWidth("500px");

        searchField.setMaxHeight("50px");
        searchField.setMaxWidth("500px");


        searchButton.addClickListener(e -> getUI().
                ifPresent(ui -> ui.navigate("/search?q=" + searchField.getValue())));

        add(searchField);

        // Элемент круга в форме ссылки на кабинет пользователя
        Button userLink = new Button(new Icon(VaadinIcon.CIRCLE));
        userLink.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("/")));
        userLink.setHeight("50px");
        add(userLink);


        HorizontalLayout layout = new HorizontalLayout(homeLink, searchField, userLink);
        layout.setWidthFull();
        add(layout);

        contentWrapper.addComponentAsFirst(layout);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        contentWrapper.getElement().appendChild(content.getElement());
    }
}