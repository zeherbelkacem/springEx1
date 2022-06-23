package com.fms.springEx1.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Rrole saveRole(Rrole role) {
		return roleRepository.save(role);
	}

}
