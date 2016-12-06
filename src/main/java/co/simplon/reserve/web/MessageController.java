package co.simplon.reserve.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import co.simplon.reserve.model.User.Role;
import co.simplon.reserve.service.EmailService;
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
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/adminInbox")
	public ModelAndView getAdminInbox(ModelMap model){
		List<Message> unopenedMessageList = messageService.getUnopenedMessageList();
		model.addAttribute("unopenedMessageList", unopenedMessageList);
		List<Message> openedMessageList = messageService.getOpenedMessageList();
		model.addAttribute("openedMessageList", openedMessageList);
		
		return new ModelAndView("adminInbox", model);
	}
	
	@RequestMapping("/adminOutbox")
	public ModelAndView getAdminOutbox(ModelMap model){
		List<Message> adminMessageRepliedList = messageService.getAdminMessageRepliedList();
		model.addAttribute("adminMessageRepliedList", adminMessageRepliedList);
		return new ModelAndView("adminOutbox", model);
	}
	
	@RequestMapping("/userInbox")
	public ModelAndView getUserInbox(ModelMap model){
		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getByEmail(currentEmail);
		
		List<Message> unopenedRepliedMessageList = messageService.getUnopenedRepliedMessageList(user.getId());
		model.addAttribute("unopenedRepliedMessageList", unopenedRepliedMessageList);
		List<Message> openedRepliedMessageList = messageService.getOpenedRepliedMessageList(user.getId());
		model.addAttribute("openedRepliedMessageList", openedRepliedMessageList);
		
		return new ModelAndView("userInbox", model);
	}
	
	@RequestMapping("/userOutbox")
	public ModelAndView getUserOutbox(ModelMap model){
		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getByEmail(currentEmail);
		
		List<Message> messageFromUserList = messageService.getMessageFromUserList(user.getId());
		model.addAttribute("messageFromUserList", messageFromUserList);
		
		return new ModelAndView("userOutbox", model);
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

		Message message = new Message(user, title, content, new Date());
		messageService.add(message);
		return new ModelAndView("redirect:/confirmcontact");
	}
	
	@RequestMapping("/confirmcontact")
	public ModelAndView getContactConfirmation(ModelMap model){
		return new ModelAndView("confirmcontact", model);
	}
	
	@RequestMapping("/message")
	private ModelAndView getMessage(ModelMap model){
		return new ModelAndView("message", model);
	}
	
	@RequestMapping("/readMail")
	private ModelAndView readMail(
			@RequestParam("messageId") Integer messageId,
			@RequestParam("mailBoxSource") String mailBoxSource,
			ModelMap model,
			RedirectAttributes redir){
		Message messageRead = messageService.getById(messageId);
		List<Reply> replyList = replyService.getReplies(messageId);
		
		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getByEmail(currentEmail);
		
		// admin process
		// if (user.getId() != messageRead.getUserId()){
		if (user.getRole() == Role.ADMIN){
			messageRead.setOpened(true);
			messageService.add(messageRead);
			for (Reply reply : replyList) {
				// if (messageRead.getUserId() != reply.getUserId()){
				if (reply.getUser().getRole() == Role.USER){
					// condition for not saving for peanuts : less DB saves vs. more checks
					// speed up or down?
					if (!reply.isOpened()){
						reply.setOpened(true);
						replyService.add(reply);
					}
				}
			}
		}
		// user process
		else {
			for (Reply reply : replyList) {
				// if (user.getId() != reply.getUserId()){
				if (reply.getUser().getRole() == Role.ADMIN){
					// condition : same as above
					if (!reply.isOpened()){
						reply.setOpened(true);
						replyService.add(reply);
					}
				}
			}
		}

		redir.addFlashAttribute("messageRead", messageRead);
		redir.addFlashAttribute("replyList", replyList);
		redir.addFlashAttribute("mailBoxSource", mailBoxSource);
		return new ModelAndView("redirect:/message");
	}
	
	@RequestMapping("/addReply")
	public ModelAndView addReply(
			@RequestParam("messageId") Integer messageId,
			@RequestParam("content") String content,
			@RequestParam("mailBoxSource") String mailBoxSource,
			ModelMap model){
		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getByEmail(currentEmail);
		Message message = messageService.getById(messageId);
		
		Reply reply = new Reply(message, user, content, new Date());
		replyService.add(reply);
		
		// status refresh and notification according to Role
		// reply from user
		if (user.getRole() == Role.USER){
			// read status actualization
			// condition in order not to save for peanuts
			if (message.isOpened()){
				message.setOpened(false);
				messageService.add(message);
			}
			// del status actualization
			// check in order not to save for peanuts
			if (message.isDelByAdmin()){
				message.setDelByAdmin(false);
				messageService.add(message);
			}
		}
		
		// reply from admin
		else if (user.getRole() == Role.ADMIN){
			// no read status actualization (read status only changes if user replies)
			// del status actualization
			// check in order not to save for peanuts
			if (message.isDelByUser()){
				message.setDelByUser(false);
				messageService.add(message);
			}
			
			// Mail notification
			sendMail(message, reply);
		}
		
		
		System.out.println(mailBoxSource);
		if (mailBoxSource.equals("adminInbox")) return new ModelAndView("redirect:/adminInbox");
		else if (mailBoxSource.equals("adminOutbox")) return new ModelAndView("redirect:/adminOutbox");
		else if (mailBoxSource.equals("userInbox")) return new ModelAndView("redirect:/userInbox");
		else return new ModelAndView("redirect:/userOutbox");
	}
	
	// Mail notification
	public void sendMail(Message message, Reply reply) {
		String toAddr = message.getUser().getEmail();
		String fromAddr = "simplon.reservation.assistance@gmail.com";
		
		// email subject
		String subject = "New reply from Simplon Reservation Services";
		
		// email body
		String body ="You've received a new reply to your Request : " + message.getTitle() + ".\n"
					+ reply.getContent();
		emailService.readyToSendEmail(toAddr, fromAddr, subject, body);
	}
	
	@RequestMapping("/disableThread")
	private ModelAndView disableThread(
			@RequestParam("messageId") Integer messageId,
			@RequestParam("mailBoxSource") String mailBoxSource,
			ModelMap model){
		Message messageToDisable = messageService.getById(messageId);
		
		String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getByEmail(currentEmail);
		
		// disabled by admin
		if (user.getRole() == Role.ADMIN){
			messageToDisable.setDelByAdmin(true);
		}
		// disabled by user
		else {
			messageToDisable.setDelByUser(true);
		}
		messageService.add(messageToDisable);
		
		System.out.println(mailBoxSource);
		if (mailBoxSource.equals("adminInbox")) return new ModelAndView("redirect:/adminInbox");
		else if (mailBoxSource.equals("adminOutbox")) return new ModelAndView("redirect:/adminOutbox");
		else if (mailBoxSource.equals("userInbox")) return new ModelAndView("redirect:/userInbox");
		else return new ModelAndView("redirect:/userOutbox");
	}
}
