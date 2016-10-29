package co.simplon.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import co.simplon.reserve.model.Person;
import co.simplon.reserve.repository.PersonRepository;

@Repository
public class PersonService {

    @Autowired
    public PersonRepository personRepository;

    public List<Person> getAll() {
	return personRepository.findAll();
    }

    public void delete(Integer id) {
	personRepository.delete(id);
    }

    public Person add(Person person) {
	return personRepository.save(person);
    }

    public Person getById(int id) {
	return personRepository.findOne(id);
    }

    public List<Person> findByName(String name) {
	Person person = new Person(name, null);
	Example<Person> example = Example.of(person);
	return personRepository.findAll(example);

    }

}