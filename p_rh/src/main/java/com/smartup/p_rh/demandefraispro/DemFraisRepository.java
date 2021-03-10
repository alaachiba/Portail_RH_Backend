package com.smartup.p_rh.demandefraispro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemFraisRepository extends JpaRepository<DemFrais, Integer> {

}
