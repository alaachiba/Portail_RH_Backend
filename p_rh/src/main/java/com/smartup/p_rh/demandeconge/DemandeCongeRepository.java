package com.smartup.p_rh.demandeconge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Integer>{

}
