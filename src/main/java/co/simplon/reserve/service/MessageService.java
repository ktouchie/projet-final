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
    
    public List<Message> getUnopenedMessageList(){
    	return messageRepository.getUnopenedMessageList();
    }
    
    public List<Message> getOpenedMessageList(){
    	return messageRepository.getOpenedMessageList();
    }
    
    public List<Message> getAdminMessageRepliedList(){
    	return messageRepository.getAdminMessageRepliedList();
    }

    public List<Message> getUnopenedRepliedMessageList(Integer userId){
    	return messageRepository.getUnopenedRepliedMessageList(userId);
    }
    
    public List<Message> getOpenedRepliedMessageList(Integer userId){
    	return messageRepository.getOpenedRepliedMessageList(userId);
    }

    public List<Message> getMessageFromUserList(Integer userId){
    	return messageRepository.getMessageFromUserList(userId);
    }
}
