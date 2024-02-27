package cz.wistron.phoneBook.controller;

import cz.wistron.phoneBook.dto.ContactInformation;
import cz.wistron.phoneBook.service.PhoneBookService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//creating the REST controller
@RestController
@RequestMapping(path = "/contacts")
public class WebController {
    //Injecting the ContactDAO class
    @Autowired
    private PhoneBookService phoneBookService;

    //Method to return the list of contacts
    @GetMapping(
            path = "/",
            produces = "application/json")
    @ApiResponse(responseCode = "200")
    public @Valid //triggers automatic annotation-based Validation
    @ResponseBody ResponseEntity<List<ContactInformation>> getAllContacts() {
        return ResponseEntity.ok(phoneBookService.getContacts());
    }

    //Create post method to add a contact to the list
    @PostMapping(
            path = "/",
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400", description = "Invalid request") //what type of responses should the caller expect
            }
    )
    public @ResponseBody ResponseEntity<Integer> addContact(@Valid @RequestBody ContactInformation request) { //Valid annotation triggers validations for this Class
        //Creating an ID of contact from number of contacts in the list
        Integer id = phoneBookService.addContact(request);
        //Creating the URI of the newly added contact
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        //Returning the response with the status created
        return ResponseEntity.created(location).body(id);
    }

    //Method to remove a contact from the database
    @DeleteMapping(path = "/{contactId}") //path variable is the contactId you want to remove, no RequestBody here
    public void removeContact(@PathVariable Integer contactId) {
        phoneBookService.removeContact(contactId);
    }

    //Method to update a contact in the database
    //there should be a combination of PathVariable (ID) and RequestBody (new ContactInformation)
    //@PutMapping
    //public void updateContact(@RequestBody ContactInformation contact) {
    //TODO
    //}
}
