package cz.wistron.phoneBook;

import cz.wistron.phoneBook.dto.ContactInformation;
import cz.wistron.phoneBook.entity.Contact;
import cz.wistron.phoneBook.repository.ContactRepository;
import cz.wistron.phoneBook.service.PhoneBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PhoneBookApplicationTestsMockito {

    @Autowired
    private PhoneBookService phoneBookService;

    @MockBean
    //spring does not create the bean, only an empty shell is created instead, which is then is "wired"" in PhoneBookService
    private ContactRepository contactRepositoryMocked;

    @Test
    public void testMockAdd() {
        //purpose of UNIT  is to test only a specific unit of code (for example - not the database interaction, but only logic implement in Service class)
        //therefore mocking the repository helps to achieve this
        Contact mockedEntity = new Contact("a", "b", "c");
        mockedEntity.setId(7);
        //telling the program what the mocked "shell" actually returns when a specific method is called
        Mockito.when(contactRepositoryMocked.save(Mockito.any())).thenReturn(mockedEntity);

        Integer result = phoneBookService.addContact(
                new ContactInformation(
                        null,
                        "Michal",
                        "Lunak",
                        "001241521305")
        );
        Assertions.assertEquals(7, result);
    }
}
