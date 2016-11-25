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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.reserve.model.Message;
import co.simplon.reserve.model.Reply;
import co.simplon.reserve.model.User;
import co.simplon.reserve.service.MessageService;
import co.simplon.reserve.service.ReplyService;
import co.simplon.reserve.service.UserService;

@Controller
@RequestMapping
public class MessageController {
	
	@Autowired
	public MessageService messageService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/messages")
	public ModelAndView getAll(ModelMap model){
		List<Message> unopenedMessageList = messageService.getUnopenedMessages();
		model.addAttribute("unopenedMessageList", unopenedMessageList);
		List<Message> openedMessageList = messageService.getOpenedMessages();
		model.addAttribute("unopenedMessageList", unopenedMessageList);
		return new ModelAndView("messages", model);
	}
	
	@RequestMapping("/contact")
	public ModelAndView getAllUsers(ModelMap model){
		List<User> userList = userService.getAll();
		model.addAttribute("userList", userList);
		return new ModelAndView("contact", model);
	}
	
	@RequestMapping("/addMessage")
	public ModelAndView addMessage(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			ModelMap model
			){
		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getByEmail(currentEmail);

		Message message = new Message(user, title, content, new Date(), false);
		messageService.add(message);
		return new ModelAndView("redirect:/contact");
	}
	
	@RequestMapping("/addReply")
	public ModelAndView addReply(
			@RequestParam("messageId") Integer messageId,
			@RequestParam("content") String content,
			ModelMap model){
		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getByEmail(currentEmail);
		Message message = messageService.getById(messageId);
		
		Reply reply = new Reply(message, user, content, new Date(), false);
		replyService.add(reply);
		return new ModelAndView("redirect:/messages");
	}
	
	@RequestMapping("/message")
	private ModelAndView getMessage(ModelMap model){
		return new ModelAndView("message", model);
	}
	
//	@RequestMapping("/replies")
//	public ModelAndView getReplies(@RequestParam("message") Message message, ModelMap model){
//		List<Reply> replyList = replyService.getReplies(message.getId());
//		model.addAttribute("replyList", replyList);
//		return new ModelAndView("replies", model);
//	}
//	
//	@RequestMapping("/addReply")
//	public ModelAndView addReply(
//			@RequestParam("messageId") Integer messageId,
//			@RequestParam("userId") Integer userId,
//			@RequestParam("content") String content){
//		Message message = messageService.getById(messageId);
//		User user = userService.getById(userId);
//		Reply reply = new Reply(message, user, content, new Date(), false);
//		replyService.add(reply);
//		return new ModelAndView("redirect:/replies");
//	}
	
	@RequestMapping("/readMail")
	private ModelAndView readMail(
			@RequestParam("messageId") Integer messageId,
			ModelMap model,
			RedirectAttributes redir){
		Message messageRead = messageService.getById(messageId);
		List<Reply> replyList = replyService.getReplies(messageId);

		redir.addFlashAttribute("messageRead", messageRead);
		redir.addFlashAttribute("replyList", replyList);
		return new ModelAndView("redirect:/message");
	}
	
}
