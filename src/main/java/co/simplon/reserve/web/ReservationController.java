package co.simplon.reserve.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Reservation;
import co.simplon.reserve.service.ReservationService;

@Controller
@RequestMapping
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/reservations")
    public ModelAndView getAll(ModelMap model) {
	List<Reservation> reservationList = reservationService.getAll();
	model.addAttribute("reservationList", reservationList);
	return new ModelAndView("reservations", model);
    }

    @RequestMapping("/addReservation")
    public ModelAndView addReservation(@RequestParam("startTime") LocalDateTime startTime,
	    @RequestParam("endTime") LocalDateTime endTime, @RequestParam("idComputer") Integer idComputer,
	    @RequestParam("idRoom") Integer idRoom, @RequestParam("idUser") Integer idUser, ModelMap model) {
	Reservation reservation = new Reservation(startTime, endTime, idComputer, idRoom, idUser);
	reservationService.add(reservation);
	return new ModelAndView("redirect:/reservations");
    }

    @RequestMapping("/addReservation")
    public ModelAndView addReservation(@RequestParam("startTime") LocalDateTime startTime,
	    @RequestParam("endTime") LocalDateTime endTime, @RequestParam("idRoom") Integer idRoom,
	    @RequestParam("idUser") Integer idUser, ModelMap model) {
	Reservation reservation = new Reservation(startTime, endTime, idRoom, idUser);
	reservationService.add(reservation);
	return new ModelAndView("redirect:/reservations");
    }

    @RequestMapping("/addReservation")
    public ModelAndView addReservation(@RequestParam("idComputer") Integer idComputer,
	    @RequestParam("idUser") Integer idUser, @RequestParam("startTime") LocalDateTime startTime,
	    @RequestParam("endTime") LocalDateTime endTime, ModelMap model) {
	Reservation reservation = new Reservation(idComputer, idUser, startTime, endTime);
	reservationService.add(reservation);
	return new ModelAndView("redirect:/reservations");
    }

    @RequestMapping("/deleteReservation")
    public ModelAndView deleteReservation(@RequestParam("id") Integer id, ModelMap model) {
	reservationService.delete(id);
	return new ModelAndView("redirect:/reservations");
    }

    // GET BY ID

}
