
package com.smartup.p_rh.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional
public class UserServiceImp implements IUserService {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private UserRepository userDao;

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userDao.save(user);
	}

	@Override
	public boolean checkIfIdExists(Integer id) {
		return userDao.existsById(id);
	}

	@Override
	public void deleteUser(Integer id) {
		userDao.deleteById(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.findAll();
	}

	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmailIgnoreCase(email);
	}

	@Override
	public boolean existsUserByNom(String nom) {
		return userDao.existsByNom(nom);
	}

	@Override
	public Boolean existsUserByEmail(String email) {
		return userDao.existsByEmail(email);

	}

	@Override
	public User findUserById(Integer id) {
		return userDao.getOne(id);
	}

	@Override
	public List<User> getallotherEmploye(String email) {
		return userDao.getallotherEmploye(email);
	}

	@Override
	public ResponseEntity<String> changeuserpassword(String password, String newpassword, Integer id) {
		Optional<User> usr = userDao.findById(id);
		if (encoder.matches(password, usr.get().getPwd())) {
			userDao.changepassword(encoder.encode(newpassword), id);
			return new ResponseEntity<String>("La mot de passe a été modifié", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erreur lors de la modification de mot de passe", HttpStatus.BAD_REQUEST);
	}

}
