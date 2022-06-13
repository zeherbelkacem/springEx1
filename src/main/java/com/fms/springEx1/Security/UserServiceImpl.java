package com.fms.springEx1.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User FindUserByEmail(String email) {
	return userRepository.findByEmail(email);
	}

	

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public List<User> readAll() {
		return userRepository.findAll();
	}

	@Override
	public User readById(long id) {
		return userRepository.findById(id).get();
	}

}
