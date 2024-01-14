package com.library.library.Components;


import com.library.library.Repository.CustomerRepository;
import com.library.library.Users.Customer;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.beans.factory.annotation.Autowired;


    @SpringComponent
    @UIScope
    public class CustomerEditor  extends VerticalLayout implements KeyNotifier {
        private final CustomerRepository customerRepository;

        private Customer customer;

        private TextField name = new TextField("", "Name");
        private TextField email = new TextField("", "Email");
        private TextField password = new TextField("", "Password");

        private Button save = new Button("Save");
        private Button cancel = new Button("Cancel");
        private Button delete = new Button("Delete");
        private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);

        private Binder<Customer> binder = new Binder<>(Customer.class);

        private ChangeHandler changeHandler;

        public void setChangeHandler(ChangeHandler changeHandler) {
            this.changeHandler = changeHandler;
        }

        public ChangeHandler getChangeHandler() {
            return changeHandler;
        }

        public interface ChangeHandler {
            void onChange();
        }

        @Autowired
        public CustomerEditor(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;

            add(name, email, password, buttons);

            binder.bindInstanceFields(this);

            setSpacing(true);

            save.getElement().getThemeList().add("primary");
            delete.getElement().getThemeList().add("error");

            addKeyPressListener(Key.ENTER, e -> save());

            save.addClickListener(e -> save());
            delete.addClickListener(e -> delete());
            cancel.addClickListener(e -> editCustomer(customer));
            setVisible(false);
        }

        private void save() {
            customerRepository.save(customer);
            changeHandler.onChange();
        }

        private void delete() {
            customerRepository.delete(customer);
            changeHandler.onChange();
        }

        public void editCustomer(Customer customer) {
            if (customer == null) {
                setVisible(false);
                return;
            }

            if (customer.getId() != null) {
                this.customer = customerRepository.findById(customer.getId()).orElse(customer);
            } else {
                this.customer = customer;
            }

            binder.setBean(this.customer);

            setVisible(true);

            name.focus();
        }
    }

