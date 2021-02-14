package my.ebl.fire.web.rest;

import my.ebl.fire.data.model.PersonDto;
import my.ebl.fire.data.model.PersonListModel;
import my.ebl.fire.exceptions.EntityIdExpectedException;
import my.ebl.fire.exceptions.EntityIdMismatchException;
import my.ebl.fire.exceptions.EntityIdNotExpectedException;
import my.ebl.fire.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonCtrl {
    Logger logger = LoggerFactory.getLogger(PersonCtrl.class);

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonListModel> findAll() {
        return personService.findAllListModels();
    }

    @GetMapping("/{id}")
    public PersonDto findOne(@PathVariable Long id) {
        return personService.findDtoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@Valid @RequestBody PersonDto person) {
        if (person.getId() != null) {
            throw new EntityIdNotExpectedException("Person");
        }
        return personService.save(person);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Long updatePerson(@Valid @RequestBody PersonDto person, @PathVariable("id") Long id) {
        if (person.getId() == null) {
            throw new EntityIdExpectedException("Person");
        }

        if (!person.getId().equals(id)) {
            throw new EntityIdMismatchException("Person", id, person.getId());
        }
        return personService.save(person);
    }
}
