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

import co.simplon.reserve.model.Computer;
import co.simplon.reserve.service.ComputerService;

@RestController
@CrossOrigin
@RequestMapping("/api/computer")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Computer> get() {
	return computerService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Computer getById(@PathVariable int id) {
	return computerService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Computer post(@RequestBody Computer computer) {
	return computerService.add(computer);
    }

}
