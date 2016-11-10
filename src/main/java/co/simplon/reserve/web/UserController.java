package co.simplon.reserve.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.User;
import co.simplon.reserve.service.UserService;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public ModelAndView getAll(ModelMap model) {
	List<User> userList = userService.getAll();
	model.addAttribute("userList", userList);
	return new ModelAndView("users", model);
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
	    @RequestParam("email") String email, @RequestParam("password") String password, ModelMap model) {
	User user = new User(name, surname, email, password, User.Role.USER, true);
	userService.add(user);
	return new ModelAndView("redirect:/users");
    }

    @RequestMapping("/addAnyUser")
    public ModelAndView addAnyUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
	    @RequestParam("email") String email, @RequestParam("password") String password,
	    @RequestParam("role") User.Role role, ModelMap model) {
	User user = new User(name, surname, email, password, role, true);
	userService.add(user);
	return new ModelAndView("redirect:/users");
    }

    @RequestMapping("/updateUserStatus")
    public ModelAndView updateUserStatus(@RequestParam("enabled") boolean enabled, @RequestParam("id") Integer id,
	    ModelMap model) {
	User user = userService.getById(id);
	user.updateUserStatus(enabled);
	userService.add(user);
	return new ModelAndView("redirect:/users");
    }

    @RequestMapping("/password")
    public ModelAndView password(ModelMap model) {
	return new ModelAndView("password", model);
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam("currentPasswordInput") String currentPasswordInput,
	    @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
	    ModelMap model) {
	System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
	return new ModelAndView("redirect:/password", model);
    }

    // @RequestMapping("/userById")
    // public ModelAndView getById(@RequestParam("id") Integer id, ModelMap
    // model) {
    // User user = userService.getById(id);
    // model.addAttribute("user", user);
    // }

}
