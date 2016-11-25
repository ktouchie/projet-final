package co.simplon.reserve.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.reserve.model.Message;

@Resource
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
