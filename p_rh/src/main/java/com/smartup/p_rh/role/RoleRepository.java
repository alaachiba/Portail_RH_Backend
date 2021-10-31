package com.smartup.p_rh.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Optional<Role> findByLibelle(RoleName roleName);

}
