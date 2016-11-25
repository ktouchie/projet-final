package co.simplon.reserve.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.Reply;

@Resource
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
	// return list of replies according to message
    @Query("select r from Reply r where r.message.id = ?1")
    public List<Reply> getReplies(Integer messageId);
    
    // return list of unread replies from users
    @Query("select r from Reply r where r.opened = 0 and r.message.id = ?1 and r.user.id = (select user.id from Message m where m.id = ?1)")
    public List<Reply> getUnreadRepliesFromUsers(Integer messageId);
	
    // return list of unread replies from admins
    @Query("select r from Reply r where r.opened = 0 and not r.user.id = ?1 and r.message.id in (select id from Message m where m.user.id = ?1)")
    public List<Reply> getUnreadRepliesFromAdmins(Integer userId);
    
}
