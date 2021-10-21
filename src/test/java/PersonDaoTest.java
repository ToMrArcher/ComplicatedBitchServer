import org.junit.jupiter.api.Test;
import person.Person;
import person.PersonDao;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonDaoTest {
    private PersonDao dao = new PersonDao(TestData.testDataSource());

    @Test
    void shouldRetrieveSavedPerson() throws SQLException {
        Person person = examplePerson();
        dao.save(person);
        assertThat(dao.retrieve(person.getId()))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(person);

    }

    private Person examplePerson() {
        Person person = new Person();
        person.setFirstName(TestData.pickOne("Herman", "Syvert", "Lars", "Jonas"));
        person.setLastName(TestData.pickOne("Loen", "Eidjord", "Olsen", "Larsen"));
        return person;
    }
}
