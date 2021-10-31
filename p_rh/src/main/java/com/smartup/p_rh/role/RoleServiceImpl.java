package com.smartup.p_rh.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleDao;

	@Override
	public Role updateRole(Role role) {
		return roleDao.save(role);
	}

	@Override
	public List<Role> getAllRole() {
		return roleDao.findAll();
	}

	@Override
	public boolean checkIfIdExists(Integer id) {
		return roleDao.existsById(id);
	}
}
