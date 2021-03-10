package com.smartup.p_rh.demandemodification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemModificationRepository extends JpaRepository<DemModification, Integer>{

}
