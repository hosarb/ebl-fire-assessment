package my.ebl.fire.service.impl;

import my.ebl.fire.data.entity.Person;
import my.ebl.fire.data.model.PersonDto;
import my.ebl.fire.data.model.PersonListModel;
import my.ebl.fire.data.repository.PersonRepository;
import my.ebl.fire.exceptions.DuplicatedPersonException;
import my.ebl.fire.exceptions.EntityNotFoundByIdException;
import my.ebl.fire.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("personService")
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonListModel> findAllListModels() {
        return personRepository.findAllListModels();
    }

    @Override
    public PersonDto findDtoById(Long id) {
        Optional<Person> byId = personRepository.findById(id);
        if (!byId.isPresent()) {
            throw new EntityNotFoundByIdException("Person", id);
        }
        Person entity = byId.get();
        PersonDto dto = new PersonDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public Long save(PersonDto dto) {
        Person entity;
        if (dto.getId() != null) {
            Optional<Person> byId = personRepository.findById(dto.getId());
            if (!byId.isPresent()) {
                throw new EntityNotFoundByIdException("Person", dto.getId());
            }
            entity = byId.get();
        } else {
            entity = new Person();
        }

        long count = personRepository.countByFirstNameAndLastNameAndIdNot(dto.getFirstName(), dto.getLastName(), (dto.getId() != null) ? dto.getId() : -1L);
        if(count > 0){
            throw new DuplicatedPersonException();
        }
        BeanUtils.copyProperties(dto, entity);
        entity = personRepository.save(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        long count = personRepository.countById(id);
        if (count == 0L) {
            throw new EntityNotFoundByIdException("Person", id);
        }

        personRepository.deleteById(id);
    }
}
