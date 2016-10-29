package co.simplon.reserve.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.User;
import co.simplon.reserve.service.UserService;

@Controller
public class JspController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ModelAndView getUserList(ModelMap model) {
	List<User> userList = userService.getAll();
	model.addAttribute("userList", userList);
	return new ModelAndView("users", model);
    }

    @RequestMapping("/addUser")
    public ModelAndView addPerson(@RequestParam("name") String name, @RequestParam("surname") String surname,
	    @RequestParam("email") String email, @RequestParam("password") String password, ModelMap model) {
	User user = new User(name, surname, email, password);
	userService.add(user);
	return new ModelAndView("redirect:/user");
    }

    @RequestMapping("/deleteUser")
    public ModelAndView deletePerson(@RequestParam("id") Integer id, ModelMap model) {
	userService.delete(id);
	return new ModelAndView("redirect:/user");
    }

}
