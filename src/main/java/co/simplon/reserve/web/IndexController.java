package co.simplon.reserve.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Reply;
import co.simplon.reserve.model.User;
import co.simplon.reserve.model.User.Role;
import co.simplon.reserve.service.ReplyService;
import co.simplon.reserve.service.UserService;

@Controller
@RequestMapping
public class IndexController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private ReplyService replyService;
	
    @RequestMapping("/")
    public ModelAndView index(ModelMap model) {
    	
    	// check for unopened messages
    	String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    	User user = userService.getByEmail(currentEmail);
    	List<Reply> unopenedMails = new ArrayList<Reply>();
    	
    	if (user.getRole() == Role.ADMIN) {
    		unopenedMails = replyService.getAdminUnopenedMails();
		}
    	else {
    		unopenedMails = replyService.getUserUnopenedMails(user.getId());
    	}
    	
    	// alert on if unread replies
    	model.addAttribute("alertMailOn", isAlertMailOn(unopenedMails));

    	return new ModelAndView("index", model);
    }
    
    public boolean isAlertMailOn (List<Reply> unopenedMails){
    	if (unopenedMails.size() != 0){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
 
}