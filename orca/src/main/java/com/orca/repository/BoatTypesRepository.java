package com.orca.repository;

import com.orca.entities.BoatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;


public interface BoatTypesRepository extends CrudRepository<BoatType,String> {





}
