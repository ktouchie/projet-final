package co.simplon.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.simplon.reserve.model.Room;
import co.simplon.reserve.repository.RoomRepository;

@Repository
public class RoomService {

    @Autowired
    public RoomRepository roomRepository;

    public List<Room> getAll() {
	return roomRepository.findAll();
    }

    public void delete(Integer id) {
	roomRepository.delete(id);
    }

    public Room add(Room room) {
	return roomRepository.save(room);
    }

    public Room getById(Integer id) {
	return roomRepository.findOne(id);
    }

}
