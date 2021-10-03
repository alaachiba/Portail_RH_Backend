package com.smartup.p_rh.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findUserByEmailIgnoreCase(String email);

	public Optional<User> findByNom(String nom);

	public Boolean existsByNom(String nom);

	public Boolean existsByEmail(String email);
	
	public Optional<User> findByUsername(String username);
	
    public Boolean existsByMatricule(Integer matricule);
    
    public Boolean existsByUsername(String username);
    
    @Query(value="select e.* from users as e WHERE e.users_email != ?1 ", nativeQuery = true)
	List<User> getallotherEmploye (String email);
    @Modifying	
    @Query("update User u set u.pwd = :password where u.id = :id")
    public void changepassword(String password, Integer id);
}
