package co.simplon.reserve.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.reserve.model.Room;

@Resource
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
