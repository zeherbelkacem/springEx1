package com.fms.springEx1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fms.springEx1.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
