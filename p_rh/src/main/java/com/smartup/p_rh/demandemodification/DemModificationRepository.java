package com.smartup.p_rh.demandemodification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DemModificationRepository extends JpaRepository<DemModification, Integer> {

	@Query(value = "select e.* from employee_infos_rh_dem as e LEFT JOIN users as u ON e.users_login=u.users_login WHERE u.users_email= ?1 ", nativeQuery = true)
	List<DemModification> findDemByEmail(String email);

}
