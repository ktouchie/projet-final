package co.simplon.reserve.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.reserve.model.Computer;
import co.simplon.reserve.model.Reservation;
import co.simplon.reserve.model.Room;
import co.simplon.reserve.model.User;
import co.simplon.reserve.service.ComputerService;
import co.simplon.reserve.service.ReservationService;
import co.simplon.reserve.service.RoomService;
import co.simplon.reserve.service.UserService;

@Controller
@RequestMapping
public class ReservationController {

    @Autowired
    private ComputerService computerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/reservations")
    public ModelAndView getAll(ModelMap model) {
	// for Admin to view all reservations
	List<Computer> computerList = computerService.getAll();
	model.addAttribute("computerList", computerList);
	List<User> userList = userService.getAll();
	model.addAttribute("userList", userList);
	List<Room> roomList = roomService.getAll();
	model.addAttribute("roomList", roomList);
	List<Reservation> reservationList = reservationService.getAll();
	model.addAttribute("reservationList", reservationList);
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
	String today = sdf.format(date);
	model.addAttribute("today", today);

	// for User to view own reservations
	String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
	User user = userService.getByEmail(currentEmail);
	List<Reservation> userReservationList = reservationService.userReservations(user.getId());
	model.addAttribute("userReservationList", userReservationList);
	return new ModelAndView("reservations", model);
    }

    @RequestMapping("/addReservation")
    public ModelAndView addReservation(
	    @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH") Date startTime,
	    @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH") Date endTime,
	    @RequestParam(name = "computerId", defaultValue = "-1") Integer computerId,
	    @RequestParam(name = "roomId", defaultValue = "-1") Integer roomId, @RequestParam("userId") Integer userId,
	    RedirectAttributes redirectAttrs) {
	boolean computerIsAvailable = reservationService.computerAvailable(computerId, startTime, endTime);
	boolean roomIsAvailable = reservationService.roomAvailable(roomId, startTime, endTime);

	if (computerIsAvailable && roomIsAvailable) {
	    Computer computer = computerService.getById(computerId);
	    Room room = roomService.getById(roomId);
	    User user = userService.getById(userId);
	    Reservation reservation = new Reservation(startTime, endTime, computer, room, user);
	    reservationService.add(reservation);
	} else if (!computerIsAvailable) {
	    redirectAttrs.addFlashAttribute("error",
		    // Add list of conflicting times or suggest available
		    // computers?
		    "Reservation conflict: Computer is already reserved at this time.");
	} else if (!roomIsAvailable) {
	    redirectAttrs.addFlashAttribute("error",
		    // Add list of conflicting times or suggest available
		    // computers?
		    "Reservation conflict: Room is already reserved at this time.");
	}

	return new ModelAndView("redirect:/reservations");
    }

    @RequestMapping("/deleteReservation")
    public ModelAndView deleteReservation(@RequestParam("id") Integer id, ModelMap model) {
	reservationService.delete(id);
	return new ModelAndView("redirect:/reservations");
    }

    // GET BY ID

}
