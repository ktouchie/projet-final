package co.simplon.reserve.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Message;
import co.simplon.reserve.model.User;
import co.simplon.reserve.model.User.Role;
import co.simplon.reserve.service.MessageService;
import co.simplon.reserve.service.UserService;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public ModelAndView index(ModelMap model) {

	// check for unopened messages
	String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

	if (!currentEmail.equals("anonymousUser")) {
	    User user = userService.getByEmail(currentEmail);
	    List<Message> unopenedMails = new ArrayList<Message>();

	    if (user.getRole() == Role.ADMIN) {
		unopenedMails = messageService.getUnopenedMessageList();
	    } else {
		unopenedMails = messageService.getUnopenedRepliedMessageList(user.getId());
	    }
	    // alert on if unread replies
	    model.addAttribute("alertMailOn", !unopenedMails.isEmpty());
	}

	return new ModelAndView("index", model);
    }

}