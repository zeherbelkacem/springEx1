package com.fms.springEx1.Security;

import java.util.List;

public interface UserService {
	public List<User> readAll();
	public User saveUser(User user);
	public User FindUserByEmail(String email);
	public User findUserByEmailAndPassword(String email, String password);
	public User readById(long id);
	

}
