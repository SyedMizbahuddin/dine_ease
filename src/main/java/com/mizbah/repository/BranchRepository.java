package com.mizbah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mizbah.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

	List<Branch> findByCityId(Long cityId);

}
