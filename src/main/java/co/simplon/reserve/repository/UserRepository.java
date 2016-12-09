package co.simplon.reserve.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.User;

@Resource
public interface UserRepository extends JpaRepository<User, Integer> {

    // return list of Users with particular email
    @Query("select u from User u where u.email = ?1")
    public List<User> findByEmail(String email);

    // return list of enabled Users
    @Query("select u from User u where u.enabled = true")
    public List<User> getAllEnabled();

}
