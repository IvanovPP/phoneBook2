package cz.wistron.phoneBook;
import java.util.ArrayList;
import java.util.List;
//Creating a class contactList to store the contacts
public class contactList {
    //Creating a list to store the contacts
    private List<Contact> contactList = new ArrayList<>();
    //Method to add a contact to the list
    public void addContact(Contact contact) {
        contactList.add(contact);
    }
    //Method to remove a contact from the list
    public void removeContact(Contact contact) {
        contactList.remove(contact);
    }
    //Method to return the list of contacts
    public List<Contact> getContactList() {
        if (contactList == null) {
            return new ArrayList<>();
        }
        return contactList;
    }
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
