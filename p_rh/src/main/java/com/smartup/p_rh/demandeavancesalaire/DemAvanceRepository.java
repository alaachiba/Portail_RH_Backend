package com.smartup.p_rh.demandeavancesalaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemAvanceRepository extends JpaRepository<DemAvance, Integer> {
	
	public DemAvance findDemById(Integer user);
	
	List<DemAvance> findByuser(Integer user);
	
	//@Query("select d from employee_avance_salaire_dem d where d.users_login = ?1")
	//List<DemAvance> findByNameEndsWith(Integer user);
	
	
	

}
