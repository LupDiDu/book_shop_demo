package com.library.library.View;

import com.library.library.Components.CustomerEditor;
import com.library.library.Repository.CustomerRepository;
import com.library.library.Users.Customer;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

@Route("/admin/user-control")
@PageTitle("Admin | UserControl")
public class AdminUserControlView extends VerticalLayout {

    private CustomerEditor customerEditor;
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("Create Customer", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewButton);

    private CustomerRepository customerRepository;
    private Grid<Customer> customerGrid = new Grid<>(Customer.class);

    @Autowired
    public AdminUserControlView(CustomerRepository customerRepository, CustomerEditor customerEditor) {
        this.customerRepository = customerRepository;
        this.customerEditor = customerEditor;

        filter.setPlaceholder("Type to filter");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> showCustomer(field.getValue()));

        customerGrid.setColumns("id", "name", "email", "password", "role");
        customerGrid.getColumns().forEach(col -> col.setAutoWidth(true));

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

    public AdminUserControlView() {

    }


    private void showCustomer(String email) {
        if (email.isEmpty()) {
            customerGrid.setItems(customerRepository.findAll());
        } else {
            customerGrid.setItems(customerRepository.findCustomerByEmail(email));
        }
    }
}