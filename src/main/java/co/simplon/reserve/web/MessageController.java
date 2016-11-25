package co.simplon.reserve.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Message;
import co.simplon.reserve.model.User;
import co.simplon.reserve.service.MessageService;
import co.simplon.reserve.service.UserService;

@Controller
@RequestMapping
public class MessageController {
	
	@Autowired
	public MessageService messageService;
	
	@Autowired
    private UserService userService;
	
//	@RequestMapping("/messages")
//	public ModelAndView getAll(ModelMap model){
//		List<Message> messageList = messageService.getAll();
//		model.addAttribute("messageList", messageList);
//		return new ModelAndView("messages", model);
//	}
	
	@RequestMapping("/contact")
	public ModelAndView getAllUsers(ModelMap model){
		List<User> userList = userService.getAll();
		model.addAttribute("userList", userList);
		return new ModelAndView("contact", model);
	}
	
	@RequestMapping("/addMessage")
	public ModelAndView addMessage(
			@RequestParam("userId") Integer userId,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			ModelMap model
			){
//		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = userService.getByEmail(currentEmail);
//		Date date = new Date();
		User user = userService.getById(userId);
		Message message = new Message(title, content, false, user);
		messageService.add(message);
		return new ModelAndView("redirect:/contact");
	}
	
}
