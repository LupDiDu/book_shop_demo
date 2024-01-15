package com.library.library.Components;


import com.library.library.Repository.CustomerRepository;
import com.library.library.Users.Customer;
import com.library.library.Users.CustomerService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.beans.factory.annotation.Autowired;


@SpringComponent
    @UIScope
    public class CustomerEditor  extends VerticalLayout implements KeyNotifier {
        private final CustomerRepository customerRepository;
        private final CustomerService customerService;

        private Customer customer;

        private TextField name = new TextField("", "Name");
        private TextField email = new TextField("", "Email");
        private TextField password = new TextField("", "Password");


         public void setLabel(String value) {
            save.setText(value);
         }

    private Button save = new Button("Save");
        private Button cancel = new Button("Cancel");
        private Button delete = new Button("Delete");
        private RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
        private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete, radioButtonGroup);

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
        public CustomerEditor(CustomerRepository customerRepository, CustomerService customerService) {
            this.customerRepository = customerRepository;
            this.customerService = customerService;

            add(name, email, password, buttons);

            binder.bindInstanceFields(this);
            binder.forField(radioButtonGroup)
                    .bind(Customer::getRole, Customer::setRole);

            setSpacing(true);

            save.getElement().getThemeList().add("primary");
            delete.getElement().getThemeList().add("error");


            // RadioButtonGroup for customer or admin
            radioButtonGroup.setLabel("Which user you want to create?");
            radioButtonGroup.setItems("customer", "admin");

            radioButtonGroup.addValueChangeListener(event -> {
                String selectedValue = event.getValue();
            });
            add(radioButtonGroup);
            radioButtonGroup.setValue("customer");



            addKeyPressListener(Key.ENTER, e -> save());

            save.addClickListener(e -> save());
            delete.addClickListener(e -> delete());
            cancel.addClickListener(e -> editCustomer(customer));
            setVisible(false);
        }

        private void save() {
            String result = customerService.addNewCustomer(customer);
            if(result.equals("Success")) {
                customerRepository.save(customer);
                changeHandler.onChange();
            }
            else {
                Notification notification = Notification.show(result,
                        3000, Notification.Position.BOTTOM_START);
            }
        }

        private void delete() {
            if(customerService.deleteCustomerById(customer)){
                customerRepository.delete(customer);
                changeHandler.onChange();
            }
            else {
                Notification notification = Notification.show("There is no such customer with this id",
                        3000, Notification.Position.BOTTOM_START);
            }
        }


        public void editCustomer(Customer customer) {
            if (customer == null) {
                setVisible(false);
                setLabel("Save");
                return;
            }
//
            if (customer.getId() != null) {
                setLabel("Edit");
                this.customer = customerRepository.findById(customer.getId()).orElse(customer);
            } else {
                setLabel("Save");
                this.customer = customer;
            }
            setVisible(true);

            binder.setBean(this.customer);


            name.focus();

            cancel.addClickListener(e -> {
                setVisible(false);  });
        }
    }

