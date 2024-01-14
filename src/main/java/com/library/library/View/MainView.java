package com.library.library.View;

import com.library.library.Components.CustomerEditor;
import com.library.library.Repository.CustomerRepository;
import com.library.library.Users.Customer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final CustomerEditor customerEditor;
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("Create Customer", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewButton);

    public CustomerRepository customerRepository;
    public Grid<Customer> customerGrid = new Grid<>(Customer.class);

    @Autowired
    public MainView(CustomerRepository customerRepository, CustomerEditor customerEditor) {
        this.customerRepository = customerRepository;
        this.customerEditor = customerEditor;

        add(customerGrid);

        filter.setPlaceholder("Type to filter");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> showCustomer(field.getValue()));

        add(toolbar, customerGrid, customerEditor);

        customerGrid
                .asSingleSelect()
                .addValueChangeListener(e -> customerEditor.editCustomer(e.getValue()));

        addNewButton.addClickListener(e -> customerEditor.editCustomer(new Customer()));

        customerEditor.setChangeHandler(() -> {
            customerEditor.setVisible(false);
            showCustomer(filter.getValue());
        });

        showCustomer("");

    }


    private void showCustomer(String email) {
        if (email.isEmpty()) {
            customerGrid.setItems(customerRepository.findAll());
        } else {
            customerGrid.setItems(customerRepository.findCustomerByEmail(email));
        }
    }
}
