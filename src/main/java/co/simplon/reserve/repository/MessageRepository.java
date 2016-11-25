package co.simplon.reserve.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.Message;

@Resource
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	@Query("select m from Message m where m.opened = 0 order by m.creationDate desc")
	public List<Message> getUnopenedMessages();
	
	@Query("select m from Message m where m.opened = 1 order by m.creationDate desc")
	public List<Message> getOpenedMessages();
}
