package si.damjah.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import si.damjah.spring.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return jdbcTemplate.update("INSERT INTO person (id, name) VALUES (?, ?)", id, person.getName());
    }

    @Override
    public List<Person> selectAllPeople() {
        return jdbcTemplate.query("SELECT id, name FROM person", (resultSet, i) ->
                new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name")));
    }

    @Override
    public int deletePersonById(UUID id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return jdbcTemplate.update("UPDATE person SET id = ?, name = ? WHERE id = ?", id, person.getName(), id);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        Person person = jdbcTemplate.queryForObject("SELECT id, name FROM person WHERE id = ?", new Object[]{id}, (resultSet, i) ->
                new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name")));
        return Optional.ofNullable(person);
    }
}
