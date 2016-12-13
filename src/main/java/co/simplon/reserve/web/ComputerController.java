package co.simplon.reserve.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.reserve.model.Computer;
import co.simplon.reserve.service.ComputerService;

@Controller
@RequestMapping
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @RequestMapping("/computers")
    public ModelAndView getAll(ModelMap model) {
	List<Computer> computerList = computerService.getAll();
	model.addAttribute("computerList", computerList);
	model.addAttribute("isComputersPage", true);
	return new ModelAndView("computers", model);
    }

    @RequestMapping("/addComputer")
    public ModelAndView addComputer(@RequestParam("brand") String brand, @RequestParam("serial") String serial,
	    ModelMap model) {
	Computer computer = new Computer(brand, serial, true);
	computerService.add(computer);
	return new ModelAndView("redirect:/computers");
    }

    @RequestMapping("/updateComputerStatus")
    public ModelAndView updateComputerStatus(@RequestParam("enabled") boolean enabled, @RequestParam("id") Integer id,
	    ModelMap model) {
	Computer computer = computerService.getById(id);
	computer.updateComputerStatus(enabled);
	computerService.add(computer);
	return new ModelAndView("redirect:/computers");
    }

    @RequestMapping("/deleteComputer")
    public ModelAndView deleteComputer(@RequestParam("id") Integer id, ModelMap model) {
	computerService.delete(id);
	return new ModelAndView("redirect:/computers");
    }

    // GET BY ID

}
