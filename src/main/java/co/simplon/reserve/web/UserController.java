package co.simplon.reserve.web;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	boolean isUsersPage = true;
	model.addAttribute("isUsersPage", isUsersPage);
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
	boolean isPasswordPage = true;
	model.addAttribute("isPasswordPage", isPasswordPage);
	return new ModelAndView("password", model);
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam("currentPasswordInput") String currentPasswordInput,
	    @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
	    RedirectAttributes redirectAttrs) {
	// get current User
	String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
	User user = userService.getByEmail(currentEmail);
	String currentPassword = (SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
	if (Objects.equals(currentPassword, currentPasswordInput) && Objects.equals(newPassword, confirmPassword)) {
	    user.setPassword(newPassword);
	    userService.add(user);
	    redirectAttrs.addFlashAttribute("success", "Success! Your password has been changed.");
	    return new ModelAndView("redirect:/password");
	} else {
	    redirectAttrs.addFlashAttribute("error", "Error Password Confirmation: Please re-enter your password.");
	    return new ModelAndView("redirect:/password");
	}
    }

    // @RequestMapping("/userById")
    // public ModelAndView getById(@RequestParam("id") Integer id, ModelMap
    // model) {
    // User user = userService.getById(id);
    // model.addAttribute("user", user);
    // }

}
