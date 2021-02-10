package my.ebl.fire.data.repository;

import my.ebl.fire.data.entity.Person;
import my.ebl.fire.data.model.PersonListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select " +
            " o.id as id, " +
            " o.firstName as firstName, " +
            " o.lastName as lastName, " +
            " o.age as age, " +
            " o.favouriteColour as favouriteColour " +
            " from Person o order by o.id asc")
    List<PersonListModel> findAllListModels();

    long countByFirstNameAndLastNameAndIdNot(String firstName, String lastName, Long id);

    long countById(Long id);
}
