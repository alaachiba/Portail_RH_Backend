package com.smartup.p_rh.demandemodification.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemModificationTypeRepository extends JpaRepository<DemModificationType, Integer> {

}
