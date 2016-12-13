package co.simplon.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.reserve.model.Reply;
import co.simplon.reserve.repository.ReplyRepository;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> getReplies(Integer messageId) {
	return replyRepository.getReplies(messageId);
    }

    public void delete(Integer id) {
	replyRepository.delete(id);
    }

    public Reply add(Reply reply) {
	return replyRepository.save(reply);
    }

    public Reply getById(Integer id) {
	return replyRepository.findOne(id);
    }

}
