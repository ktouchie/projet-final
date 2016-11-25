package co.simplon.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.reserve.model.Message;
import co.simplon.reserve.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> getAll() {
		return messageRepository.findAll();
	}
	
	public void delete(Integer id) {
		messageRepository.delete(id);
    }

    public Message add(Message message) {
    	return messageRepository.save(message);
    }

    public Message getById(Integer id) {
    	return messageRepository.findOne(id);
    }
}
