package co.simplon.reserve.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.User;
import co.simplon.reserve.model.User.Role;
import co.simplon.reserve.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login")
    public ModelAndView login(ModelMap model) {
	// If database is empty, create admin profile
	try {
	    List<User> userList = userService.getAll();
	    model.addAttribute("userList", userList);
	} catch (Exception e) {
	    User user = new User("Admin", "Admin", "admin", "admin", Role.ADMIN, true);
	    userService.add(user);
	}
	model.addAttribute("isLoginPage", true);
	return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null) {
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	return new ModelAndView("redirect:/");
    }

}
