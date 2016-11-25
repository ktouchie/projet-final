package co.simplon.reserve.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Message;
import co.simplon.reserve.model.Reply;
import co.simplon.reserve.model.User;
import co.simplon.reserve.service.MessageService;
import co.simplon.reserve.service.ReplyService;
import co.simplon.reserve.service.UserService;

@Controller
@RequestMapping
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private MessageService messageService;
	
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
	
}
