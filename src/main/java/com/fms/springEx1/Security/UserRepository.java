package com.fms.springEx1.Security;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Uuser, Long>{
	public Uuser findByEmail(String email);
	public Uuser findByEmailAndPassword(String email, String pwd);
	public Page<Uuser> findByEmailContains(String email, Pageable pageable);
	public Uuser findByUserName(String userName);
}
