package com.app.burenok.controller.view;

import com.app.burenok.exception.ContactIdNotFoundException;
import com.app.burenok.model.Contact;
import com.app.burenok.service.IContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/contacts")
public class ContactViewController {

    private final IContactService contactService;

    public ContactViewController(IContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Render list of all available contacts
     */
    @GetMapping("list")
    public String listForm(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "list-contacts";
    }

    /**
     * Show contact creation page
     */
    @GetMapping("create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "create-contact";
    }

    /**
     * Creates new contact in system
     *
     * @param data contact that will be add to the system
     */
    @PostMapping("add")
    public String addContactForm(@Valid Contact data, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/contact/create-contact";
        }
        contactService.createContact(data);
        return "redirect:/contacts/list";
    }

    /**
     * Render for contact update
     *
     * @param id ID of contact to update
     * @throws ContactIdNotFoundException when contact not found
     */
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) throws ContactIdNotFoundException {
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            model.addAttribute("contact", contact);
            return "update-contact";
        }
        throw new IllegalArgumentException("Invalid contact Id:" + id);
    }

    /**
     * Updates contact with the given ID in the system
     *
     * @param id ID of contact to update
     * @throws ContactIdNotFoundException when contact not found
     */
    @PostMapping("update/{id}")
    public String updateContactForm(@PathVariable("id") long id, @Valid Contact contact, BindingResult result,
                                    Model model) throws ContactIdNotFoundException {
        if (result.hasErrors()) {
            return "update-contact";
        }
        contactService.updateContact(id, contact);
        model.addAttribute("contacts", contactService.getAllContacts());
        return "list-contacts";
    }

    /**
     * Delete contact with the given ID from the system
     *
     * @param id ID of contact to delete
     * @throws ContactIdNotFoundException when contact not found
     */
    @GetMapping("delete/{id}")
    public String deleteContact(@PathVariable("id") long id, Model model) throws ContactIdNotFoundException {
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            contactService.deleteContact(id);
            model.addAttribute("contacts", contactService.getAllContacts());
            return "list-contacts";
        }
        throw new IllegalArgumentException("Invalid contact Id:" + id);
    }
}
