package com.smartup.p_rh.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findUserByEmailIgnoreCase(String email);

	public Optional<User> findByNom(String nom);

	public Boolean existsByNom(String nom);

	public Boolean existsByEmail(String email);
	
	public Optional<User> findByUsername(String username);
	
    public Boolean existsByMatricule(Integer matricule);
    
  //   public Boolean findByUsername(String username);
}
