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

import co.simplon.reserve.model.Room;
import co.simplon.reserve.service.RoomService;

@RestController
@CrossOrigin
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Room> get() {
	return roomService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Room getById(@PathVariable int id) {
	return roomService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Room post(@RequestBody Room room) {
	return roomService.add(room);
    }

}
