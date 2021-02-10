package my.ebl.fire.service;

import my.ebl.fire.data.model.PersonDto;
import my.ebl.fire.data.model.PersonListModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService {
    List<PersonListModel> findAllListModels();

    PersonDto findDtoById(Long id);

    @Transactional
    Long save(PersonDto dto);

    @Transactional
    void delete(Long id);
}
