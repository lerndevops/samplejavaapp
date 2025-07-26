package com.devopsdemo.tutorial.addressbook;

import com.devopsdemo.tutorial.addressbook.backend.Contact;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */
public class ContactForm extends FormLayout {

    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField phone = new TextField("Phone");
    private final TextField email = new TextField("Email");
    private final DatePicker birthDate = new DatePicker("Birth date");

    private final Binder<Contact> binder = new Binder<>(Contact.class);
    private Contact contact;

    public ContactForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        save.addClickListener(e -> save());
        cancel.addClickListener(e -> cancel());

        binder.bindInstanceFields(this);
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        add(actions, firstName, lastName, phone, email, birthDate);
    }

    private void save() {
        if (contact != null) {
            binder.writeBeanIfValid(contact);
            Notification.show(String.format("Saved '%s %s'.", contact.getFirstName(), contact.getLastName()));
            getUI().ifPresent(ui -> ((AddressbookUI) ui).refreshContacts());
        }
    }

    private void cancel() {
        Notification.show("Cancelled");
        getUI().ifPresent(ui -> ((AddressbookUI) ui).contactList.deselectAll());
    }

    public void edit(Contact contact) {
        this.contact = contact;
        if (contact != null) {
            binder.readBean(contact);
            firstName.focus();
        }
        setVisible(contact != null);
    }
}
