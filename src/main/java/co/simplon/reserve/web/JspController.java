package co.simplon.reserve.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Computer;
import co.simplon.reserve.model.Room;
import co.simplon.reserve.model.User;
import co.simplon.reserve.service.ComputerService;
import co.simplon.reserve.service.RoomService;
import co.simplon.reserve.service.UserService;

@Controller
public class JspController {

    @Autowired
    private UserService userService;

    @Autowired
    private ComputerService computerService;

    @Autowired
    private RoomService roomService;

    @RequestMapping("/main")
    public ModelAndView getAllLists(ModelMap model) {
	List<User> userList = userService.getAll();
	model.addAttribute("userList", userList);
	List<Computer> computerList = computerService.getAll();
	model.addAttribute("computerList", computerList);
	List<Room> roomList = roomService.getAll();
	model.addAttribute("roomList", roomList);
	return new ModelAndView("main", model);
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
	    @RequestParam("email") String email, @RequestParam("password") String password, ModelMap model) {
	User user = new User(name, surname, email, password);
	userService.add(user);
	return new ModelAndView("redirect:/main");
    }

    @RequestMapping("/deleteUser")
    public ModelAndView deletePerson(@RequestParam("id") Integer id, ModelMap model) {
	userService.delete(id);
	return new ModelAndView("redirect:/main");
    }

    @RequestMapping("/addComputer")
    public ModelAndView addComputer(@RequestParam("brand") String brand, @RequestParam("serial") String serial,
	    ModelMap model) {
	Computer computer = new Computer(brand, serial);
	computerService.add(computer);
	return new ModelAndView("redirect:/main");
    }

    @RequestMapping("/deleteComputer")
    public ModelAndView deleteComputer(@RequestParam("id") Integer id, ModelMap model) {
	computerService.delete(id);
	return new ModelAndView("redirect:/main");
    }

    @RequestMapping("/addRoom")
    public ModelAndView addRoom(@RequestParam("name") String name, @RequestParam("capacity") Integer capacity,
	    ModelMap model) {
	Room room = new Room(name, capacity);
	roomService.add(room);
	return new ModelAndView("redirect:/main");
    }

    @RequestMapping("/deleteRoom")
    public ModelAndView deleteRoom(@RequestParam("id") Integer id, ModelMap model) {
	roomService.delete(id);
	return new ModelAndView("redirect:/main");
    }

}
