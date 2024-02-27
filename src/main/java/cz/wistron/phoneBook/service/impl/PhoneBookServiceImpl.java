package cz.wistron.phoneBook.service.impl;

import cz.wistron.phoneBook.dto.ContactInformation;
import cz.wistron.phoneBook.entity.Contact;
import cz.wistron.phoneBook.repository.ContactRepository;
import cz.wistron.phoneBook.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Integer addContact(ContactInformation contactInformation) {
        return contactRepository.save(toEntity(contactInformation)).getId();
    }

    @Override
    public List<ContactInformation> getContacts() {
        return contactRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeContact(Integer contactId) {
        contactRepository.deleteById(contactId);
    }

    //mapping methods,  can use frameworks like Mapstruct instead
    private cz.wistron.phoneBook.entity.Contact toEntity(ContactInformation dto) {
        return new cz.wistron.phoneBook.entity.Contact(
                dto.firstName(),
                dto.lastName(),
                dto.phoneNumber()
        );
    }

    private ContactInformation toDto(Contact entity) {
        return new ContactInformation(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber()
        );
    }
}
