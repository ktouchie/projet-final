package co.simplon.reserve.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Room;
import co.simplon.reserve.service.RoomService;

@Controller
@RequestMapping
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/rooms")
    public ModelAndView getAll(ModelMap model) {
	List<Room> roomList = roomService.getAll();
	model.addAttribute("roomList", roomList);
	model.addAttribute("isRoomsPage", true);
	return new ModelAndView("rooms", model);
    }

    @RequestMapping("/addRoom")
    public ModelAndView addRoom(@RequestParam("name") String name, @RequestParam("capacity") Integer capacity,
	    ModelMap model) {
	Room room = new Room(name, capacity, true);
	roomService.add(room);
	return new ModelAndView("redirect:/rooms");
    }

    @RequestMapping("/updateRoomStatus")
    public ModelAndView updateRoomStatus(@RequestParam("enabled") boolean enabled, @RequestParam("id") Integer id,
	    ModelMap model) {
	Room room = roomService.getById(id);
	room.updateRoomStatus(enabled);
	roomService.add(room);
	return new ModelAndView("redirect:/rooms");
    }

    @RequestMapping("/deleteRoom")
    public ModelAndView deleteRoom(@RequestParam("id") Integer id, ModelMap model) {
	roomService.delete(id);
	return new ModelAndView("redirect:/rooms");
    }

    // GET BY ID

}
