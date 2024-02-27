package cz.wistron.phoneBook;

import cz.wistron.phoneBook.dto.ContactInformation;
import cz.wistron.phoneBook.service.PhoneBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhoneBookApplicationTests {

    @Autowired
    private PhoneBookService phoneBookService;

    @Test
    void contextLoads() {
    }

    @Test
    void testService() {
        phoneBookService.addContact(new ContactInformation(null, "name", "lastName", "00420123456789"));
    }
}
