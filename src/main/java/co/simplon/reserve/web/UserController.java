package co.simplon.reserve.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.reserve.model.User;
import co.simplon.reserve.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> get() {
	return userService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable int id) {
	return userService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User post(@RequestBody User user) {
	return userService.add(user);
    }

    @RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
    public List<User> findByName(@PathVariable String name) {
	return userService.findByName(name);
    }

}
