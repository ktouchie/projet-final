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

    /*
     * // Mail Alert
     * 
     * // list of replies from users yet unopened by admins
     * 
     * @Query("select r from Reply r where r.opened = 0 and r.message.user.id = r.user.id)"
     * ) public List<Reply> getAdminUnopenedMails();
     * 
     * // list of replies from admins yet unopened by user
     * 
     * @Query("select r from Reply r where r.opened = 0 and not r.user.id = ?1 and r.message.user.id in "
     * + "(select m.user.id from Message m where m.user.id = ?1)") public
     * List<Reply> getUserUnopenedMails(Integer userId);
     * 
     */
}
