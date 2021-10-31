package com.smartup.p_rh.demandeavancesalaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DemAvanceRepository extends JpaRepository<DemAvance, Integer> {

	public DemAvance findDemById(Integer user);

	List<DemAvance> findByuser(Integer user);

	@Query(value = "select e.* from employee_avance_salaire_dem as e LEFT JOIN users as u ON e.users_login=u.users_login WHERE u.users_email= ?1 ", nativeQuery = true)
	List<DemAvance> findDemByEmail(String email);

}
