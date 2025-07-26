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
    private boolean isUIAvailable() {
        try {
            return com.vaadin.flow.component.UI.getCurrent() != null;
        } catch (Exception e) {
            return false;
        }
    }
    public interface ContactFormListener {
        void onSave();
        void onCancel();
    }

    private ContactFormListener listener;
    public void setListener(ContactFormListener listener) {
        this.listener = listener;
    }

    public final Button save = new Button("Save");
    public final Button cancel = new Button("Cancel");
    public final TextField firstName = new TextField("First name");
    public final TextField lastName = new TextField("Last name");
    public final TextField phone = new TextField("Phone");
    public final TextField email = new TextField("Email");
    public final DatePicker birthDate = new DatePicker("Birth date");

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
            if (isUIAvailable()) {
                Notification.show(String.format("Saved '%s %s'.", contact.getFirstName(), contact.getLastName()));
            }
            if (listener != null) listener.onSave();
        }
    }

    private void cancel() {
        if (isUIAvailable()) {
            Notification.show("Cancelled");
        }
        setVisible(false);
        if (listener != null) listener.onCancel();
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
