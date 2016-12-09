package co.simplon.reserve.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.Computer;

@Resource
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

    // return list of enabled Computers
    @Query("select c from Computer c where c.enabled = true")
    public List<Computer> getAllEnabled();

}
