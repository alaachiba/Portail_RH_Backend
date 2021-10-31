
package com.smartup.p_rh.users;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IUserService {

	public User saveUser(User user);

	public User updateUser(User user);

	public boolean checkIfIdExists(Integer id);

	public void deleteUser(Integer id);

	public List<User> getAllUser();

	public User findUserByEmail(String email);

	public boolean existsUserByNom(String nom);

	public Boolean existsUserByEmail(String email);

	public User findUserById(Integer userId);

	public List<User> getallotherEmploye(String email);

	public ResponseEntity<String> changeuserpassword(String password, String newpassword, Integer id);

}
