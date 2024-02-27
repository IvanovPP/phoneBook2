package cz.wistron.phoneBook.service;

import cz.wistron.phoneBook.dto.ContactInformation;

import java.util.List;

public interface PhoneBookService {

    Integer addContact(ContactInformation contactInformation);

    List<ContactInformation> getContacts();

    void removeContact(Integer contactId);
}
