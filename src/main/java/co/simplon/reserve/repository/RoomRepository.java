package co.simplon.reserve.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.reserve.model.Room;

@Resource
public interface RoomRepository extends JpaRepository<Room, Integer> {

    // return list of enabled Rooms
    @Query("select r from Room r where r.enabled = true")
    public List<Room> getAllEnabled();

}
