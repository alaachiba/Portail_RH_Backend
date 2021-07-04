package com.smartup.p_rh.demandeavancesalaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemAvanceRepository extends JpaRepository<DemAvance, Integer> {
	
	public DemAvance findDemById(Integer user);
	

}
