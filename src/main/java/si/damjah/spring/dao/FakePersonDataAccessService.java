package si.damjah.spring.dao;

import org.springframework.stereotype.Repository;
import si.damjah.spring.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Fake")
public class FakePersonDataAccessService implements PersonDao {

    private static final List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}
