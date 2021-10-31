package com.smartup.p_rh.congetype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeCongeTypeRepository extends JpaRepository<DemandeCongeType, Integer> {

}
