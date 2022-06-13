package com.fms.springEx1.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	public User findByEmailAndPassword(String email, String pwd);
}
