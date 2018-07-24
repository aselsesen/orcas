package com.orca.repository;

import com.orca.entities.Boat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoatRepository extends CrudRepository<Boat,String>{




List<Boat> findByBoatTypeAndReservable(String boatType , boolean reservable);





}
