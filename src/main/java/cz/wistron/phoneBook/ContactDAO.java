package cz.wistron.phoneBook;

import org.springframework.stereotype.Repository;
@Repository
public class ContactDAO {
private static contactList contactList = new contactList();
//This static block is executed when the class is loaded into the memory
static {
    contactList.getContactList().add(new Contact(1, "Plamen", "Ivanov", "1234567890"));
    contactList.getContactList().add(new Contact(2, "John", "Doe", "0987654321"));
    contactList.getContactList().add(new Contact(3, "Jane", "Doe", "1231231234"));
}
//Method to return the list of contacts
    public contactList getAllContacts() {
    return contactList;
}
//Method to add a contact to the list
    public void addContact(Contact contact) {
    contactList.getContactList().add(contact);
}
//Method to remove a contact from the list
    public void removeContact(Contact contact) {
    contactList.getContactList().remove(contact);
}
}
