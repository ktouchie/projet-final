package co.simplon.reserve.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.reserve.model.Computer;

@Resource
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

}
