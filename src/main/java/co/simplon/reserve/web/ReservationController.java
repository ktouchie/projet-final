package co.simplon.reserve.web;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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

    // Set up calendar for current month
    private final static Locale locale = Locale.ENGLISH;
    private Calendar calendar = Calendar.getInstance();
    private int currentMonth = calendar.get(Calendar.MONTH);
    private int currentYear = calendar.get(Calendar.YEAR);
    private int thisYear = calendar.get(Calendar.YEAR);
    private int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    private static DateFormatSymbols dfs = new DateFormatSymbols(locale);
    private static String[] months = dfs.getMonths();
    private Calendar resStartDate = Calendar.getInstance();
    private Calendar resEndDate = Calendar.getInstance();
    private Set<String> resClasses = new HashSet<String>();
    private String startStr = "start";
    private String dayStr = " day";
    private String searchRes;
    private String searchCompRes;
    private String searchRoomRes;
    // clear editId
    private Integer editId = 0;

    @RequestMapping("/reservations")
    public ModelAndView getAll(ModelMap model) {

	// for Admin to view all reservations
	List<Computer> computerList = computerService.getAllEnabled();
	model.addAttribute("computerList", computerList);
	List<User> userList = userService.getAllEnabled();
	model.addAttribute("userList", userList);
	List<Room> roomList = roomService.getAllEnabled();
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
	model.addAttribute("currentUserId", user.getId());
	List<Reservation> userReservationList = reservationService.userReservations(user.getId());
	model.addAttribute("userReservationList", userReservationList);
	boolean isReservationsPage = true;
	model.addAttribute("isReservationsPage", isReservationsPage);

	// allow editing for selected Reservation
	model.addAttribute("editId", editId);

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

	// Check for booking conflicts before adding Reservation
	if (computerIsAvailable && roomIsAvailable) {
	    Computer computer = computerService.getById(computerId);
	    Room room = roomService.getById(roomId);
	    User user = userService.getById(userId);
	    Reservation reservation = new Reservation(startTime, endTime, computer, room, user);
	    reservationService.add(reservation);
	} else if (!computerIsAvailable) {
	    redirectAttrs.addFlashAttribute("error",
		    "Reservation conflict: Computer is already reserved at this time.");
	} else if (!roomIsAvailable) {
	    redirectAttrs.addFlashAttribute("error", "Reservation conflict: Room is already reserved at this time.");
	}

	return new ModelAndView("redirect:/reservations");
    }

    @RequestMapping("/editReservation")
    public ModelAndView editReservation(@RequestParam("id") Integer id, ModelMap model) {
	editId = id;
	return new ModelAndView("redirect:/reservations", model);
    }

    @RequestMapping("/modifyReservation")
    public ModelAndView modifyReservation(@RequestParam("editReservationId") Integer editReservationId,
	    @RequestParam("editUserId") Integer editUserId,
	    @RequestParam(name = "editComputerId", defaultValue = "-1") Integer editComputerId,
	    @RequestParam(name = "editRoomId", defaultValue = "-1") Integer editRoomId,
	    @RequestParam("editStartTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH") Date editStartTime,
	    @RequestParam("editEndTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH") Date editEndTime, ModelMap model) {
	Reservation reservation = reservationService.getById(editReservationId);
	reservation.setUser(userService.getById(editUserId));
	reservation.setComputer(computerService.getById(editComputerId));
	reservation.setRoom(roomService.getById(editRoomId));
	reservation.setStartTime(editStartTime);
	reservation.setEndTime(editEndTime);
	reservationService.add(reservation);
	editId = 0;
	return new ModelAndView("redirect:/reservations", model);
    }

    @RequestMapping("/cancelEdit")
    public ModelAndView cancelEdit(@RequestParam("editId") Integer id, ModelMap model) {
	editId = id;
	return new ModelAndView("redirect:/reservations", model);
    }

    @RequestMapping("/deleteReservation")
    public ModelAndView deleteReservation(@RequestParam("id") Integer id, ModelMap model) {
	reservationService.delete(id);
	return new ModelAndView("redirect:/reservations");
    }

    @RequestMapping("/planning")
    public ModelAndView showCalendar(ModelMap model) {

	// Setup calendar
	Calendar selected = Calendar.getInstance();
	selected.set(Calendar.YEAR, currentYear);
	selected.set(Calendar.MONTH, currentMonth);
	maxDays = selected.getActualMaximum(Calendar.DAY_OF_MONTH);

	// Display all resources for search filter
	List<Computer> computerList = computerService.getAll();
	model.addAttribute("computerList", computerList);
	List<Room> roomList = roomService.getAll();
	model.addAttribute("roomList", roomList);

	// Show current calendar
	model.addAttribute("currentMonth", currentMonth);
	model.addAttribute("months", months);
	model.addAttribute("currentYear", currentYear);
	model.addAttribute("maxDays", maxDays);
	model.addAttribute("resClasses", resClasses);
	model.addAttribute("thisYear", thisYear);
	model.addAttribute("isPlanningPage", true);
	model.addAttribute("searchCompRes", searchCompRes);
	model.addAttribute("searchRoomRes", searchRoomRes);
	model.addAttribute("searchRes", searchRes);

	return new ModelAndView("/planning", model);
    }

    @RequestMapping("/updateCalendar")
    public ModelAndView updateCalendar(ModelMap model,
	    @RequestParam(name = "computerId", defaultValue = "-1") Integer computerId,
	    @RequestParam(name = "roomId", defaultValue = "-1") Integer roomId,
	    @RequestParam(name = "selectedMonth", defaultValue = "-1") Integer selectedMonth,
	    @RequestParam(name = "selectedYear", defaultValue = "-1") Integer selectedYear) {

	// Refresh calendar and "searching for" Strings
	resClasses.clear();
	searchRes = null;
	searchCompRes = null;
	searchRoomRes = null;
	List<Reservation> reservationList = reservationService.getAll();

	// Display search filters with resulting planning
	if (selectedMonth > -1) {
	    currentMonth = selectedMonth;
	}
	if (selectedYear > -1) {
	    currentYear = selectedYear;
	}
	if (computerId > -1 && roomId == -1) {
	    reservationList = reservationService.computerReservations(computerId);
	    searchCompRes = computerService.getById(computerId).getBrand().concat("-")
		    .concat(computerService.getById(computerId).getSerial());
	} else if (computerId == -1 && roomId > -1) {
	    reservationList = reservationService.roomReservations(roomId);
	    searchRoomRes = roomService.getById(roomId).getName();
	} else if (computerId > -1 && roomId > -1) {
	    searchCompRes = computerService.getById(computerId).getBrand().concat("-")
		    .concat(computerService.getById(computerId).getSerial());
	    searchRoomRes = roomService.getById(roomId).getName();
	    reservationList = reservationService.doubleReservations(computerId, roomId);
	} else if (computerId == -1 && roomId == -1) {
	    searchRes = ("All Reservations");
	}

	// Display reservations according to search filters
	for (Reservation r : reservationList) {
	    resStartDate.setTime(r.getStartTime());
	    resEndDate.setTime(r.getEndTime());

	    int resStartMonth = resStartDate.get(Calendar.MONTH);
	    int resEndMonth = resEndDate.get(Calendar.MONTH);
	    int resStartDay = resStartDate.get(Calendar.DAY_OF_MONTH);
	    int resEndDay = resEndDate.get(Calendar.DAY_OF_MONTH);
	    int resStartHour = resStartDate.get(Calendar.HOUR_OF_DAY);
	    int resEndHour = resEndDate.get(Calendar.HOUR_OF_DAY);

	    if (resStartMonth == currentMonth) {
		if (resStartMonth == resEndMonth) {
		    addReservationClass(resClasses, resStartDay, resEndDay, resStartHour, resEndHour, startStr, dayStr);
		} else if (resStartMonth < resEndMonth) {
		    addReservationClass(resClasses, resStartDay, maxDays, resStartHour, resEndHour, startStr, dayStr);
		}
	    } else if (resStartMonth < currentMonth && resEndMonth > currentMonth) {
		addReservationClass(resClasses, 1, maxDays, resStartHour, resEndHour, startStr, dayStr);
	    } else if (currentMonth == resEndMonth) {
		addReservationClass(resClasses, 1, resEndDay, resStartHour, resEndHour, startStr, dayStr);
	    }
	}

	return new ModelAndView("redirect:/planning");
    }

    // Creates a collection of classes corresponding to each <td> in the
    // calendar. Each <td> with a class in the list will be marked as "reserved"
    // according to the search filters.
    public Collection<String> addReservationClass(Collection<String> resClasses, int resStartDay, int resEndDay,
	    int resStartHour, int resEndHour, String startStr, String dayStr) {
	String res;
	if (resStartDay == resEndDay) {
	    for (int i = resStartHour; i < resEndHour; ++i) {
		res = startStr.concat(String.valueOf(i)).concat(dayStr).concat(String.valueOf(resStartDay));
		resClasses.add(res);
	    }
	} else if (resStartDay < resEndDay) {
	    for (int i = resStartDay; i <= resEndDay; ++i) {
		if (i == resStartDay) {
		    for (int j = resStartHour; j < 18; ++j) {
			res = startStr.concat(String.valueOf(j)).concat(dayStr).concat(String.valueOf(i));
			resClasses.add(res);
		    }
		}
		if (i > resStartDay && i < resEndDay) {
		    for (int j = 9; j < 18; ++j) {
			res = startStr.concat(String.valueOf(j)).concat(dayStr).concat(String.valueOf(i));
			resClasses.add(res);
		    }
		}
		if (i == resEndDay) {
		    for (int j = 9; j < resEndHour; ++j) {
			res = startStr.concat(String.valueOf(j)).concat(dayStr).concat(String.valueOf(i));
			resClasses.add(res);
		    }
		}
	    }
	}

	return resClasses;
    }

    // GET BY ID

}
