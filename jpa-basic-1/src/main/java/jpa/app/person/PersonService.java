package jpa.app.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
class Address {

    private String address;

    private String city;

    private int zip;

}


@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

}

@Profile("arch")
@Controller
@ResponseBody
@RequestMapping("/person")
@Slf4j
class PersonController {

    private final PersonRespository personRespository;

    PersonController(PersonRespository personRespository) {
        this.personRespository = personRespository;
    }

    @GetMapping("/")
    List<Person> findAll() {
        return personRespository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Person> findById(@PathVariable Long id) {
        log.info("calling person by Id");
        var foundPerson = personRespository.findById(id);
        log.info("return person ", foundPerson);
        return foundPerson;
    }

    @PostMapping("/new")
    Person add(@RequestBody Person person) {
        log.info("calling person add ", person);
        var newPerson = personRespository.save(person);
        log.info("end person add ", newPerson);
        return findById(newPerson.getId()).get();
    }

}

interface PersonRespository extends JpaRepository<Person, Long> {
}
