package co.simplon.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.reserve.model.User;
import co.simplon.reserve.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getAll() {
	return userRepository.findAll();
    }

    public List<User> getAllEnabled() {
	return userRepository.getAllEnabled();
    }

    public void delete(Integer id) {
	userRepository.delete(id);
    }

    public User add(User user) {
	return userRepository.save(user);
    }

    public User getById(Integer id) {
	return userRepository.findOne(id);
    }

    public User getByEmail(String email) {
	List<User> userList = userRepository.findByEmail(email);
	return userList.get(0);
    }

}