package jpa.app.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
class UnitTestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PersonRespository personRespository;


    @Test
    void simpleTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/unit-test/")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }


    @Test
    @Transactional
    void checkDataForQuery() {
        String query = " select p from Person p where p.id = :value";
        var result = testEntityManager
                .getEntityManager()
                .createQuery(query)
                .setParameter("value", Integer.valueOf(1))
                .getResultList();

        var person = (Person) result.getFirst();
        assertEquals(1, person.getId());

    }

}