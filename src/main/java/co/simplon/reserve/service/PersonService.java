package co.simplon.reserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.simplon.reserve.repository.PersonRepository;

@Repository
public class PersonService {

    @Autowired
    public PersonRepository personRepository;

}