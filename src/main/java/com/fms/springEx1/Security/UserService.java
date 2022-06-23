package com.fms.springEx1.Security;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	public List<Uuser> readAll();
	public Uuser saveUuser(Uuser user);
	public Uuser findUuserByEmail(String email);
	public Uuser findUuserByUserName(String userName);
	public Uuser findUserByEmailAndPassword(String email, String password);
	public Uuser readById(long id);
	
	/**
	 * 
	 * @return
	 */
	public long getUserId();
	public Page<Uuser> findByPageByPageAndEmail(String email, Pageable pageable);
	
	
	

}
