package co.simplon.reserve.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.reserve.model.Person;

@Resource
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
