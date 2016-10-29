package co.simplon.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import co.simplon.reserve.model.User;
import co.simplon.reserve.repository.UserRepository;

@Repository
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getAll() {
	return userRepository.findAll();
    }

    public void delete(Integer id) {
	userRepository.delete(id);
    }

    public User add(User user) {
	return userRepository.save(user);
    }

    public User getById(int id) {
	return userRepository.findOne(id);
    }

    public List<User> findByName(String name) {
	User user = new User(name, null, null);
	Example<User> example = Example.of(user);
	return userRepository.findAll(example);

    }

}