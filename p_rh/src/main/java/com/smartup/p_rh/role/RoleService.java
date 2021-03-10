package com.smartup.p_rh.role;

import java.util.List;

public interface RoleService {
	
	public Role updateRole(Role role);
	
	public List<Role> getAllRole();
	
	public boolean checkIfIdExists(Integer id);
	
}
