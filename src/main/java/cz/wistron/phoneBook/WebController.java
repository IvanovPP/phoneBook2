package cz.wistron.phoneBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

//creating the REST controller
@RestController
@RequestMapping(path = "/contacts")
public class WebController {
    //Injecting the ContactDAO class
    @Autowired
    private ContactDAO contactDAO;
    //Method to return the list of contacts
    @GetMapping(
            path = "/",
            produces = "application/json")
    public contactList getAllContacts() {
        return contactDAO.getAllContacts();
    }
    //Create post method to add a contact to the list
    @PostMapping(
            path = "/",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Object> addContact(@RequestBody Contact contact)
    {
        //Creating an ID of contact from number of contacts in the list
        Integer id = contactDAO.getAllContacts().getContactList().size() + 1;
        contact.setId(id);
        //Adding the contact to the list
        contactDAO.addContact(contact);
        //Creating the URI of the newly added contact
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contact.getId()).toUri();
        //Returning the response with the status created
        return ResponseEntity.created(location).build();
    }
    //Method to remove a contact from the list
    @DeleteMapping
    public void removeContact(@RequestBody Contact contact) {
        contactDAO.removeContact(contact);
    }
    //Method to update a contact in the list
    @PutMapping
    public void updateContact(@RequestBody Contact contact) {
        contactDAO.removeContact(contact);
        contactDAO.addContact(contact);
    }
}
