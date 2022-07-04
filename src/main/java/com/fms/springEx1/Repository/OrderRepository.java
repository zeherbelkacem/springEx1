package com.fms.springEx1.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.springEx1.Entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//	@Query("select t from orders t where t.Customer.LastName like %?1% or t.Date like %?1%")
// Page<Order> findByCustomerLastNameContainsOrDateContains(String keyWord, String keyWord2, Pageable pageable);

	public Page<Order> findByCustomerLastName(String keyWod, Pageable pageable);
	public Page<Order> findByCustomerId(Long customerId, Pageable pageable);
}
