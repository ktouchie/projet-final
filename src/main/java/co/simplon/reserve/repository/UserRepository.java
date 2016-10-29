package co.simplon.reserve.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.reserve.model.User;

@Resource
public interface UserRepository extends JpaRepository<User, Integer> {

}
