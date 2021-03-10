
package com.smartup.p_rh.users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("UserService")
@Transactional
public class UserServiceImp implements IUserService {

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

}
