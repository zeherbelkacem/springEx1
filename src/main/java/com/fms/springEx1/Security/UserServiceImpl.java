package com.fms.springEx1.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	long userId = 0;
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Uuser saveUuser(Uuser user) {
		String pwdEncoder = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(pwdEncoder);
		return userRepository.save(user);
	}

	@Override
	public Uuser findUuserByEmail(String email) {
	return userRepository.findByEmail(email);
	}

	

	@Override
	public Uuser findUserByEmailAndPassword(String email, String password) {
		Uuser user = userRepository.findByEmailAndPassword(email, password);
		if(user != null) userId = user.getUserId();
		return user;
	}

	@Override
	public List<Uuser> readAll() {
		return userRepository.findAll();
	}

	@Override
	public Uuser readById(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public Page<Uuser> findByPageByPageAndEmail(String email, Pageable pageable) {
		return userRepository.findByEmailContains(email, pageable);
	}

	@Override
	public Uuser findUuserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
