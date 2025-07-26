package com.devopsdemo.tutorial.addressbook;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServlet;
import com.devopsdemo.tutorial.addressbook.backend.Contact;
import com.devopsdemo.tutorial.addressbook.backend.ContactService;

@Route("/")
public class AddressbookUI extends VerticalLayout {

    final TextField filter = new TextField("Filter contacts...");
    final Grid<Contact> contactList = new Grid<>(Contact.class);
    final Button newContact = new Button("New contact");
    final ContactForm contactForm = new ContactForm();
    final ContactService service = ContactService.createDemoService();

    public AddressbookUI() {
        contactForm.setListener(new ContactForm.ContactFormListener() {
            @Override
            public void onSave() {
                refreshContacts();
            }
            @Override
            public void onCancel() {
                contactList.deselectAll();
            }
        });
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        newContact.addClickListener(e -> contactForm.edit(new Contact()));

        filter.setPlaceholder("Filter contacts...");
        filter.addValueChangeListener(e -> refreshContacts(e.getValue()));

        contactList.setColumns("firstName", "lastName", "email");
        contactList.setSelectionMode(Grid.SelectionMode.SINGLE);
        contactList.asSingleSelect().addValueChangeListener(
                e -> contactForm.edit(e.getValue()));

        refreshContacts();
    }

    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(filter, newContact);
        actions.setWidthFull();
        filter.setWidthFull();
        actions.expand(filter);

        VerticalLayout left = new VerticalLayout(actions, contactList);
        left.setSizeFull();
        contactList.setSizeFull();
        left.expand(contactList);

        HorizontalLayout mainLayout = new HorizontalLayout(left, contactForm);
        mainLayout.setSizeFull();
        mainLayout.expand(left);

        add(mainLayout);
    }

    private void refreshContacts() {
        refreshContacts(filter.getValue());
    }

    private void refreshContacts(String stringFilter) {
        contactList.setItems(service.findAll(stringFilter));
        contactForm.setVisible(false);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    public static class MyUIServlet extends VaadinServlet {
        @Override
        protected void servletInitialized() throws ServletException {
            super.servletInitialized();
            getService().setClassLoader(getClass().getClassLoader());
        }
    }
}
