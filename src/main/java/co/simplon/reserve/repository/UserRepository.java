package co.simplon.reserve.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.User;

@Resource
public interface UserRepository extends JpaRepository<User, Integer> {

    // return list of users with particular email
    @Query("select u from User where u.email = ?1")
    public User findByEmail(String email);

}
