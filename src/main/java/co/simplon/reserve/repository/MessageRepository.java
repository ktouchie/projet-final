package co.simplon.reserve.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.Message;

@Resource
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	// Requests for admins mailboxes
	// Inbox
	@Query("select m from Message m where m.opened = 0 and m.delByAdmin = 0 order by m.creationDate desc")
	public List<Message> getUnopenedMessageList();
	
	@Query("select m from Message m where m.opened = 1 and m.delByAdmin = 0 order by m.creationDate desc")
	public List<Message> getOpenedMessageList();
	
	// Outbox
	@Query("select m from Message m where m.delByAdmin = 0 and m.id in "
			+ "(select r.message.id from Reply r where m.user.id <> r.user.id) "
			+ "order by m.creationDate desc")
	public List<Message> getAdminMessageRepliedList();
	
	
	// Requests for users mailboxes
	// Inbox
	@Query("select m from Message m where m.delByUser = 0 and m.user.id = ?1 and m.id in "
			+ "(select r.message.id from Reply r where m.user.id <> r.user.id and r.opened = 0) "
			+ "order by m.creationDate desc")
	public List<Message> getUnopenedRepliedMessageList(Integer userId);
	
	@Query("select m from Message m where m.delByUser = 0 and m.user.id = ?1 and m.id in "
			+ "(select r.message.id from Reply r where m.user.id <> r.user.id and r.opened = 1 and r.replyDate = "
			+ "(select max(r.replyDate) from r where m.user.id <> r.user.id))")
	public List<Message> getOpenedRepliedMessageList(Integer userId);
	
	// Outbox
	@Query("select m from Message m where m.delByUser = 0 and m.user.id = ?1")
	public List<Message> getMessageFromUserList(Integer userId);
	
}
