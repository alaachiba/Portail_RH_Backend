package com.smartup.p_rh.demandeconge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Integer>{
	
	@Query(value="select e.* from employee_conges_dem as e LEFT JOIN users as u ON e.users_login=u.users_login WHERE u.users_email= ?1 ", nativeQuery = true)
	List<DemandeConge> findDemByEmail (String email);

}
