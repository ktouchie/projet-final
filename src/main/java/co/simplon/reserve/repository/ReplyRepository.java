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
	
}
